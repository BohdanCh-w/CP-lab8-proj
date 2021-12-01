package com.company.ui;

import java.awt.*;
import javax.swing.*;

public class UI {
    private JFrame root = new JFrame();
    private JPanel sidebar;
    
    public UI() {
        root.setSize(1280,720);
        root.setLayout(new GridLayout());

        createComponents();
        drawComponents();

        root.setVisible(true);
    }

    private void createComponents() {
        sidebar = new ControlPanel().getComponent();
    }

    private void drawComponents() {
        root.add(sidebar);
    }
}
