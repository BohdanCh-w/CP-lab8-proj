package com.company.ui;

import java.awt.*;
import javax.swing.*;

public class ControlPanel {
    private JPanel panel = new JPanel();
    private JLabel label;

    public ControlPanel() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 720));

        createComponents();
        drawComponents();
    }

    private void createComponents() {
        label = new JLabel("Label");
    }

    private void drawComponents() {
        var labelConstr = new GridBagConstraints();
        labelConstr.gridx = 0;
        labelConstr.gridy = 0;
        panel.add(label, labelConstr);        
    }

    public JPanel getComponent() {
        return panel;
    }
}
