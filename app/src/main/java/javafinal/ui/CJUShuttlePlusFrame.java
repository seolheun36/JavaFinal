package javafinal.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 청주대학교 셔틀+ 프로그램의 메인 프레임을 구성하는 클래스입니다. </br>
 * 이 클래스는 프로그램의 최상위 창을 생성하며, 공지사항 패널을 포함합니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-13
 * @lastModified 2024-12-03
 * 
 * @changelog
 * <ul>
 * <li>2024-11-13: 최초 생성</li>
 * <li>2024-11-13: 프레임 기본 설정</li>
 * <li>2024-11-19: 셔틀 결행 패널 클래스 이름 변경에 따른 수정</li>
 * <li>2024-12-03: 앱 아이콘 설정</li>
 * </ul>
 * 
 * @see <a href="https://www.flaticon.com/uicons">Flaticon</a>의 UIcon
 */
public class CJUShuttlePlusFrame extends JFrame {

    /**
     * {@code CJUShuttlePlusFrame}의 생성자입니다. </br>
     * 프로그램의 메인 프레임을 초기화하고 기본 설정을 적용합니다.
     * <ul>
     *     <li>창 제목: "청주대학교 셔틀+"</li>
     *     <li>창 크기: 400x700 픽셀</li>
     *     <li>화면 중앙에 창 배치</li>
     *     <li>아이콘 이미지 설정</li>
     *     <li>{@link CJUShuttlePlusNoticePanel} 추가</li>
     * </ul>
     */
    public CJUShuttlePlusFrame() {
        setTitle("청주대학교 셔틀+");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);

        Image icon = new ImageIcon(getClass().getClassLoader().getResource("icon/busIcon.png")).getImage();
        setIconImage(icon);

        CJUShuttlePlusNoticePanel noticePanel = new CJUShuttlePlusNoticePanel();
        add(noticePanel);

        setVisible(true);
    }
}
