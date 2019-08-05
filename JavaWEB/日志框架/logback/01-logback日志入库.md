## logback 日志入库

#### logback 提供了一个抽象类在日志输出时被回调

```java
public class MongoDBAppenderBase extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) { }
}
```

##### ILoggingEvent 一条日志输出就是一个日志事件。

| 方法                  | 作用     |
| --------------------- | -------- |
| getFormattedMessage() | 日志内容 |
| getLevel().levelStr   | 日志级别 |

#### 在 logback 配置文件

```xml
<appender name="dbLog" class="package.MongoDBAppenderBase"></appender>

<root level="info">
    <appender-ref ref="consoleAppender"/>
    <appender-ref ref="dbLog"/>
</root>
```

---

