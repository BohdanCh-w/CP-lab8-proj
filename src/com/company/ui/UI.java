package com.company.ui;

import java.awt.*;
import javax.swing.*;

public class UI {
    private JFrame root = new JFrame();
    private JPanel sidebar;
    private JPanel building;
    
    public UI() {
        root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        root.setSize(1280,720);
        root.setLayout(null);

        createComponents();
        drawComponents();

        root.setVisible(true);
    }

    private void createComponents() {
        sidebar = new ControlPanel().getComponent();
        building = new BuildingPanel().getComponent();
    }

    private void drawComponents() {        
        root.add(sidebar);
        sidebar.setBounds(0, 0, 350, 720);

        root.add(building);
        building.setBounds(350, 0, 1280, 720);
    }
}
