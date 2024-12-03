package javafinal.utils;

import javax.swing.*;
import java.awt.*;

/**
 * {@code Rounded Panel} 클래스는 둥근 JPanel을 설정하는 클래스입니다.
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
    private Color color;
    private int radius;

    public RoundedPanel(Color color, int radius) {
        this.color = color;
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}