package com.company;
import java.awt.event.*;
import javax.swing.JButton;

import com.company.ui.UI;

public class Program {
    public static void startStop(){
        isActive = !isActive;
    }

    public static boolean isActive = false;

    public static void main(String[] args) {
        var ui = new UI();

        ui.addOnStart(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startStop();
                if(isActive) {
                    ((JButton)(e.getSource())).setText("Stop");
                    System.out.println("Started");
                } else {
                    ((JButton)(e.getSource())).setText("Start");
                    System.out.println("Stopped");
                }
            }
        });
        
        ui.addOnClose(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Closed");
            }
        });

        try{Thread.sleep(200);} catch(Exception e) {}
        ui.Building().CreateBuilding(7, 6);
        ui.Building().SetPassangerNumber(2, 3, 5);
        try{Thread.sleep(800);} catch(Exception e) {}
        ui.Building().SetPassangerNumber(2, 3, 7);
        try{Thread.sleep(800);} catch(Exception e) {}
        ui.Building().SetPassangerNumber(2, 3, 2);
        ui.Building().MoveLift(3, 2);
    }
}
