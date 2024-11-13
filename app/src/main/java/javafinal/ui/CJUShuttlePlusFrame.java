package javafinal.ui;

import javax.swing.*;

public class CJUShuttlePlusFrame extends JFrame {
    public CJUShuttlePlusFrame() {
        setTitle("청주대학교 셔틀+");
        setSize(400, 700);
        setLocationRelativeTo(null);

        CJUShuttlePlusNotice noticePanel = new CJUShuttlePlusNotice();
        add(noticePanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
