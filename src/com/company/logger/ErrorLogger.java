package com.company.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger extends BaseLogger{
    @Override
    public void Log(String data, LogLvl level) {
        if(level == LogLvl.LOG_ERROR){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("Pizda.txt"));
                writer.write("Пизда рулю, бочок потік, тікай з села");
            }
            catch (Exception ex){
                System.out.println("Ahahahahah cum :)");
            }

            System.exit(1);
        }
    }
}
