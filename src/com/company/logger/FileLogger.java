package com.company.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(dtf.format(now) + " " + data + " Lvl: " + level);
                writer.close();
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
