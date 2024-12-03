package javafinal.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * {@code CJUShuttlePlusFrame} 클래스는 앱의 패널들을 관리해주는 프레임입니다.
 * 
 * @author seolheun5
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
    public CJUShuttlePlusFrame() {
        setTitle("청주대학교 셔틀+");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);

        Image icon = new ImageIcon(getClass().getResource("../resources/icon/busIcon.png")).getImage();
        setIconImage(icon);

        CJUShuttlePlusNoticePanel noticePanel = new CJUShuttlePlusNoticePanel();
        add(noticePanel);

        setVisible(true);
    }
}
