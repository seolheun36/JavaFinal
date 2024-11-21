package javafinal.ui;

import javafinal.logic.NoticeCrawler;
import javafinal.utils.Constants;
import javafinal.utils.CustomScrollBarUI;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

/**
 * {@code CJUShuttlePlusNoticePanel} 클래스는 셔틀 결행 공지 부분 패널에 대한 설정 클래스입니다.
 * 
 * @author seolheun5 (김은성, piberius5@gmail.com)
 * 
 * @create 2024-11-13
 * @lastModified 2024-11-19
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

        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        createTitlePanel();
        createNoticePanel();

        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.95;
        gbc.gridy = 1;
        mainPanel.add(noticeScrollPane, gbc);

        add(mainPanel);
    }

    /**
     * {@code createTitlePanel} 메서드는 Title Panel을 설정하고 추가하는 메서드입니다.
     * 
     * @author seolheun5
     */
    private void createTitlePanel() {
        titlePanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(Constants.NOTICE_TITLE, SwingConstants.LEFT);
        titleLabel.setFont(new Font(Constants.FONT, Font.PLAIN, 20));
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
        JPanel noticeListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        noticeListPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        noticeScrollPane = new JScrollPane(noticeListPanel);
        noticeScrollPane.setBorder(null);
        noticeScrollPane.setHorizontalScrollBar(null);
        noticeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollBar noticeScrollBar = noticeScrollPane.getVerticalScrollBar();
        noticeScrollBar.setUI(new CustomScrollBarUI());
        noticeScrollBar.setUnitIncrement(15);

        NoticeCrawler nc = new NoticeCrawler();
        Map<Integer, String[]> notices = nc.noticeListCrawler();
        for (int i = 0; i < notices.size(); i++) {
            String[] notice = notices.get(i);

            JPanel noticeBorderPanel = new JPanel();
            noticeBorderPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JPanel noticePanel = new JPanel(new GridBagLayout());
            noticePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;

            // 공지 제목 생성 및 설정
            JLabel noticeTitle = new JLabel(notice[0]);
            noticeTitle.setFont(new Font(Constants.FONT, Font.BOLD, 14));
            noticeTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 5));

            gbc.weightx = 1;
            gbc.weighty = 0.2;
            gbc.gridy = 0;
            noticePanel.add(noticeTitle, gbc);

            // 공지 내용 생성 및 설정
            String htmlContent = "<html><style>div { width: 250px; word-wrap: break-word; white-space: normal; }</style><div>" + notice[1] + "</div></html>";
            JLabel noticeContents = new JLabel(htmlContent);
            noticeContents.setFont(new Font(Constants.FONT, Font.PLAIN, 13));
            gbc.weightx = 1;
            gbc.weighty = 0.8;
            gbc.gridy = 1;
            noticePanel.add(noticeContents, gbc);

            noticeBorderPanel.add(noticePanel);
            noticeListPanel.add(noticeBorderPanel);
        }
    }
}
