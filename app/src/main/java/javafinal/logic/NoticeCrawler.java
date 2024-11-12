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
     * 
     */
    NoticeCrawler() {
        String infoUrl = homepage + "selectBbsNttList.do?key=4577&bbsNo=881&searchCnd=SJ&searchKrwd=결행";
        try {
            Document doc = Jsoup.connect(infoUrl).get();
            Elements infoBox = doc.select(".tb");
            Elements infoLists = infoBox.select("tr");

            for (Element infoList : infoLists) {
                String infoNum = infoList.select(".web_block").first().text();

                if (!infoNum.contains("[공지]")) {
                    Elements infoSubjects = infoList.select(".subject");
                    contentsCrawler(infoSubjects);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void contentsCrawler(Elements infoSubjects) {
        Elements infoLinks = infoSubjects.select("a");

        String subject = infoSubjects.text();
        String link = infoLinks.attr("href").substring(2);
        String listUrl = homepage + link;

        try {
            Document doc = Jsoup.connect(listUrl).get();
            Elements contents = doc.select(".bbs_content");
            Elements contentLines = contents.select(".0");

            for (Element element : contentLines) {
                Elements contentTexts = element.select("span");

                for (Element contentText : contentTexts) {
                    System.out.print(contentText.wholeText());
                }

                System.out.println();
            }

            System.out.println("---");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NoticeCrawler();
    }
}