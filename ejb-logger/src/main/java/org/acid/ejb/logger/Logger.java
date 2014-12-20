package org.acid.ejb.logger;

import javax.ejb.Remote;

@Remote
public interface Logger {

    void setLevel(Level level);
    
    void debug(String type, String msg);

    void info(String type, String msg);

    void warn(String type, String msg);

    void error(String type, String msg);

    void error(String type, String msg, Exception ex);

    void fatal(String type, String msg);

    void fatal(String type, String msg, Exception ex);
}
