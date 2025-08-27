package medium.loggingsystemlld.handler;

import medium.loggingsystemlld.appender.LogAppender;
import medium.loggingsystemlld.entity.LogLevel;
import medium.loggingsystemlld.entity.LogMessage;

public class ErrorLogHandler extends LogHandler{

    public ErrorLogHandler(LogAppender logAppender) {
        super(logAppender);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(logLevel.equals(LogLevel.ERROR)) {
            LogMessage logMessage = new LogMessage(message, logLevel);
            logAppender.append("[ERROR]: " + logMessage.toString());
        }else{
            super.log(logLevel, message);
        }
    }
}
