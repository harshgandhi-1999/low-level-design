package medium.loggingsystemlld.handler;

import medium.loggingsystemlld.appender.LogAppender;
import medium.loggingsystemlld.entity.LogLevel;
import medium.loggingsystemlld.entity.LogMessage;

public class DebugLogHandler extends LogHandler{

    public DebugLogHandler(LogAppender logAppender) {
        super(logAppender);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(logLevel.equals(LogLevel.DEBUG)){
            LogMessage logMessage = new LogMessage(message, logLevel);
            logAppender.append("[DEBUG]: " + logMessage.toString());
        }else{
            super.log(logLevel, message);
        }
    }
}
