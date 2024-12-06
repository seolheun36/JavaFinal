package javafinal.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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
 * <li>2024-12-06: readCSV, createHashMap 메서드 작성</li>
 * </ul>
 */
public class ManagementCSV {
    /**
     * {@code readCSV} 메서드는 CSV를 읽어 리스트 형태로 반환해주는 메서드입니다.
     * 
     * @return CSV 파일을 읽어 String 타입으로 반환
     */
    private String readCSV() {
        String input = "./app/src/main/resources/data/notice.csv";
        StringBuilder text = new StringBuilder();

        try (
            FileInputStream fis = new FileInputStream(input);
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
        ) {
            int c;
            while ((c = br.read()) != -1) {
                text.append((char) c);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return text.toString();
    }

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

    /**
     * {@code createHashMap} 메서드는 String으로 불러온 CSV 파일 내용을 HashMap으로 변경하여 반환하는 메서드입니다.
     * 
     * @return CSV 파일 내용을 정제하여 HashMap 타입으로 반환
     */
    public Map<Integer, String[]> createHashMap() {
        StringTokenizer st = new StringTokenizer(readCSV(), "\r\n");
        Map<Integer, String[]> hashMap = new HashMap<>();

        int i = 0;
        while (st.hasMoreTokens()) {
            String content = st.nextToken();

            if (content.contains("공지")) {
                continue;
            }

            String[] contentArray = new String[2];
            String[] arrays = content.split("\",\"");
            contentArray[0] = arrays[0].substring(1);
            contentArray[1] = arrays[1].substring(0, arrays[1].length() - 1);

            hashMap.put(i, contentArray);

            i++;
        }

        return hashMap;
    }
}
