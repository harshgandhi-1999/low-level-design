package medium.loggingsystemlld.handler;

import medium.loggingsystemlld.appender.LogAppender;
import medium.loggingsystemlld.entity.LogLevel;

public abstract class LogHandler {

    protected LogHandler nextLogHandler;
    protected final LogAppender logAppender;

    public LogHandler(LogAppender logAppender) {
        this.logAppender = logAppender;
    }

    public void setNextLogHandler(LogHandler nextLogHandler) {
        this.nextLogHandler = nextLogHandler;
    }

    public void log(LogLevel logLevel, String message){
        if(nextLogHandler != null){
            nextLogHandler.log(logLevel, message);
        }
    }
}
