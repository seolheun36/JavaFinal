package javafinal.utils;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;


/**
 * {@code CustomScrollBarUI} 클래스는 스크롤바의 사용자 지정 UI를 제공하며, 스크롤바의 버튼과 트랙, thumb의 외형을 변경합니다.
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
 * <li>2024-11-21: 스크롤바 디자인 및 색상 변경</li>
 * <li>2024-11-21: 스크롤바 트랙 디자인 변경</li>
 * </ul>
 *
 * @see <a href="https://chat.openai.com">커스텀 스크롤바 참고</a>
 */
public class CustomScrollBarUI extends BasicScrollBarUI {
    
    // 버튼 설정
    /**
     * 스크롤바 감소 버튼을 생성합니다.</br>
     * 이 메서드는 감소 버튼을 숨기기 위해 사용됩니다.
     *
     * @param orientation 버튼의 방향
     * @return 숨겨진 상태의 버튼
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    /**
     * 스크롤바 증가 버튼을 생성합니다.</br>
     * 이 메서드는 증가 버튼을 숨기기 위해 사용됩니다.
     * 
     * @param orientation 버튼의 방향
     * @return 숨겨진 상태의 버튼
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    /**
     * 보이지 않는 버튼을 생성합니다.</br>
     * 이 버튼은 크기가 0으로 설정되어 화면에 표시되지 않습니다.
     * 
     * @return 숨겨진 상태의 버튼
     */
    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    // thumb 설정
    /**
     * 스크롤바의 thumb을 그립니다.</br>
     * thumb은 밝은 회색으로 채워진 둥근 사각형 형태로 그려집니다.
     * 
     * @param g 그래픽 객체
     * @param c 컴포넌트
     * @param thumbBounds thumb의 위치와 크기 정보
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(Color.lightGray);
        g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
    }

    /**
     * 스크롤바의 선호 크기를 반환합니다.</br>
     * 가로 또는 세로 방향에 따라 스크롤바의 두께를 설정합니다.
     *
     * @param c 컴포넌트
     * @return 스크롤바의 선호 크기
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
            return new Dimension(8, super.getPreferredSize(c).height);
        } else {
            return new Dimension(super.getPreferredSize(c).width, 10);
        }
    }

    // 스크롤바 트랙 설정
    /**
     * 스크롤바 트랙을 그립니다.>/br>
     * 트랙은 {@link Constants#CLEAR_COLOR} 색상으로 채워집니다.
     *
     * @param g 그래픽 객체
     * @param c 컴포넌트
     * @param trackBounds 트랙의 위치와 크기 정보
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Constants.CLEAR_COLOR);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    /**
     * 스크롤바 트랙의 위치와 크기를 반환합니다.</br>
     * 트랙의 높이와 너비를 직접 수정하여 크기를 변경합니다.
     *
     * @return 트랙의 위치와 크기 정보
     */
    @Override
    protected Rectangle getTrackBounds() {
        Rectangle trackBounds = super.getTrackBounds();
        // 트랙 높이 및 너비를 직접 수정하여 크기 변경
        if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
            trackBounds.width = 12;
        } else {
            trackBounds.height = 12;
        }
        return trackBounds;
    }
}