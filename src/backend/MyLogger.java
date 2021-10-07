package backend;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
  private static Logger logger = Logger.getLogger("MyLog");


  public static Logger getLogger() {
    return logger;
  }

  public static void init() {
    FileHandler fh;
    try {
      fh = new FileHandler("log/server.log");
      logger.addHandler(fh);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);

      logger.info("Logger Initialized");
    } catch (SecurityException | IOException e) {
      logger.log(Level.WARNING, null, e);
    }
  }
}
