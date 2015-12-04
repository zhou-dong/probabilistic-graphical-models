package org.zhengbo.study;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dongzhou.rescueTime.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WriteXmlCategoryToCsv {

	static final String COMMA_DELIMITER = ",";
	static Pattern pattern = Pattern.compile("[.,\"\\?!:']");

	static String basePath = "/Users/dongdong/Desktop/zhong-data/";
	static String csvFile = basePath + "restaurants-category.csv";
	static List<String> xmlFiles = new ArrayList<String>();

	static void addXmlFilePath() {
		xmlFiles.add(basePath + "restaurants-trial.xml");
		xmlFiles.add(basePath + "Restaurants_Train.xml");
	}

	static void addHeaderToFolie() {
		StringBuffer header = new StringBuffer();
		stringBufferappendWithComma(header, "id");
		stringBufferappendWithComma(header, "docId");
		stringBufferappendWithComma(header, "category");
		header.append("text");
		FileUtil.write(csvFile, header.toString(), false);
	}

	static void stringBufferappendWithComma(StringBuffer buffer, String text) {
		buffer.append(text).append(COMMA_DELIMITER);
	}

	static String removeCharacter(String text) {
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll("");
	}

	public static Document createDocument(String filePath) {
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
		addHeaderToFolie();
		addXmlFilePath();
		for (String filePath : xmlFiles) {
			System.out.println(filePath);
			Document document = createDocument(filePath);
			writeToCsv(document);
		}
	}

	static void writeToCsv(Document document) {
		NodeList sentenceList = document.getElementsByTagName("sentence");
		for (int i = 0; i < sentenceList.getLength(); i++) {
			Element sentence = (Element) sentenceList.item(i);
			String docId = sentence.getAttribute("id");
			NodeList categories = sentence.getElementsByTagName("aspectCategory");
			String text = removeCharacter(sentence.getTextContent().trim());
			addCategoriesToString(categories, text, docId);
		}
	}

	static int lineNumber = 0;

	static void addCategoriesToString(NodeList categories, String text, String docId) {
		for (int x = 0; x < categories.getLength(); x++) {
			lineNumber++;
			StringBuffer buffer = new StringBuffer();
			stringBufferappendWithComma(buffer, lineNumber + "");
			stringBufferappendWithComma(buffer, docId);
			Element aspectCategory = (Element) categories.item(x);
			String category = aspectCategory.getAttribute("category");
			stringBufferappendWithComma(buffer, category);
			buffer.append(text);
			FileUtil.write(csvFile, buffer.toString(), true);
		}
	}

}
