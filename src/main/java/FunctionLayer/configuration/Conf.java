/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
//import loggingdemo.LoggingDemo;

/**
 *
 * @author tha
 */
public class Conf {

//    public static final boolean PRODUCTION = false;
//    public static final String LOGFILEPATH = "/FogExamProjct/FogExamProject.log"; // "/var/log/tomcat8/FogExamProject.log";
//    public static final String LOG_FILE_PATH_DEVELOP = "/FogExamProjct/FogExamProject.log";
//    private static Logger logger;

//    public static Logger getLogger() {
//        if (logger == null) {
//            logger = Logger.getLogger("");
//            if (FunctionLayer.configuration.Conf.PRODUCTION) {
//                try {
//                    FileHandler handler = new FileHandler(FunctionLayer.configuration.Conf.LOGFILEPATH);
//                    handler.setFormatter(new VerySimpleFormatter());
//                    logger.addHandler(handler);
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            } else {
//                try {
//                    FileHandler handler = new FileHandler("FogExamProject-log.%u.%g.txt"); // see: http://tutorials.jenkov.com/java-logging/handlers.html
//                    handler.setFormatter(new VerySimpleFormatter());
//                    logger.addHandler(handler);
//                } catch (IOException ex) {
//                    Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return logger;
//    }
//
//    private static class VerySimpleFormatter extends Formatter {
//
//        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
//
//        @Override
//        public String format(LogRecord record) {
//            return String.format(
//                    "%1$s %2$-7s %3$s %4$s\n",
//                    new SimpleDateFormat(datePattern).format(
//                            new Date(record.getMillis())
//                    ),
//                    record.getLevel().getName(),
//                    formatMessage(record)//,
////                     record.getThrown().
//            );
//        }
//    }
//        private static class withStackTraceFormatter extends Formatter {
//
//        private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
//
//        @Override
//        public String format(final LogRecord record) {
//            StackTraceElement[] traces = record.getThrown().getStackTrace();
//            StringBuilder sb = new  StringBuilder("Stacktrace: \n--------------------------------------------------------\n");
//            for (StackTraceElement trace : traces) {
//                sb.append("Line number: "+trace.getLineNumber()+"in Method: "+trace.getMethodName()+" in class "+trace.getClassName()+" in file: "+trace.getFileName()+"\n");
//            }
//            return String.format(
//                    "%1$s %2$-7s %3$s %4$s\n",
//                    new SimpleDateFormat(PATTERN).format(new Date(record.getMillis())),
//                    record.getLevel().getName(),
//                    formatMessage(record),
//                    sb.toString()
//            );
//        }
//    }
    
}
