package com.company.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger extends BaseLogger{
    @Override
    public void Log(String data, LogLvl level) {
        if (level == LogLvl.LOG_CONSOLE) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            System.out.println(dtf.format(now) + " " + data + " Lvl: " + level);
        }
        else{
            next.Log(data, level);
        }
    }
}
