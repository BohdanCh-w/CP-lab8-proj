package com.company.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileLogger extends BaseLogger{
    private String filePath;

    @Override
    public void Log(String data, LogLvl level) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + " " + data + " Lvl: " + level);
        } catch(IOException ex){
            System.out.println("File writing exception: " + ex.getMessage());
            System.out.println("Stack trace: " + Arrays.toString(ex.getStackTrace()));
        } catch (Exception ex){
            System.out.println("Unknown exception: " + ex.getMessage());
            System.out.println("Stack trace: " + Arrays.toString(ex.getStackTrace()));
        }
    }
}
