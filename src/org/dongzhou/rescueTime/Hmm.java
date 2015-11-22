package org.dongzhou.rescueTime;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/* 
 * java -jar jhmm.jar model.xml input.txt
 * 
 * com.milowski.hmm.tools.Forward - the forward algorithm output
 * com.milowski.hmm.tools.Generate - generates random output according to your model
 * com.milowski.hmm.tools.ModelCheck - check the syntax of your model
 * com.milowski.hmm.tools.Predict - calculate the most likely state for a sequence
 * com.milowski.hmm.tools.Train - train a model on a set of sequences
 * 
 */
public class Hmm {

	protected static Logger logger = Logger.getLogger(Hmm.class.getName());

	private static List<Integer> latent = new ArrayList<>();
	private static List<Integer> observe = new ArrayList<>();
	private static String trainingFile = "rescue-time-model-data.txt";

	public static void main(String args[]) {
		// Forward.main(new String[2]);
		setTrainingData();
		createTrainingFile(trainingFile);
	}

	protected static void createTrainingFile(String fileName) {
		FileUtil.write(fileName, getStringData(latent), false);
		FileUtil.write(fileName, getStringData(observe), true);
	}

	private static String getStringData(List<Integer> datas) {
		StringBuffer result = new StringBuffer();
		for (Integer data : datas)
			result.append(data);
		return result.toString();
	}

	protected static void setTrainingData() {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			long totalHours = Math.round(day.getTotalHours());
			int hourIndex = totalHours > 4 ? 1 : 0;
			double productive = day.getProductivePercent();
			int state = productive > 50d ? 1 : 0;
			latent.add(hourIndex);
			observe.add(state);
		}
	}

}
