package com.company.ui;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BuildingPanel {
    private JPanel root = new JPanel();
    private List<JLabel> lifts;
    private List<JLabel> floors;
    private Vector<Vector<List<JLabel>>> passangers;

    private final int windowWidth = 930;
    private final int windowHeight = 720;
    private final int liftMargin = 150;
    private final int liftWidth = 50;
    private final int liftHeight = 80;
    private final int floorHeight = 130;
    private final int personWidth = 15;
    private final int personHeight = 60;
    private final int personMargin = 19;

    public BuildingPanel() {
        root.setLayout(new GridBagLayout());
        root.setPreferredSize(new Dimension(windowWidth, windowHeight));
    }

    public JPanel getComponent() {
        return root;
    }

    public void CreateBuilding(int floorCount, int liftCount) {
        for(int i = 0; i < liftCount; ++i) {
            lifts.add(new JLabel() {
                public void paintComponent(Graphics g) {
                    g.drawImage(new ImageIcon("./assets/lift.jpg").getImage(), 0, 0, null);
                    super.paintComponent(g);
                }
            });
            MoveLift(i, 0);
        };
        int width = liftCount*liftMargin + 50;
        for(int i = 0; i < floorCount; ++i) {
            var floor = new JLabel();
            floor.setBackground(Color.black);
            floor.setOpaque(true);
            floor.setBounds(windowWidth-width, windowHeight-i*floorHeight-20, width, 5);
            floors.add(floor);
        };

        passangers = new Vector<Vector<List<JLabel>>>(floorCount);
        for(int i = 0; i < floorCount; ++i) {
            passangers.set(i, new Vector<List<JLabel>>(liftCount));
            for(int j = 0; j < liftCount; ++j) {
                passangers.get(i).set(j, new ArrayList<JLabel>());
            }
        }
    }

    public void MoveLift(int index, int floor) {
        lifts.get(index).setBounds(
            windowWidth-index*liftMargin-20, windowHeight-20-floorHeight*floor-liftHeight, 
            liftWidth, liftHeight
        );
    }

    public void SetPassangerNumber(int floor, int lift, int passangerNumber) {
        var queue = passangers.get(floor).get(lift);
        if(queue.size() > passangerNumber) {
            for(int i = queue.size()-1; i > passangerNumber; --i) {
                root.remove(queue.get(i));
                root.revalidate();
                queue.remove(i);
            }
        } else if(queue.size() < passangerNumber) {
            for(int i = queue.size(); i < passangerNumber; ++i) {
                var person = new JLabel() {
                    public void paintComponent(Graphics g) {
                        g.drawImage(new ImageIcon("./assets/person.png").getImage(), 0, 0, null);
                        super.paintComponent(g);
                    }
                };
                queue.add(person);
                root.add(person);
                person.setBounds(
                    windowWidth-lift*liftMargin-20-personMargin,
                    windowHeight-20-floorHeight*floor-personHeight,
                    personWidth, personHeight
                );
            }
        }
    }
}
