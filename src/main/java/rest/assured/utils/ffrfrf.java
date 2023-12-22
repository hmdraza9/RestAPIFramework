package rest.assured.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ffrfrf {

	private static final Logger log = LogManager.getLogger(ffrfrf.class);

//	public static final String path = System.getProperty("user.dir") + "/src/test/resources/"+"config.properties";
	public static final String path = System.getProperty("user.dir") + "\\src\\test\\resources\\"+"config.properties";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		log.info(path);
		
		File file = new File(path);
		
		System.out.println(file.exists());
		
	}

}
