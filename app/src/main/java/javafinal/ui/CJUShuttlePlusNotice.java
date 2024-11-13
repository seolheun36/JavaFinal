package javafinal.ui;

import javafinal.logic.NoticeCrawler;
import javafinal.utils.Constants;

import java.awt.*;
import javax.swing.*;

public class CJUShuttlePlusNotice extends JPanel {
    private JPanel titlePanel;
    private JPanel noticeListPanel;

    protected CJUShuttlePlusNotice() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        createTitlePanel();
        createNoticePanel();

        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridy = 1;
        mainPanel.add(noticeListPanel, gbc);

        add(mainPanel);
    }

    private void createTitlePanel() {
        titlePanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(Constants.NOTICE_TITLE, SwingConstants.LEFT);
        titleLabel.setFont(new Font(Constants.FONT, Font.PLAIN, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));

        titlePanel.add(titleLabel, BorderLayout.WEST);
    }

    private void createNoticePanel() {
        noticeListPanel = new JPanel(new GridLayout(0, 1));

        NoticeCrawler nc = new NoticeCrawler();
        String[][] notices = nc.noticeListCrawler();
        for (String[] notice : notices) {
            JPanel noticePanel = new JPanel();

            JLabel noticeTitle = new JLabel(notice[0]);
            noticePanel.add(noticeTitle);

            noticeListPanel.add(noticePanel);
        }
    }
}
