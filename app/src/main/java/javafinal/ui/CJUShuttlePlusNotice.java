package javafinal.ui;

import java.awt.*;
import javax.swing.*;

public class CJUShuttlePlusNotice extends JPanel {
    private JPanel titlePanel;

    protected CJUShuttlePlusNotice() {
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        createTitlePanel();

        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        add(mainPanel);
    }

    private void createTitlePanel() {
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.black);

        JLabel titleLabel = new JLabel("셔틀 결행 공지", SwingConstants.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));

        titlePanel.add(titleLabel, BorderLayout.WEST);
    }
}
