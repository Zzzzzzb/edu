package com.zhengzb.edu.mybatis.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class CustomMessageFormattingStrategy implements MessageFormattingStrategy {
    /**
     * Formats a log message for the logging module
     *
     * @param connectionId the id of the connection
     * @param now          the current ime expressing in milliseconds
     * @param elapsed      the time in milliseconds that the operation took to complete
     * @param category     the category of the operation
     * @param prepared     the SQL statement with all bind variables replaced with actual values
     * @param sql          the sql statement executed
     * @param url          the database url where the sql statement executed
     * @return the formatted log message
     */
    @Override
    public String formatMessage(int connectionId,
            String now,
            long elapsed,
            String category,
            String prepared,
            String sql,
            String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n┏━━━━━━━━━━━━━━━━━━━━数据库执行信息━━━━━━━━━━━━━━━━━━━━┓\n");
        stringBuilder.append("┃ 执行时间：").append(now).append("\n");
        stringBuilder.append("┃ 耗时：").append(elapsed).append("ms\n");
        stringBuilder.append("┃ 事物：").append(category).append("\n");
        stringBuilder.append("┃ 连接ID：").append(connectionId).append("\n");
        stringBuilder.append("┃ 连接URL：").append(url).append("\n");
        // SQLUtils.format(prepared, JdbcConstants.MYSQL)
        stringBuilder.append("┃ 预编译SQL：").append(prepared).append("\n");
        stringBuilder.append("┃ 完整SQL：").append(sql).append("\n");
        stringBuilder.append("┗━━━━━━━━━━━━━━━━━━━━数据库执行信息━━━━━━━━━━━━━━━━━━━━┛\n");
        return stringBuilder.toString();
    }
}
