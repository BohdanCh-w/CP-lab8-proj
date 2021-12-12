package com.company.ui;

import com.company.*;
import com.company.logger.LogLvl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import static com.company.Program.logger;

public class UI {
    private JFrame root = new JFrame();
    private BuildingPanel building;
    private ControlPanel sidebar;
    
    public UI() {
        root.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        root.setSize(1280,750);
        root.setLayout(null);
        root.getContentPane().setBackground(Color.decode("#e0ad34"));

        createComponents();
        drawComponents();

        root.setVisible(true);
        logger.Log("UI Loaded", LogLvl.LOG_FILE);
    }

    private void createComponents() {
        building = new BuildingPanel();
        sidebar = new ControlPanel();
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

    public ControlPanel Configuration() {
        return sidebar;
    }

    public void SetLiftListForConfig(List<Lift> lifts) {
        sidebar.SetLiftListForConfig(lifts);
    }

    public void addOnClose(ActionListener listener) {
        root.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                root.dispose();
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {});
            }
        });
    }

    public void addOnStart(ActionListener listener) {
        sidebar.addStartActionListener(listener);
    }

    public void addOnBuild(ActionListener listener) {
        sidebar.addBuildActionListener(listener);
    }
}
