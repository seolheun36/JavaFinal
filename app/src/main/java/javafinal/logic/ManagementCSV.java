package javafinal.logic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

/**
 * {@code ManagementCSV} 클래스는 CSV를 읽고 쓰는 기능을 관리하는 클래스입니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-12-06
 * @lastModified 2024-12-06
 * 
 * @changelog
 * <ul>
 * <li>2024-12-06: 최초 생성</li>
 * <li>2024-12-06: writeCSV 메서드 작성</li>
 * </ul>
 */
public class ManagementCSV {

    /**
     * {@code ManagementCSV} 생성자
     */
    protected ManagementCSV() {}

    /**
     * {@code writeCSV} 메서드는 HashMap 데이터를 받아 CSV 파일로 작성하는 메서드입니다.
     * 
     * @param hashMap 공지 제목과 내용을 담고 있는 
     */
    protected void writeCSV(Map<Integer, ArrayList<String>> hashMap) {
        String output = "./app/src/main/resources/data/notice.csv";

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output, false), StandardCharsets.UTF_8))) {
            bw.write("공지제목,공지내용");
            bw.write("\n");

            for (int i = 0; i < hashMap.size(); i++) {
                ArrayList<String> notice = hashMap.get(i);

                StringBuilder noticeStringBuilder = new StringBuilder();
                noticeStringBuilder.append("\"" + notice.get(0) + "\"");
                noticeStringBuilder.append(",");
                noticeStringBuilder.append("\"" + notice.get(1) + "\"");

                String noticeString = noticeStringBuilder.toString();

                bw.write(noticeString);
                bw.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
