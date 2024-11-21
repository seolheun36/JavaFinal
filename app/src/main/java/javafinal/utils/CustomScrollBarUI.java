package javafinal.utils;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;


/**
 * {@code CustomScrollBarUI} 클래스는 스크롤바의 커스텀 디자인을 설정하는 클래스입니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-21
 * @lastModified 2024-11-21
 * 
 * @changelog
 * <ul>
 * <li>2024-11-21: 최초 생성</li>
 * <li>2024-11-21: 스크롤바 버튼 삭제</li>
 * </ul>
 */
public class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }
}