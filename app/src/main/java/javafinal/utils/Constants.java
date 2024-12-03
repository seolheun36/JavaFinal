package javafinal.utils;

import java.awt.Color;

/**
 * {@code Constants} 클래스는 앱 전반에서 사용하는 상수들을 모아서 관리해주는 클래스입니다.
 * 
 * @author seolheun5
 * 
 * @create 2024-11-13
 * @lastModified 2024-11-13
 * 
 * @changelog
 * <ul>
 * <li>2024-11-13: 최초 생성</li>
 * <li>2024-11-13: FONT 상수와 NOTICE_TITLE 상수 추가</li>
 * <li>2024-11-19: 유틸리티 클래스에서 인스턴스화 방지</li>
 * <li>2024-11-22: 색상 상수 추가</li>
 * </ul>
 */
public class Constants {
    /**
     * 유틸리티 클래스인 {@code Constants}의 인스턴스화 방지 처리.
     */
    private Constants() {
        throw new IllegalStateException("This is a Utility class");
    }

    // 폰트
    public static final String FONT = "SansSerif";

    // 텍스트
    public static final String NOTICE_TITLE = "셔틀 결행 공지";

    // 색상
    public static final Color CJU_MAIN_BLUE = new Color(0x2d4b7a);
    public static final Color CLEAR_COLOR = new Color(0, 0, 0, 0);
    public static final Color CJU_PANEL_BACKGROUND = new Color(0xe7ecf2);
    public static final Color CJU_CONTENT_BACKGROUND = new Color(0xffffff);
    public static final Color NOTICE_BORDER_COLOR = new Color(0xdddddd);
}
