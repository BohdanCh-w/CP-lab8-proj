package com.company.logger;

public abstract class BaseLogger implements Logger{
    public BaseLogger(Logger next) {
        this.next = next;
    }

    protected Logger next;

    public void SetNext(Logger logger) {
        this.next = logger;
    }

    @Override
    public abstract void Log(String data, LogLvl level);
}
