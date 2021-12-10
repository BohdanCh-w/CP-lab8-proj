package com.company.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ErrorLogger extends BaseLogger{
    public ErrorLogger(Logger next) {
        super(next);
    }

    @Override
    public void Log(String data, LogLvl level) {
        if(level == LogLvl.LOG_ERROR){
            System.exit(1);
        }
    }
}
