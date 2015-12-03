package org.zhengbo.study;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dongzhou.rescueTime.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WriteXmlToCsv {

	static final String COMMA_DELIMITER = ",";
	static Pattern pattern = Pattern.compile("[.,\"\\?!:']");
	static String filePath = "/Users/dongdong/Downloads/Restaurants_Train.xml";
	static String csvPath = "/Users/dongdong/Downloads/Restaurants_Train.csv";
	static Map<String, Integer> categoryMap = new HashMap<String, Integer>();
	static Map<String, Integer> termMap = new HashMap<String, Integer>();

	static List<String> files = new ArrayList<>();

	static void addHeader() {
		StringBuffer header = new StringBuffer();
		stringBufferappendWithComma(header, "id");
		stringBufferappendWithComma(header, "docId");
		stringBufferappendWithComma(header, "anecdotes/miscellaneous");
		stringBufferappendWithComma(header, "ambience");
		stringBufferappendWithComma(header, "servic");
		stringBufferappendWithComma(header, "price");
		stringBufferappendWithComma(header, "food");
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

		addHeader();

		Document document = createDocument();

		NodeList sentenceList = document.getElementsByTagName("sentence");

		for (int i = 0; i < sentenceList.getLength(); i++) {

			StringBuffer buffer = new StringBuffer().append(i + 1).append(COMMA_DELIMITER);

			Element sentence = (Element) sentenceList.item(i);

			String docId = sentence.getAttribute("id");
			stringBufferappendWithComma(buffer, docId);

			NodeList terms = sentence.getElementsByTagName("aspectTerm");
			addTermsToString(terms, buffer);

			NodeList categories = sentence.getElementsByTagName("aspectCategory");
			addCategoriesToString(categories, buffer);

			String text = removeCharacter(sentence.getTextContent().trim());
			buffer.append(text);

			FileUtil.write(csvPath, buffer.toString(), true);

		}

	}

	static void addTermsToString(NodeList terms, StringBuffer buffer) {
		for (int x = 0; x < terms.getLength(); x++) {
			Element aspectTerm = (Element) terms.item(x);
			String term = aspectTerm.getAttribute("term");
			if (termMap.containsKey(term))
				termMap.put(term, termMap.get(term) + 1);
			else
				termMap.put(term, 1);
			aspectTerm.getAttribute("polarity");
		}
	}

	static void addCategoriesToString(NodeList categories, StringBuffer buffer) {
		Map<String, String> tmpMap = new HashMap<>();
		for (int x = 0; x < categories.getLength(); x++) {
			Element aspectCategory = (Element) categories.item(x);
			String category = aspectCategory.getAttribute("category");
			String polarity = aspectCategory.getAttribute("polarity");
			tmpMap.put(category, polarity);
		}
		stringBufferappendWithComma(buffer, tmpMap.get("anecdotes/miscellaneous"));
		stringBufferappendWithComma(buffer, tmpMap.get("ambience"));
		stringBufferappendWithComma(buffer, tmpMap.get("service"));
		stringBufferappendWithComma(buffer, tmpMap.get("price"));
		stringBufferappendWithComma(buffer, tmpMap.get("food"));
	}

	static void printTerms() {
		printMap(termMap);
	}

	static void printMap(Map<String, Integer> map) {
		System.out.println(map.size());
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
