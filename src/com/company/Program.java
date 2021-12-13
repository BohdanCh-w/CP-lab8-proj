package com.company;

import com.company.logger.ConsoleLogger;
import com.company.logger.ErrorLogger;
import com.company.logger.FileLogger;
import com.company.logger.Logger;
public class Program {
    public static Logger logger;

    public static void main(String[] args) {
        logger = new ConsoleLogger(new FileLogger(new ErrorLogger(null), "logger.txt"));
        Passanger.InitializeId();
        Emulation.getInstance();
        Emulation.getInstance();
    }
}
