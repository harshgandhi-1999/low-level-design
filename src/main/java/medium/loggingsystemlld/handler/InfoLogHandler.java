package medium.loggingsystemlld.handler;

import medium.loggingsystemlld.appender.LogAppender;
import medium.loggingsystemlld.entity.LogLevel;
import medium.loggingsystemlld.entity.LogMessage;

public class InfoLogHandler extends LogHandler {

    public InfoLogHandler(LogAppender logAppender) {
        super(logAppender);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(logLevel.equals(LogLevel.INFO)){
            LogMessage logMessage = new LogMessage(message, logLevel);
            logAppender.append("[INFO]: " + logMessage.toString());
        }else{
            super.log(logLevel, message);
        }
    }
}
