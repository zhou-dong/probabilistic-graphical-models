package org.zhengbo.study;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dongzhou.rescueTime.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WriteXmlToCsv2 {

	static final String COMMA_DELIMITER = ",";
	static Pattern pattern = Pattern.compile("[.,\"\\?!:']");
	static String filePath = "/Users/dongdong/Downloads/Restaurants_Train.xml";
	static String csvPath = "/Users/dongdong/Downloads/Restaurants_Train2.csv";

	static void addHeaderToFolie() {
		StringBuffer header = new StringBuffer();
		stringBufferappendWithComma(header, "id");
		stringBufferappendWithComma(header, "docId");
		stringBufferappendWithComma(header, "critique");
		header.append("text");
		FileUtil.write(csvPath, header.toString(), false);
	}

	static void stringBufferappendWithComma(StringBuffer buffer, String text) {
		buffer.append(text).append(COMMA_DELIMITER);
	}

	static String removeCharacter(String text) {
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll("");
	}

	public static Document createDocument() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document document = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(new File(filePath));
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void main(String args[]) throws Exception {
		System.out.println("begin to read file with path: " + filePath);
		addHeaderToFolie();
		Document document = createDocument();
		NodeList sentenceList = document.getElementsByTagName("sentence");
		for (int i = 0; i < sentenceList.getLength(); i++) {
			StringBuffer buffer = new StringBuffer();
			// add id
			buffer.append(i + 1).append(COMMA_DELIMITER);
			Element sentence = (Element) sentenceList.item(i);
			// add docId
			String docId = sentence.getAttribute("id");
			stringBufferappendWithComma(buffer, docId);
			// add critique
			NodeList categories = sentence.getElementsByTagName("aspectCategory");
			addCategoriesToString(categories, buffer);
			// add text
			String text = removeCharacter(sentence.getTextContent().trim());
			buffer.append(text);
			FileUtil.write(csvPath, buffer.toString(), true);
		}
		System.out.println("finish write file to: " + csvPath);
	}

	static void addCategoriesToString(NodeList categories, StringBuffer buffer) {
		String critique = "neutral";
		for (int x = 0; x < categories.getLength(); x++) {
			Element aspectCategory = (Element) categories.item(x);
			String polarity = aspectCategory.getAttribute("polarity");
			if ("positive".equalsIgnoreCase(polarity))
				critique = "positive";
			else if ("negative".equalsIgnoreCase(polarity))
				critique = "negative";
		}
		stringBufferappendWithComma(buffer, critique);
	}

}
