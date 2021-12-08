package com.company.logger;

public abstract class BaseLogger implements Logger{
    protected Logger next;

    public void SetNext(Logger logger) {
        this.next = logger;
    }

    @Override
    public abstract void Log(String data, LogLvl level);
}
