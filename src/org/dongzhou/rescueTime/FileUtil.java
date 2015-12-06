package org.dongzhou.rescueTime;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class.getName());

	public static void write(String fileName, String content, boolean append) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, append);
			writer.write(content);
			writer.write("\n");
		} catch (IOException e) {
			logger.error(e);
		}
		close(writer);
		logger.debug("logger to file: " + fileName);
	}

	public static String reader(File file, String seperate) {
		if (null == seperate) {
			seperate = System.lineSeparator();
		}
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				result.append(line);
				result.append(seperate);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(reader);
		}
		return result.toString();
	}

	public static void close(Closeable closeable) {
		if (closeable == null)
			return;
		try {
			closeable.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}

}
