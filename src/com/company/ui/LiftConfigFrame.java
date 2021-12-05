package com.company.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.List;
import com.company.*;
import com.company.strategy.*;

public class LiftConfigFrame {
    private JFrame root = new JFrame();
    private JButton bPrev;
    private JButton bNext;
    private JLabel lCurr;
    private JLabel lStrategy;
    private JRadioButton rbNoMore;
    private JRadioButton rbTakeMore;
    private JLabel lWeight;
    private JTextField eWeight;
    private JLabel lPassangers;
    private JTextField ePassangers;
    private JLabel lSpeed;
    private JTextField eSpeed;
    private JButton bSave;

    private List<Lift> lifts;
    private int current = 0;
    
    public LiftConfigFrame(List<Lift> lifts) {
        this.lifts = lifts;
        
        root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        root.setSize(500,350);
        root.setLayout(null);

        createComponents();
        drawComponents();

        root.setVisible(true);
        root.setResizable(false);
    }

    private void createComponents() {
        var primaryFont = new Font("TimesRoman", Font.PLAIN, 20);
        var secondaryFont = new Font("TimesRoman", Font.PLAIN, 16);

        bPrev = new JButton("Prev");
        bPrev.setFont(secondaryFont);
        bPrev.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });
        bPrev.setEnabled(false);
        bNext = new JButton("Next");
        bNext.setFont(secondaryFont);
        bNext.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        lCurr = new JLabel("Lift Configuration");
        lCurr.setFont(new Font("TimesRoman", Font.PLAIN, 26));

        lStrategy = new JLabel("Lift Strategy");
        lStrategy.setFont(primaryFont);
        rbNoMore = new JRadioButton("No More Passangers");
        rbNoMore.setFont(secondaryFont);
        rbTakeMore = new JRadioButton("Take More Passangers");
        rbTakeMore.setFont(secondaryFont);
        var rbGroup = new ButtonGroup();
        rbGroup.add(rbNoMore);
        rbGroup.add(rbTakeMore);

        lWeight = new JLabel("Max Weight");
        lWeight.setFont(primaryFont);
        eWeight = new JTextField();
        eWeight.setFont(secondaryFont);

        lPassangers = new JLabel("Max Passangers Count");
        lPassangers.setFont(primaryFont);
        ePassangers = new JTextField();
        ePassangers.setFont(secondaryFont);

        lSpeed = new JLabel("Lift Speed");
        lSpeed.setFont(primaryFont);
        eSpeed = new JTextField();
        eSpeed.setFont(secondaryFont);

        bSave = new JButton("Save");
        bSave.setFont(secondaryFont);
        bSave.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    private void drawComponents() {
        root.add(bPrev);
        bPrev.setBounds(15, 15, 85, 35);
        root.add(lCurr);
        lCurr.setBounds(120, 15, 270, 35);
        root.add(bNext);
        bNext.setBounds(400, 15, 85, 35);

        root.add(lStrategy);
        lStrategy.setBounds(30, 90, 110, 45);
        root.add(rbNoMore);
        rbNoMore.setBounds(30, 130, 200, 30);
        root.add(rbTakeMore);
        rbTakeMore.setBounds(30, 155, 200, 30);

        root.add(lWeight);
        lWeight.setBounds(30, 210, 130, 30);
        root.add(eWeight);
        eWeight.setBounds(250, 210, 130, 30);
        
        root.add(lPassangers);
        lPassangers.setBounds(30, 240, 210, 30);
        root.add(ePassangers);
        ePassangers.setBounds(250, 240, 130, 30);

        root.add(lSpeed);
        lSpeed.setBounds(30, 270, 130, 30);
        root.add(eSpeed);
        eSpeed.setBounds(250, 270, 130, 30);
        
        root.add(bSave);
        bSave.setBounds(390, 270, 85, 30);
    }

    public JFrame getComponent() {
        return root;
    }

    private void next() {
        current++;
        update();
        if(current == lifts.size()-1) {
            bNext.setEnabled(false);
        }
        bPrev.setEnabled(true);
    }
    
    private void previous() {
        current--;
        update();
        if(current == 0) {
            bPrev.setEnabled(false);
        }
        bNext.setEnabled(true);
    }
    
    private void update() {
        lCurr.setText(String.format("Lift %d Configuration", current+1));
        var lift = lifts.get(current);
        if(lift.getStrategy() instanceof NoNewStrategy) {
            rbNoMore.setSelected(true);
        } else {
            rbTakeMore.setSelected(true);
        }
        eWeight.setText(lift.getMaxWeight().toString());
        ePassangers.setText(lift.getMaxPeopleCount().toString());
        eSpeed.setText(lift.getSpeed().toString());
    }

    private void save() {
        var lift = lifts.get(current);
        if(rbNoMore.isSelected()) {
            lift.setStrategy(new NoNewStrategy());
        } else {
            lift.setStrategy(new TakeNewStrategy());
        }
        lift.setMaxWeight(Integer.parseInt(eWeight.getText()));
        lift.setMaxPeopleCount(Integer.parseInt(ePassangers.getText()));
        lift.setSpeed(Integer.parseInt(eSpeed.getText()));
    }
}
