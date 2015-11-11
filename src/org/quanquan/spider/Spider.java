package org.quanquan.spider;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {

	private static boolean isRun = false;
	private static ExecutorService executors = null;
	private static Queue<String> unexplored = new ArrayBlockingQueue<>(100);
	private static Map<String, Integer> explored = new ConcurrentHashMap<>();

	public static ExecutorService createThreadPool(int size) {
		return Executors.newFixedThreadPool(size);
	}

	public static void watchQueue() {
		isRun = true;
		while (isRun) {
			String url = unexplored.poll();
			if (url != null) {
				executors.execute(new GetPage(url));
			}
		}
	}

	public static void close() {
		isRun = false;
		executors.shutdown();
	}

	public static class GetPage implements Runnable {

		private String url = null;

		public GetPage(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			if (explored.containsKey(url))
				return;
			try {
				getPageContent(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void getPageContent(String url) throws IOException {
		System.out.println("get content from: " + url);
		Document document = Jsoup.connect(url).get();
		System.out.println(document);
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String href = link.attr("abs:href");
			if (explored.containsKey(href) == false) {
				unexplored.add(href);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String mainPage = "http://www.troy.edu/";
		getPageContent(mainPage);
		executors = createThreadPool(10);
		watchQueue();
	}

}