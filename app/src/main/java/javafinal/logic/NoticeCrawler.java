package javafinal.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * {@code NoticeCrawler} 클래스
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-12
 * @lastModified 2024-11-12
 * 
 * @changelog
 *            <ul>
 *            <li>2024-11-12: 최초 생성</li>
 *            </ul>
 */
public class NoticeCrawler {
    private String homepage = "https://www.cju.ac.kr/www/";

    /**
     * {@code NoticeCrawler} 생성자
     * 
     * @author seolheun5
     */
    NoticeCrawler() {
        String noticeListURL = homepage + "selectBbsNttList.do?key=4577&bbsNo=881&searchCnd=SJ&searchKrwd=결행";
        try {
            Document doc = Jsoup.connect(noticeListURL).get();
            Elements noticeTBody = doc.select(".tb");
            Elements noticeTRs = noticeTBody.select("tr");

            for (Element noticeTR : noticeTRs) {
                String infoNum = noticeTR.select(".web_block").first().text();

                if (!infoNum.contains("[공지]")) {
                    Elements noticeSubjectss = noticeTR.select(".subject");
                    
                    String noticeSubject = noticeSubjectss.text();
                    String noticeContent = contentsCrawler(noticeSubjectss);

                    System.out.println(noticeSubject + "\n\n" + noticeContent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@code contentsCrawler} 메서드
     * 
     * @author seolheun5
     * 
     * @param infoSubjects 각 공지 내용 중 {@code class="subject"}인 요소를 담은 {@code Elements} 타입 자료
     */
    private String contentsCrawler(Elements noticeSubjects) {
        Elements noticeLinks = noticeSubjects.select("a");

        String link = noticeLinks.attr("href").substring(2);
        String noticeURL = homepage + link;

        String notice = "";

        try {
            Document doc = Jsoup.connect(noticeURL).get();
            Elements contents = doc.select(".bbs_content");
            Elements contentLines = contents.select(".0");

            StringBuilder bld = new StringBuilder();
            for (Element element : contentLines) {
                Elements contentTexts = element.select("span");

                for (Element contentText : contentTexts) {
                    bld.append(contentText.wholeText());
                }
                bld.append("\n");
            }
            notice = bld.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return notice;
    }

    public static void main(String[] args) {
        new NoticeCrawler();
    }
}