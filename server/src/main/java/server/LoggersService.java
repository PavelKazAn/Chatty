package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggersService {
    Logger logger;
    private LogManager manager = LogManager.getLogManager();

    public LoggersService(Class log) {
        try {
            logger = Logger.getLogger(log.getName());
            manager.readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(String message){
        logger.log(Level.INFO,message);
    }

    public void sendWarning(String message){
        logger.log(Level.WARNING,message);
    }


}
