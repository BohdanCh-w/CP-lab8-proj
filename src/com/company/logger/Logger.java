package com.company.logger;

public interface Logger {
    public void SetNext(Logger logger);
    public void Log(String data, LogLvl level);
}
