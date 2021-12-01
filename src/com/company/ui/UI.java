package com.company.ui;

import java.awt.*;
import javax.swing.*;

public class UI {
    private JFrame root = new JFrame();
    private JPanel sidebar;
    private JPanel building;
    
    public UI() {
        root.setSize(1280,720);
        root.setLayout(new GridBagLayout());

        createComponents();
        drawComponents();

        root.setVisible(true);
    }

    private void createComponents() {
        sidebar = new ControlPanel().getComponent();
        building = new BuildingPanel().getComponent();
    }

    private void drawComponents() {        
        var sidebarConstr = new GridBagConstraints();
        sidebarConstr.gridx = 0;
        sidebarConstr.gridy = 0;
        sidebarConstr.weightx = 350;
        root.add(sidebar, sidebarConstr);
            
        var buildingConstr = new GridBagConstraints();
        buildingConstr.gridx = 1;
        buildingConstr.gridy = 0;
        buildingConstr.weightx = 930;
        root.add(building, buildingConstr);
    }
}
