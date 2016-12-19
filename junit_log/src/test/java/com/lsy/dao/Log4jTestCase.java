package com.lsy.dao;


//import org.apache.log4j.Logger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class Log4jTestCase {
    @Test
    public void testLog(){
       /* Logger logger= Logger.getLogger(Log4jTestCase.class);
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");*/

       Logger logger= LoggerFactory.getLogger(Log4jTestCase.class);
        logger.trace("{}-{} trace message","tom","hello");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");

    }
}
