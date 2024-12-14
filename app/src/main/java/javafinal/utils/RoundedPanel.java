package javafinal.utils;

import javax.swing.*;
import java.awt.*;

/**
 * {@code RoundedPanel} 클래스는 모서리가 둥근 사각형 형태의 GUI 패널을 생성하는 클래스입니다.</br>
 * 이 클래스는 {@link JPanel}을 상속하며, 패널의 배경색과 테두리 색상, 그리고 모서리의 반지름을 설정할 수 있습니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-12-03
 * @lastModified 2024-12-03
 * 
 * @changelog
 *  <ul>
 *  <li>2024-12-03: 최초 생성</li>
 *  <li>2024-12-03: 둥근 패널 설정 코드 작성</li>
 *  </ul>
 * 
 * @see <a href="https://chat.openai.com">둥근 패널 참고</a>
 */
public class RoundedPanel extends JPanel {

    /** 테두리 색상을 지정하는 변수 */
    private Color color;
    /** 모서리의 반지름을 저장하는 변수 */
    private int radius;

    /**
     * 지정된 색상과 반지름을 사용하여 {@code RoundedPanel} 인스턴스를 생성합니다.
     * 
     * @param color 테두리의 색상
     * @param radius 패널 모서리의 반지름
     */
    public RoundedPanel(Color color, int radius) {
        this.color = color;
        this.radius = radius;
        setOpaque(false);
    }

    /**
     * 패널의 내부를 둥근 사각형으로 채우는 메서드입니다.
     * 
     * @param g 그래픽 컨텍스트 객체
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
    }

    /**
     * 패널의 테두리를 둥근 사각형 형태로 그리는 메서드입니다.
     * 
     * @param g 그래픽 컨텍스트 객체
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}