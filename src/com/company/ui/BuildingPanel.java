package com.company.ui;

import java.awt.*;
import javax.swing.*;

public class BuildingPanel {
    private JPanel panel = new JPanel();

    public BuildingPanel() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(410, 720));

        createComponents();
        drawComponents();
    }

    private void createComponents() {
        
    }

    private void drawComponents() {

    }

    public JPanel getComponent() {
        return panel;
    }
}
