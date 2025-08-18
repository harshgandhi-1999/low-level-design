package medium.loggingsystemlld;

import medium.loggingsystemlld.appender.ConsoleAppender;
import medium.loggingsystemlld.appender.LogAppender;
import medium.loggingsystemlld.entity.LogLevel;
import medium.loggingsystemlld.handler.DebugLogHandler;
import medium.loggingsystemlld.handler.ErrorLogHandler;
import medium.loggingsystemlld.handler.InfoLogHandler;
import medium.loggingsystemlld.handler.LogHandler;

public class Main {

    public static void main(String[] args) {
//  Rules of the System:
//  Setup:
//
//  - The system supports multiple log levels (DEBUG, INFO, WARNING, ERROR, FATAL)
//  - Logs have attributes like timestamp, level, message, and context data
//  - The system can route logs to different destinations based on level and content
//
//  Operation:
//
//  - Applications create log messages with a specified severity level
//  - Each log passes through a chain of handlers that decide whether to process it
//  - Handlers can filter, format, and dispatch logs to various destinations
//
//  - The system can be configured to customize logging behavior at runtime
//
//  Features:
//
//  - Level-based filtering to control which logs are processed
//
//  - Formatting options for different output destinations
//
//  - Support for multiple output destinations (console, file, network, etc.)
//
//  - Context enrichment to add additional information to log entries

        ConsoleAppender consoleAppender = new ConsoleAppender();
        LogHandler logHandler = getLogger(consoleAppender);

        logHandler.log(LogLevel.INFO, "This is debug log");
        logHandler.log(LogLevel.DEBUG, "This is debug log");
        logHandler.log(LogLevel.ERROR, "This is error log");

    }

    private static LogHandler getLogger(LogAppender logAppender){
        LogHandler infoLogHandler = new InfoLogHandler(logAppender);
        LogHandler debugLogHandler = new DebugLogHandler(logAppender);
        LogHandler errorLogHandler = new ErrorLogHandler(logAppender);

        debugLogHandler.setNextLogHandler(errorLogHandler);
        infoLogHandler.setNextLogHandler(debugLogHandler);

        return infoLogHandler;
    }
}
