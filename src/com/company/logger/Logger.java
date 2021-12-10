package com.company.logger;

public interface Logger {
    void SetNext(Logger logger);
    void Log(String data, LogLvl level);
}
