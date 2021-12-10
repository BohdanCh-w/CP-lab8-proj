package com.company;
import java.awt.event.*;
import javax.swing.JButton;

import com.company.logger.ConsoleLogger;
import com.company.logger.ErrorLogger;
import com.company.logger.FileLogger;
import com.company.logger.Logger;
import com.company.ui.UI;

import com.company.ui.UI;

public class Program {
    public static Logger logger;

    public static void main(String[] args) {
        logger = new ConsoleLogger(new FileLogger(new ErrorLogger(null), "logger.txt"));
        Passanger.InitializeId();
        Emulation.getInstance();
        Emulation.getInstance();
    }
}
