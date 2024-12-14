package javafinal.utils;

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
 * CSV 파일의 읽기, 쓰기 및 해시맵 생성을 담당하는 유틸리티 클래스입니다.
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
     * CSV 파일을 읽어 문자열로 반환합니다.
     * 
     * @return CSV 파일의 내용을 문자열로 반환합니다.</br>
     *         파일을 읽는 중 오류가 발생하면 빈 문자열을 반환합니다.
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
            e.printStackTrace();
        }

        return text.toString();
    }

    /**
     * 주어진 해시맵 데이터를 CSV 파일에 저장합니다.
     * 
     * @param hashMap 공지 제목과 내용을 포함한 데이터가 저장된 해시맵입니다.</br>
     *                키는 정수형 인덱스이며, 값은 공지 제목과 내용을 포함한 문자열 리스트입니다.
     */
    public void writeCSV(Map<Integer, ArrayList<String>> hashMap) {
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
     * CSV 파일의 데이터를 해시맵 형태로 변환합니다.
     * 
     * @return CSV 데이터를 기반으로 생성된 해시맵을 반환합니다.</br>
     *         키는 정수형 인덱스이며, 값은 공지 제목과 내용을 포함한 문자열 배열입니다.
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
