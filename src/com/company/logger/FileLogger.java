package com.company.logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileLogger extends BaseLogger{
    private String filePath;

    public FileLogger(Logger next, String filePath) {
        super(next);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void Log(String data, LogLvl level) {
        if(level == LogLvl.LOG_FILE) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                File log = new File(filePath);
                if (!log.exists()){
                    System.out.println("Can`t find log file, creating a new one.");
                    log.createNewFile();
                }

                PrintWriter out = new PrintWriter(new FileWriter(log, true));
                out.append(dtf.format(now)).append(" ").append(data).append(" Lvl: ").append(String.valueOf(level));
                out.close();

            } catch (IOException ex) {
                System.out.println("File writing exception: " + ex.getMessage());
                System.out.println("Stack trace: " + Arrays.toString(ex.getStackTrace()));
            } catch (Exception ex) {
                System.out.println("Unknown exception: " + ex.getMessage());
                System.out.println("Stack trace: " + Arrays.toString(ex.getStackTrace()));
            }
        }
        else{
            next.Log(data, level);
        }
    }
}
