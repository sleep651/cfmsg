package nak.nsf.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jSpringLevelConfigurer {

	public Log4jSpringLevelConfigurer(String loggerName, Level level) {
		Logger.getLogger(loggerName).setLevel(level);
	}
}
