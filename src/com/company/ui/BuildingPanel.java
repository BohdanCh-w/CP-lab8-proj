package com.company.ui;

import com.company.logger.LogLvl;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.image.BufferedImage;

public class BuildingPanel {
    private JPanel root = new JPanel();
    private List<JLabel> lifts;
    private List<JLabel> floors;
    private Vector<Vector<List<JLabel>>> passangers;

    private ImageIcon liftImg;
    private ImageIcon personImg;

    private final int windowWidth = 930;
    private final int windowHeight = 720;
    private final int liftMargin = 150;
    private final int liftWidth = 75;
    private final int liftHeight = 80;
    private final int floorHeight = 90;
    private final int personWidth = 20;
    private final int personHeight = 53;
    private final int personMargin = 23;

    private final String liftImgPath = "src\\com\\company\\ui\\assets\\lift.png";
    private final String personImgPath = "src\\com\\company\\ui\\assets\\person.png";

    public BuildingPanel() {
        root.setLayout(new GridBagLayout());
        root.setPreferredSize(new Dimension(windowWidth, windowHeight));

        loadResourses();
    }

    private void loadResourses() {
        BufferedImage liftBuffImg = null;
        try {
            liftBuffImg = ImageIO.read(new File(liftImgPath));
        } catch (IOException e) {
            System.getProperty("user.dir");
            e.printStackTrace();
        }
        Image liftDimg = liftBuffImg.getScaledInstance(liftWidth, liftHeight, Image.SCALE_SMOOTH);
        liftImg = new ImageIcon(liftDimg);

        BufferedImage personBuffImg = null;
        try {
            personBuffImg = ImageIO.read(new File(personImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image personDimg = personBuffImg.getScaledInstance(personWidth, personHeight, Image.SCALE_SMOOTH);
        personImg = new ImageIcon(personDimg);

        // TODO: Console logger "UI resourses loaded"
    }

    public JPanel getComponent() {
        return root;
    }

    public void CreateBuilding(int floorCount, int liftCount) {
        root.removeAll();
        root.repaint();
        lifts = new ArrayList<JLabel>();
        for(int i = 0; i < liftCount; ++i) {
            var lift = new JLabel(liftImg);
            lifts.add(lift);
            root.add(lift);
            MoveLift(i, 0);
        };
        floors = new ArrayList<JLabel>();
        int width = liftCount*liftMargin + 50;
        for(int i = 0; i < floorCount; ++i) {
            var floor = new JLabel();
            floor.setBackground(Color.black);
            floor.setOpaque(true);
            floors.add(floor);
            root.add(floor);
            floor.setBounds(windowWidth-width, windowHeight-i*floorHeight-10, width, 3);
        };

        passangers = new Vector<Vector<List<JLabel>>>();
        passangers.setSize(floorCount);
        for(int i = 0; i < floorCount; ++i) {
            passangers.set(i, new Vector<List<JLabel>>(liftCount));
            passangers.get(i).setSize(liftCount);
            for(int j = 0; j < liftCount; ++j) {
                passangers.get(i).set(j, new ArrayList<JLabel>());
            }
        }
        // TODO: Console logger "UI building created"
    }

    public void MoveLift(int index, int floor) {
        lifts.get(index).setBounds(
            windowWidth-index*liftMargin-15-liftWidth, windowHeight-7-floorHeight*floor-liftHeight, 
            liftWidth, liftHeight
        );
    }

    public void SetPassangerNumber(int floor, int lift, int passangerNumber) {
        var queue = passangers.get(floor).get(lift);
        if(queue.size() > passangerNumber) {
            for(int i = queue.size()-1; i >= passangerNumber; --i) {
                root.remove(queue.get(i));
                root.repaint();
                queue.remove(i);
            }
        } else if(queue.size() < passangerNumber) {
            for(int i = queue.size(); i < passangerNumber; ++i) {
                var person = new JLabel(personImg);
                queue.add(person);
                root.add(person);
                person.setBounds(
                    windowWidth-lift*liftMargin-15-liftWidth-personWidth-i*personMargin,
                    windowHeight-10-floorHeight*floor-personHeight,
                    personWidth, personHeight
                );
            }
        }
    }
}
