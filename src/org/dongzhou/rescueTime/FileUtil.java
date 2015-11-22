package org.dongzhou.rescueTime;

import java.io.Closeable;
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
