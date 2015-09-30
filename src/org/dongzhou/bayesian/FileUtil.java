package org.dongzhou.bayesian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.dongzhou.bayesian.Node.BackSurgery;
import org.dongzhou.bayesian.Node.BrokenBones;
import org.dongzhou.bayesian.Node.BrokenHip;
import org.dongzhou.bayesian.Node.CanNotStopWorrying;
import org.dongzhou.bayesian.Node.DepressionOrHopelessness;
import org.dongzhou.bayesian.Node.FallIn;
import org.dongzhou.bayesian.Node.HeartAttack;
import org.dongzhou.bayesian.Node.HeartSurgery;
import org.dongzhou.bayesian.Node.HipSurgery;
import org.dongzhou.bayesian.Node.HospitalStay;
import org.dongzhou.bayesian.Node.KneeSurgery;
import org.dongzhou.bayesian.Node.MoreThan30MinsToSleep;
import org.dongzhou.bayesian.Node.NervousOrAnxious;
import org.dongzhou.bayesian.Node.OverallHealth;
import org.dongzhou.bayesian.Node.TakeSleepMedication;
import org.dongzhou.bayesian.Node.WorryFalling;

public class FileUtil {

	static Logger logger = Logger.getLogger(FileUtil.class.getName());

	public static void rewriteFile(String sourceFile, String destinationFile) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(new File(sourceFile)));
			writer = new BufferedWriter(new FileWriter(new File(destinationFile)));
			String line;
			int index = 0;
			while ((line = reader.readLine()) != null) {
				String[] elements = line.split(",");
				if (index == 0) {
					writeInNewLine(writer, "IDnum " + String.join(" ", elements));
					index++;
					continue;
				}
				StringBuffer newLine = new StringBuffer().append(index + " ");
				for (int i = 0; i < elements.length; i++) {
					String state = getState(i, Integer.parseInt(elements[i]));
					newLine.append(state).append(" ");
				}
				index++;
				logger.info(newLine.toString());
				writeInNewLine(writer, newLine.toString());
			}
			writer.flush();
		} catch (IOException e) {
			logger.error(e);
		} finally {
			close(reader);
			close(writer);
		}

	}

	// H,HA,BH,BB,HS,KS,HIS,BS,HAS,FS,WF,DD,DN,DW,SL,SM
	private static String getState(int caseIndex, int caseValue) {
		switch (caseIndex) {
		case 0:
			return OverallHealth.getName(caseValue);
		case 1:
			return HeartAttack.getName(caseValue);
		case 2:
			return BrokenHip.getName(caseValue);
		case 3:
			return BrokenBones.getName(caseValue);
		case 4:
			return HospitalStay.getName(caseValue);
		case 5:
			return KneeSurgery.getName(caseValue);
		case 6:
			return HipSurgery.getName(caseValue);
		case 7:
			return BackSurgery.getName(caseValue);
		case 8:
			return HeartSurgery.getName(caseValue);
		case 9:
			return FallIn.getName(caseValue);
		case 10:
			return WorryFalling.getName(caseValue);
		case 11:
			return DepressionOrHopelessness.getName(caseValue);
		case 12:
			return NervousOrAnxious.getName(caseValue);
		case 13:
			return CanNotStopWorrying.getName(caseValue);
		case 14:
			return MoreThan30MinsToSleep.getName(caseValue);
		case 15:
			return TakeSleepMedication.getName(caseValue);
		default:
			return " ";
		}
	}

	private static void writeInNewLine(BufferedWriter writer, String content) throws IOException {
		writer.write(content);
		writer.newLine();
	}

	private static void close(Closeable closeable) {
		if (closeable == null)
			return;
		try {
			closeable.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static void divideDataset() {
		List<String> datas = loadData(destinationFile);
		createTrainingFile(datas);
		createTestFile(datas);
	}

	public static void createTrainingFile(List<String> datas) {
		createSubsetFile(datas, trainingFile, getTrainingSize(datas.size()));
	}

	public static void createTestFile(List<String> datas) {
		createSubsetFile(datas, testFile, getTestSize(datas.size()));
	}

	public static void createSubsetFile(List<String> datas, String subsetFile, int subsetSize) {
		int[] index = new int[subsetSize];
		for (int i = 0; i < subsetSize; i++)
			index[i] = getRandomNumber(datas.size());
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(subsetFile)));
			writeInNewLine(writer, datas.get(0));
			for (int x : index)
				writeInNewLine(writer, datas.get(x));
			writer.flush();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			close(writer);
		}
	}

	public static List<String> loadData(String path) {
		List<String> result = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			String line;
			while ((line = reader.readLine()) != null) {
				result.add(line);
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			close(reader);
		}
		return result;
	}

	static Random randomGenerator = new Random();

	private static int getRandomNumber(int dataSize) {
		return randomGenerator.nextInt(dataSize - 1) + 1;
	}

	private static int getTestSize(int dataSize) {
		Double result = (dataSize - 1) * 0.2;
		return result.intValue();
	}

	private static int getTrainingSize(int dataSize) {
		Double result = (dataSize - 1) * 0.8;
		return result.intValue();
	}

	public static String sourceFile = "data/dataprocess.csv";
	public static String destinationFile = "data/newdataprocess.csv";
	public static String learnedFile = "data/learnedfile.dne";
	public static String trainingFile = "data/trainingFile.csv";
	public static String testFile = "data/testFile.csv";

	public static void main(String[] args) {
		int key = 0;
		switch (key) {
		case 0:
			rewriteFile(sourceFile, destinationFile);
			break;
		case 1:
			divideDataset();
			break;
		}
	}

}
