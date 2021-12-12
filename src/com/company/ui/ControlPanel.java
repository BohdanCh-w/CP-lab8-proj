package com.company.ui;

import com.company.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
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

    public static Color fontColor = Color.WHITE;
    public static Color backColor = Color.decode("#51997c");
    public static Color backColorHover = Color.decode("#256e51");

    public ControlPanel() {
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 720));
        panel.setBackground(Color.decode("#ffc77d"));

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
        eLift.setBorder(new RoundedBorder(20));

        lFloor = new JLabel("Floors");
        lLift.setPreferredSize(new Dimension(30, 35));
        lFloor.setFont(lFont);
        eFloor = new JTextField();
        eFloor.setPreferredSize(new Dimension(30, 35));
        eFloor.setFont(lFont);
        eFloor.setBorder(new RoundedBorder(20));

        lSpwnTime = new JLabel("Spawn Time");
        lLift.setPreferredSize(new Dimension(30, 35));
        lSpwnTime.setFont(lFont);
        eSpwnTime = new JTextField();
        eSpwnTime.setPreferredSize(new Dimension(30, 35));
        eSpwnTime.setFont(lFont);
        eSpwnTime.setBorder(new RoundedBorder(20));

        bLiftConfig = new JButton("Lift Config");
        bLiftConfig.setPreferredSize(new Dimension(30, 35));
        bLiftConfig.setFont(bFont);
        bLiftConfig.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLiftConfig();
            }
        });
        bLiftConfig.addMouseListener(new Hover(bLiftConfig));
        bLiftConfig.setBackground(backColor);
        bLiftConfig.setForeground(fontColor);
        bLiftConfig.setBorder(new RoundedBorder(20));
        
        bBuild = new JButton("Build");
        bBuild.setPreferredSize(new Dimension(30, 35));
        bBuild.setFont(bFont);
        bBuild.addMouseListener(new Hover(bBuild));
        bBuild.setBackground(backColor);
        bBuild.setForeground(fontColor);
        bBuild.setBorder(new RoundedBorder(20));
        
        bStart = new JButton("Start");
        bStart.setPreferredSize(new Dimension(30, 35));
        bStart.setFont(bFont);
        bStart.addMouseListener(new Hover(bStart));
        bStart.setBackground(backColor);
        bStart.setForeground(fontColor);
        bStart.setBorder(new RoundedBorder(20));
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

    public static class Hover extends MouseAdapter{
        private JButton button;

        public Hover(JButton button) {
            this.button = button;
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(backColorHover);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(backColor);
        }
    }

    public static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius+1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
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
