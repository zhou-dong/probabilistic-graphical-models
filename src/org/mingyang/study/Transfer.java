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

	static Pattern pattern = Pattern.compile("[.,\"\\?!:'()-/$#%@|]");

	static void addHeaderToFolie() {
		String header = "id,category,content";
		FileUtil.write(csvPath, header, false);
	}

	static String removeCharacter(String text) {
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll("");
	}

	public static String getString(String src) {
		char[] cr = src.toCharArray();
		char[] str = new char[cr.length];
		int index = 0;
		for (char newchar : cr) {
			if ((newchar <= 'Z' && newchar >= 'A') || (newchar <= 'z' && newchar >= 'a')
					|| newchar == ' ') {
				str[index++] = newchar;
			}
		}
		return new String(str, 0, index);
	}

	public static List<String> getContent(String path) {
		List<String> result = new ArrayList<String>();
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			List<String> lines = FileUtil.reader(file, " ");
			for (String line : lines) {
				// line = removeCharacter(line);
				line = line.replace("Subject", "");
				// line = line.replaceAll("\\d+", "");
				line = line.trim();
				line = getString(line);
				if (line == null || line.isEmpty() || " ".equals(line))
					continue;
				result.add(line);
			}
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
