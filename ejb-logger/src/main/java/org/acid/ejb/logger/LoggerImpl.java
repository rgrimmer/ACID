package org.acid.ejb.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateful;

@Stateful(mappedName = "logger")
public class LoggerImpl implements Logger {

    public static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Level level;

    public LoggerImpl() {
        level = Level.ALL;
    }

    @Override
    public void setLevel(Level level) {
        info("EJB-logger", "Logging level set to " + level.toString());
        this.level = level;
    }

    @Override
    public void debug(String type, String msg) {
        log(Level.DEBUG, type, msg, Color.BLUE);
    }

    @Override
    public void info(String type, String msg) {
        log(Level.INFO, type, msg, Color.NORMAL);
    }

    @Override
    public void warn(String type, String msg) {
        log(Level.WARN, type, msg, Color.YELLOW);
    }

    @Override
    public void error(String type, String msg) {
        error(type, msg, null);
    }

    @Override
    public void error(String type, String msg, Exception ex) {
        log(Level.ERROR, type, msg, Color.RED, ex);
    }

    @Override
    public void fatal(String type, String msg) {
        fatal(type, msg, null);
    }

    @Override
    public void fatal(String type, String msg, Exception ex) {
        log(Level.FATAL, type, msg, Color.RED, ex);
    }

    private void log(Level level, String type, String msg, Color color) {
        log(level, type, msg, color, null);
    }

    private void log(Level level, String type, String msg, Color color, Exception ex) {
        if (level.getValue() >= this.level.getValue()) {
            System.out.println(String.format("%s[%s][%s][%s]%s %s%s",
                                             color.toString(),
                                             format.format(new Date()),
                                             level.toString(),
                                             type,
                                             ((ex == null) ? "" : "[" + ex.getClass().getSimpleName() + "(" + ex.getMessage() + ")]"),
                                             msg,
                                             Color.NORMAL.toString()));
        }
    }

}
