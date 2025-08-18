package medium.loggingsystemlld.appender;

import medium.loggingsystemlld.entity.LogMessage;

public class ConsoleAppender implements LogAppender{

    @Override
    public void append(String message) {
        System.out.println(message);
    }
}
