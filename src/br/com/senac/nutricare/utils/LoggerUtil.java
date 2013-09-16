package br.com.senac.nutricare.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private Logger logger;

    public LoggerUtil() {
        System.out.println("Carrega parâmetros do logger");
        initializeLogger();
        logger = LoggerFactory.getLogger(LoggerUtil.class);
    }

    private void initializeLogger() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        lc.reset();
        configurator.setContext(lc);
        try {
            configurator.doConfigure("logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }

    public void info(final String msg) {
        System.out.println(msg);
        logger.info(msg);
    }

    public void error(final String msg) {
        logger.error(msg);
    }
}
