package javafinal.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * {@code NoticeCrawler} 클래스
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-12
 * @lastModified 2024-12-03
 * 
 * @changelog
 * <ul>
 * <li>2024-11-12: 최초 생성</li>
 * <li>2024-11-12: 결행 공지사항 내용 추출기능 추가</li>
 * <li>2024-11-12: 결행 게시물 제목 및 내용 추출기능 업데이트</li>
 * <li>2024-11-12: 추출한 데이터를 이차원 배열에 저장하는 기능 추가</li>
 * <li>2024-11-19: noticeNum이 '[공지]'인 게시물이 두 개 이상일 때 예외처리</li>
 * <li>2024-11-19: 이차원 배열 data를 Map으로 변경</li>
 * <li>2024-12-03: 셔틀 결행 공지 내용 JLabel 자동 줄바꿈 이전 설정</li>
 * <li>2024-12-03: 크롤링 내용 전달 타입 변경</li>
 * </ul>
 */
public class NoticeCrawler {
    private String homepage = "https://www.cju.ac.kr/www/";

    /**
     * {@code noticeListCrawler} 메서드는 셔틀 결행 공지사항 리스트를 순회하며 리스트 기본 정보를 크롤링하는 메서드입니다.
     * 
     * @author seolheun5
     */
    public Map<Integer, ArrayList<String>> noticeListCrawler() {
        // 공지사항 사이트에서 '결행'을 검색했을 때 homepage url 이후 url과 homepage url을 합치는 코드
        String noticeListURL = homepage + "selectBbsNttList.do?key=4577&bbsNo=881&searchCnd=SJ&searchKrwd=결행";

        Map<Integer, ArrayList<String>> noticeData = new HashMap<>();

        try {
            Document doc = Jsoup.connect(noticeListURL).get();
            Elements noticeTBodys = doc.select(".tb");
            Elements noticeTRs = noticeTBodys.select("tr");

            // 공지 번호가 '[공지]'로 되어 있는 공지는 셔틀버스 결행과는 관련 없는 공지이므로 이를 예외처리하는 코드
            Elements firstFiveNoticeTRs;
            int startIdx = 0;
            for (int i = 0; noticeTRs.get(i).text().contains("[공지]"); i++) {
                startIdx = i;
            }
            startIdx += 1;
            firstFiveNoticeTRs = new Elements(noticeTRs.subList(startIdx, startIdx + 5));

            // 공지사항 각 내용을 순회하면서 각 내용을 추출하고 data라는 매트릭스에 저장하는 코드
            int i = 0;
            for (Element noticeTR : firstFiveNoticeTRs) {
                Elements noticeSubjects = noticeTR.select(".subject");

                ArrayList<String> noticeLine = new ArrayList<>();

                noticeLine.add(noticeSubjects.text());
                noticeLine.add(contentsCrawler(noticeSubjects));

                noticeData.put(i, noticeLine);

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noticeData;
    }

    /**
     * {@code contentsCrawler} 메서드는 각 공지 내용을 크롤링하는 메서드입니다.
     * 
     * @author seolheun5
     * 
     * @param noticeSubjects 각 공지 내용 중 {@code class="subject"}인 요소를 담은
     *                     {@code Elements} 타입 자료
     * @return 공지 내용을 String 타입으로 반환
     */
    private String contentsCrawler(Elements noticeSubjects) {
        Elements contentLinks = noticeSubjects.select("a");

        // homepage url이 /로 끝나고 각 게시글에 할당된 href url이 ./로 시작하여 ./를 자르고 저장하는 코드
        String link = contentLinks.attr("href").substring(2);
        String noticeURL = homepage + link;

        String notice = "";

        // p.0 클래스로 구분되어있는 문장을 하나의 notice라는 String 타입으로 변경하는 코드
        try {
            Document doc = Jsoup.connect(noticeURL).get();
            Elements contents = doc.select(".bbs_content");
            Elements contentLines = contents.select(".0");

            StringBuilder bld = new StringBuilder();

            bld.append("<html><style>div { width: 250px; word-wrap: break-word; white-space: normal; }</style><div>");

            for (Element element : contentLines) {
                Elements contentTexts = element.select("span");

                for (Element contentText : contentTexts) {
                    bld.append(contentText.wholeText());
                }
                bld.append("<br>");
            }

            bld.append("</div></html>");

            notice = bld.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notice;
    }
}