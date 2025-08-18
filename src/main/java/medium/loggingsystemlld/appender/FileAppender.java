package medium.loggingsystemlld.appender;

import medium.loggingsystemlld.entity.LogMessage;

public class FileAppender implements LogAppender{

    @Override
    public void append(String message) {
        System.out.println("Appended to file: " + message);
    }
}
