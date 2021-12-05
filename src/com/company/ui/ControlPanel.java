package com.company.ui;

import com.company.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ControlPanel {
    private JPanel panel = new JPanel();
    private JLabel lLift;
    private JLabel lFloor;
    private JLabel lSpwnTime;
    private JTextField eLift;
    private JTextField eFloor;
    private JTextField eSpwnTime;
    private JButton bLiftConfig;
    private JButton bBuild;
    private JButton bStart;
    private JFrame configFrame = null;

    private List<Lift> lifts;

    public ControlPanel() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 720));

        createComponents();
        drawComponents();
    }

    private void createComponents() {
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        var lFont = new Font("TimesRoman", Font.PLAIN, 30);
        var bFont = new Font("TimesRoman", Font.PLAIN, 20);

        lLift = new JLabel("Lifts");
        lLift.setPreferredSize(new Dimension(30, 35));
        lLift.setFont(lFont);        
        eLift = new JTextField();
        eLift.setPreferredSize(new Dimension(30, 35));
        eLift.setFont(lFont);

        lFloor = new JLabel("Floors");
        lLift.setPreferredSize(new Dimension(30, 35));
        lFloor.setFont(lFont);
        eFloor = new JTextField();
        eFloor.setPreferredSize(new Dimension(30, 35));
        eFloor.setFont(lFont);

        lSpwnTime = new JLabel("Spawn Time");
        lLift.setPreferredSize(new Dimension(30, 35));
        lSpwnTime.setFont(lFont);
        eSpwnTime = new JTextField();
        eSpwnTime.setPreferredSize(new Dimension(30, 35));
        eSpwnTime.setFont(lFont);

        bLiftConfig = new JButton("Lift Config");
        bLiftConfig.setPreferredSize(new Dimension(30, 35));
        bLiftConfig.setFont(bFont);
        bLiftConfig.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLiftConfig();
            }
        });
        
        bBuild = new JButton("Build");
        bBuild.setPreferredSize(new Dimension(30, 35));
        bBuild.setFont(bFont);
        
        bStart = new JButton("Start");
        bStart.setPreferredSize(new Dimension(30, 35));
        bStart.setFont(bFont);
    }

    private void drawComponents() {
        panel.add(lLift, createGbc(0, 0));        
        panel.add(eLift, createGbc(1, 0));

        panel.add(lFloor, createGbc(0, 1));
        panel.add(eFloor, createGbc(1, 1));

        panel.add(lSpwnTime, createGbc(0, 2));
        panel.add(eSpwnTime, createGbc(1, 2));
        
        panel.add(bLiftConfig, createGbc(1, 3));

        panel.add(bBuild, createGbc(1, 4));

        var gbc = createGbc(1, 5);
        gbc.weighty = 20;
        panel.add(bStart, gbc);
    }

    private GridBagConstraints createGbc(int x, int y) {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;

      gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
      gbc.fill = (x == 0) ? GridBagConstraints.BOTH
            : GridBagConstraints.HORIZONTAL;

      gbc.weightx = (x == 0) ? 0.1 : 1.0;
      gbc.weighty = 1.0;
      return gbc;
   }

    private void openLiftConfig() {
        if(configFrame != null) {
            configFrame.dispose();
        }
        configFrame = new LiftConfigFrame(lifts).getComponent();
    }

    public JPanel getComponent() {
        return panel;
    }

    public Integer getLiftsCount() {
        return Integer.parseInt(eLift.getText());
    }

    public Integer getFloorsCount() {
        return Integer.parseInt(eFloor.getText());
    }

    public Integer getPassangerSpawnTime() {
        return Integer.parseInt(eSpwnTime.getText());
    }

    public void SetLiftListForConfig(List<Lift> lifts) {
        this.lifts = lifts;
    }

    public void addBuildActionListener(ActionListener listener) {
        bBuild.addActionListener(listener);
    }

    public void addStartActionListener(ActionListener listener) {
        bStart.addActionListener(listener);
    }
}
