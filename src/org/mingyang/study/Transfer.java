package org.mingyang.study;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dongzhou.rescueTime.FileUtil;

public class Transfer {

	private static String hamPath = "/Users/dongdong/Desktop/mingyang/ham";
	private static String spamPath = "/Users/dongdong/Desktop/mingyang/spam";
	private static String csvPath = "/Users/dongdong/Desktop/mingyang/data.csv";

	static Pattern pattern = Pattern.compile("[.,\"\\?!:'()-/$#]");

	static void addHeaderToFolie() {
		String header = "id,category,content";
		FileUtil.write(csvPath, header, false);
	}

	static String removeCharacter(String text) {
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll("");
	}

	public static List<String> getContent(String path) {
		List<String> result = new ArrayList<String>();
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			String content = FileUtil.reader(file, " ");
			content = removeCharacter(content);
			content = content.replace("Subject", "");
			result.add(content);
		}
		return result;
	}

	static int index = 0;

	static void writeToFile(String path, String category) {
		List<String> hamContent = getContent(path);
		for (String line : hamContent) {
			String content = (++index) + "," + category + "," + line;
			FileUtil.write(csvPath, content, true);
		}
	}

	public static void main(String[] args) {
		addHeaderToFolie();
		writeToFile(hamPath, "ham");
		writeToFile(spamPath, "spam");
	}

}
