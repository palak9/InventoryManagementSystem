package system;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

	public Logger logger;
	FileHandler fh;
	
	public Log(String filename)throws SecurityException,IOException
	{
		fh = new FileHandler("resource/note/LogText.txt",true);
		logger = Logger.getLogger(Log.class.getName());
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		logger.addHandler(fh);
	}
}