package javafinal.logic;

import javafinal.utils.ManagementCSV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 공지사항 크롤링 기능을 제공하는 클래스입니다. </br>
 * 청주대학교의 셔틀버스 결행 관련 공지사항을 크롤링하고 CSV 파일로 저장합니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-12
 * @lastModified 2024-12-06
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
 * <li>2024-12-06: 크롤링 내용 CSV로 저장 및 송신</li>
 * </ul>
 */
public class NoticeCrawler {
    private String homepage = "https://www.cju.ac.kr/www/";

    /**
     * {@code NoticeCrawler} 의 기본 생성자입니다. </br>
     * 생성 시 자동으로 공지사항 크롤링 작업을 수행합니다.
     */
    public NoticeCrawler() {
        noticeListCrawler();
    }

    /**
     * 공지사항 목록을 크롤링하는 메서드입니다. </br>
     * 셔틀버스 결행 관련 공지사항을 청주대학교 홈페이지에서 가져와 가공한 후, {@link ManagementCSV#writeCSV(Map)}을 사용해 CSV 파일로 저장합니다.
     */
    private void noticeListCrawler() {
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

            new ManagementCSV().writeCSV(noticeData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 특정 공지사항의 내용을 크롤링하는 메서드입니다.
     * 
     * @param noticeSubjects 공지사항 제목과 관련된 HTML 요소
     * @return 크롤링된 공지사항의 세부 내용 (HTML 형식)
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