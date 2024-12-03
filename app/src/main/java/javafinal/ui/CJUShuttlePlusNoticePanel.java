package javafinal.ui;

import javafinal.logic.NoticeCrawler;
import javafinal.utils.Constants;
import javafinal.utils.CustomScrollBarUI;
import javafinal.utils.RoundedPanel;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

/**
 * {@code CJUShuttlePlusNoticePanel} 클래스는 셔틀 결행 공지 부분 패널에 대한 설정 클래스입니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-13
 * @lastModified 2024-12-03
 * 
 * @changelog
 * <ul>
 * <li>2024-11-13: 최초 생성</li>
 * <li>2024-11-13: 결행 공지 제목 및 내용 생성</li>
 * <li>2024-11-19: 결행 공지 패널 공지 부분 공지 제목 및 내용 추가</li>
 * <li>2024-11-19: nc 객체의 return 타입 변경에 따른 코드 수정</li>
 * <li>2024-11-20: 셔틀 결행 공지 내용 부분 레이아웃 변경</li>
 * <li>2024-11-21: 셔틀 결행 공지 내용 JLabel 자동 줄바꿈 설정</li>
 * <li>2024-11-21: 스크롤바 디자인 적용</li>
 * <li>2024-12-03: 타이틀 및 내용, 내용 패널에서의 타이틀과 내용, 각 내용 패널 사이의 레이아웃 수정</li>
 * </ul>
 */
public class CJUShuttlePlusNoticePanel extends JPanel {
    private JPanel titlePanel;
    private JScrollPane noticeScrollPane;

    /**
     * {@code CJUShuttlePlusNoticePanel} 패널의 생성자로 패널의 큰 틀을 설정한다.
     * 
     * @author seolheun5
     */
    protected CJUShuttlePlusNoticePanel() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        createTitlePanel();
        createNoticePanel();

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(noticeScrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * {@code createTitlePanel} 메서드는 Title Panel을 설정하고 추가하는 메서드입니다.
     * 
     * @author seolheun5
     */
    private void createTitlePanel() {
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        titlePanel.setBackground(Constants.CJU_MAIN_BLUE);

        JLabel titleLabel = new JLabel(Constants.NOTICE_TITLE, SwingConstants.LEFT);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font(Constants.FONT, Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        titlePanel.add(titleLabel, BorderLayout.WEST);
    }

    /**
     * {@code createNoticePanel} 메서드는 공지 내용인 Notice Panel을 설정하고 추가하는 메서드입니다.
     * 
     * @author seolheun5
     * 
     * @see <a href="https://heaven0713.tistory.com/28">JLabel 개행 처리 참고</a>
     * @see <a href="https://www.php.cn/ko/faq/1796691556.html">JLabel 자동 줄바꿈 참고1</a>
     * @see <a href="https://chat.openai.com">JLabel 자동 줄바꿈 참고2</a>
     */
    private void createNoticePanel() {
        JPanel noticeListPanel = new JPanel();
        noticeListPanel.setLayout(new BoxLayout(noticeListPanel, BoxLayout.Y_AXIS));
        noticeListPanel.setBorder(BorderFactory.createEmptyBorder(3, 8, 8, 8));
        noticeListPanel.setBackground(Constants.CJU_PANEL_BACKGROUND);

        noticeScrollPane = new JScrollPane(noticeListPanel);
        noticeScrollPane.setBorder(null);
        noticeScrollPane.setBackground(Constants.CJU_PANEL_BACKGROUND);
        noticeScrollPane.setHorizontalScrollBar(null);
        noticeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollBar noticeScrollBar = noticeScrollPane.getVerticalScrollBar();
        noticeScrollBar.setUI(new CustomScrollBarUI());
        noticeScrollBar.setUnitIncrement(15);

        NoticeCrawler nc = new NoticeCrawler();
        Map<Integer, String[]> notices = nc.noticeListCrawler();
        for (int i = 0; i < notices.size(); i++) {
            String[] notice = notices.get(i);

            RoundedPanel noticeBorderPanel = new RoundedPanel(Constants.NOTICE_BORDER_COLOR, 10);
            noticeBorderPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            noticeBorderPanel.setBackground(Constants.CJU_CONTENT_BACKGROUND);

            JPanel noticePanel = new JPanel(new BorderLayout());
            noticePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            noticePanel.setBackground(Constants.CJU_CONTENT_BACKGROUND);

            // 공지 제목 생성 및 설정
            JLabel noticeTitle = new JLabel(notice[0]);
            noticeTitle.setFont(new Font(Constants.FONT, Font.BOLD, 14));
            noticeTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 5));
            noticePanel.add(noticeTitle, BorderLayout.NORTH);

            // 공지 내용 생성 및 설정
            String htmlContent = "<html><style>div { width: 250px; word-wrap: break-word; white-space: normal; }</style><div>" + notice[1] + "</div></html>";
            JLabel noticeContents = new JLabel(htmlContent);
            noticeContents.setFont(new Font(Constants.FONT, Font.PLAIN, 13));
            noticePanel.add(noticeContents, BorderLayout.CENTER);

            noticeBorderPanel.add(noticePanel);
            noticeListPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            noticeListPanel.add(noticeBorderPanel);
        }
    }
}
