package javafinal.utils;

import java.awt.Color;

/**
 * 상수를 정의한 유틸리티 클래스입니다. </br>
 * 애플리케이션에서 공통적으로 사용되는 색상, 텍스트, 폰트 정보를 관리합니다.
 * 
 * @author seolheun5
 * 
 * @create 2024-11-13
 * @lastModified 2024-11-22
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
     * 생성자가 호출되지 않도록 설정된 유틸리티 클래스입니다. </br>
     * 이 클래스의 인스턴스를 생성하려고 하면 {@link IllegalStateException} 예외를 발생시킵니다.
     *
     * @see <a href="https://chat.openai.com">인스턴스 생성 방지 참고</a>
     * @see <a href="https://rules.sonarsource.com/java/tag/design/RSPEC-1118">SonarLint</a> - Utility classes should not have public constructors</a>
     */
    private Constants() {
        throw new IllegalStateException("This is a Utility class");
    }

    // 폰트
    /** 애플리케이션에서 사용할 기본 폰트 이름입니다. */
    public static final String FONT = "SansSerif";

    // 텍스트
    /** 공지 제목 텍스트로 사용되는 문자열입니다. */
    public static final String NOTICE_TITLE = "셔틀 결행 공지";

    // 색상
    /** 앱 메인 색상(파란색) 상수입니다. */
    public static final Color CJU_MAIN_BLUE = new Color(0x2d4b7a);
    /** 투명 색상 상수입니다. */
    public static final Color CLEAR_COLOR = new Color(0, 0, 0, 0);
    /** 패널의 기본 배경색입니다. */
    public static final Color CJU_PANEL_BACKGROUND = new Color(0xe7ecf2);
    /** 콘텐츠 영역의 기본 배경색입니다. */
    public static final Color CJU_CONTENT_BACKGROUND = new Color(0xffffff);
    /** 공지 테두리 색상 상수입니다. */
    public static final Color NOTICE_BORDER_COLOR = new Color(0xdddddd);
}
