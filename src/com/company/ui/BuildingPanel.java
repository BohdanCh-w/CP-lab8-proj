package com.company.ui;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class BuildingPanel {
    private JPanel panel = new JPanel();
    private List<JLabel> lifts;
    private List<JLabel> floors;
    private List<JLabel> persons;
    private Vector<Vector<Integer>> passanger;

    private final int windowWidth = 930;
    private final int windowHeight = 720;
    private final int liftMargin = 150;
    private final int liftWidth = 50;
    private final int liftHeight = 80;
    private final int floorHeight = 130;

    public BuildingPanel() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(windowWidth, windowHeight));
    }

    public JPanel getComponent() {
        return panel;
    }

    public void CreateBuilding(int floorCount, int liftCount) {
        for(int i = 0; i < liftCount; ++i) {
            var lift = new JLabel() {
                public void paintComponent(Graphics g) {
                    g.drawImage(new ImageIcon("./assets/lift.jpg").getImage(), 0, 0, null);
                    super.paintComponent(g);
                }
            };
            lift.setBounds(windowWidth-i*liftMargin-20, windowHeight-20, liftWidth, liftHeight);
            lifts.get(i);
        };
        int width = liftCount*liftMargin + 50;
        for(int i = 0; i < floorCount; ++i) {
            var floor = new JLabel();
            floor.setBackground(Color.black);
            floor.setOpaque(true);
            floor.setBounds(windowWidth-width, windowHeight-i*floorHeight-20, width, 5);
        };
        passanger = new Vector<Vector<Integer>>(floorCount);
        for(int i = 0; i < floorCount; ++i) {
            passanger.set(i, new Vector<Integer>(liftCount));
        }
    }


}
