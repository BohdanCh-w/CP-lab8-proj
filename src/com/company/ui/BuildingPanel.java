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

import static com.company.Program.logger;

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
        root.setBackground(Color.decode("#fff4e6"));
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

        logger.Log(String.format("UI resources loaded"), LogLvl.LOG_CONSOLE);
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
        logger.Log("UI building created", LogLvl.LOG_CONSOLE);
    }

    public void MoveLift(int index, int floor) {
        lifts.get(index).setBounds(
            windowWidth-index*liftMargin-15-liftWidth, windowHeight-7-floorHeight*floor-liftHeight, 
            liftWidth, liftHeight
        );
    }

    public void AnimateLift(int index, int floor, int duration) {
        var lift = lifts.get(index);
        var box = lift.getBounds();

        int start = box.y;
        int end = windowHeight-7-floorHeight*floor-liftHeight;
        int stepsCount = duration / 10;
        double step = (end - start + 0.0) / stepsCount;
        double height = start;

        for(int i = 0; i < stepsCount; ++i) {
            try {Thread.sleep(10); } catch(Exception e) {};
            height += step;
            box.y = (int)height;
            lift.setBounds(box);
        }
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

    public synchronized void changePassangerNumber(int floor, int lift, int passangerNumber) {
        var queue = passangers.get(floor).get(lift);
        if(passangerNumber < 0) {
            for(int i = 0; i > passangerNumber; --i) {
                root.remove(queue.get(queue.size() - 1));
                root.repaint();
                queue.remove(queue.size() - 1);
            }
        } else {
            for(int i = 0; i < passangerNumber; ++i) {
                var person = new JLabel(personImg);
                queue.add(person);
                root.add(person);
                person.setBounds(
                    windowWidth-lift*liftMargin-15-liftWidth-personWidth-(queue.size()-1)*personMargin,
                    windowHeight-10-floorHeight*floor-personHeight,
                    personWidth, personHeight
                );
            }
        }
    }
}
