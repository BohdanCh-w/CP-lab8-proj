package com.company.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger extends BaseLogger{
    public ConsoleLogger(Logger next) {
        super(next);
    }

    @Override
    public void Log(String data, LogLvl level) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(dtf.format(now) + " " + data + " Lvl: " + level);
        if (level != LogLvl.LOG_CONSOLE) {
            next.Log(data, level);
        }
    }
}
