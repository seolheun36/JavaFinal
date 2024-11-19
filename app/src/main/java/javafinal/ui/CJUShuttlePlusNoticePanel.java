package javafinal.ui;

import javafinal.logic.NoticeCrawler;
import javafinal.utils.Constants;

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
        gbc.weighty = 0.1;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.9;
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
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));

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
     */
    private void createNoticePanel() {
        JPanel noticeListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        noticeListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        noticeScrollPane = new JScrollPane(noticeListPanel);
        noticeScrollPane.setHorizontalScrollBar(null);
        noticeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        NoticeCrawler nc = new NoticeCrawler();
        Map<Integer, String[]> notices = nc.noticeListCrawler();
        for (int i = 0; i < notices.size(); i++) {
            String[] notice = notices.get(i);

            JPanel noticeBorderPanel = new JPanel();
            noticeBorderPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JPanel noticePanel = new JPanel(new GridLayout(0, 1));
            noticePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel noticeTitle = new JLabel(notice[0]);
            noticePanel.add(noticeTitle);

            String htmlContent = "<html>" + notice[1] + "</html>";
            JLabel noticeContents = new JLabel(htmlContent);
            noticePanel.add(noticeContents);

            noticeBorderPanel.add(noticePanel);
            noticeListPanel.add(noticeBorderPanel);
        }
    }
}
