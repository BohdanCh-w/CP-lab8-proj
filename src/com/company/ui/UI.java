package com.company.ui;

import com.company.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class UI {
    private JFrame root = new JFrame();
    private BuildingPanel building;
    private ControlPanel sidebar;
    
    public UI() {
        root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        root.setSize(1280,720);
        root.setLayout(null);

        createComponents();
        drawComponents();

        root.setVisible(true);
    }

    private void createComponents() {
        building = new BuildingPanel();
        sidebar = new ControlPanel();
        sidebar.addBuildActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Build();
            }
        });
        sidebar.addStartActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Start();
            }
        });
    }

    private void drawComponents() {        
        root.add(sidebar.getComponent());
        sidebar.getComponent().setBounds(0, 0, 350, 720);

        root.add(building.getComponent());
        building.getComponent().setBounds(350, 0, 1280, 720);
    }

    public BuildingPanel Building() {
        return building;
    }

    public void SetLiftListForConfig(List<Lift> lifts) {
        sidebar.SetLiftListForConfig(lifts);
    }

    private void Start() {

    }

    private void Build() {
        int floors = sidebar.getFloorsCount();
        int lifts = sidebar.getLiftsCount();
        building.CreateBuilding(floors, lifts);
    }
}
