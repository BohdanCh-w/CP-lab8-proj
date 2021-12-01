package com.company.logger;

public abstract class BaseLogger implements Logger{
    protected Logger next;

    @Override
    public void SetNext(Logger logger) {
        this.next = logger;
    }

    @Override
    public void Log(String data, LogLvl level) {

    }
}
