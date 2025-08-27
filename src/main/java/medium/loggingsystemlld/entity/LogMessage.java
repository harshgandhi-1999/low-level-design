package medium.loggingsystemlld.entity;

public class LogMessage{
    private final String content;
    private final LogLevel logLevel;
    private final long timestamp;

    public LogMessage(String content, LogLevel logLevel) {
        this.content = content;
        this.logLevel = logLevel;
        this.timestamp = System.currentTimeMillis();
    }

    public String getContent() {
        return content;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "content='" + content + '\'' +
                ", logLevel=" + logLevel +
                ", timestamp=" + timestamp +
                '}';
    }
}
