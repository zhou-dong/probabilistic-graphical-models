package org.dongzhou.rescueTime;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.milowski.hmm.tools.Train;

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

	static enum Execute {
		CREATE_DATA, FORWARD, GENERATE, PREDICT, TRAIN;
	}

	protected static Logger logger = Logger.getLogger(Hmm.class.getName());

	private static List<Object> latent = new ArrayList<>();
	private static List<Object> observe = new ArrayList<>();
	private static String trainingFile = "rescue-time-model-data.txt";
	private static String model = "rescue-time-model.xml";
	private static String trainedModel = "rescue-time-model-trained.xml";

	public static void main(String args[]) {

		Execute execute = Execute.TRAIN;
		switch (execute) {
		case CREATE_DATA:
			setTrainingData();
			createTrainingFile(trainingFile);
			break;
		case TRAIN:
			String[] trainArgs = { model, "0.5", "-f", trainingFile };
			Train.main(trainArgs);
			break;
		case FORWARD:
			break;
		case GENERATE:
			break;
		case PREDICT:
			break;
		}
	}

	protected static void createTrainingFile(String fileName) {
		FileUtil.write(fileName, getStringData(latent), false);
		FileUtil.write(fileName, getStringData(observe), true);
		// FileUtil.write(fileName, "1 = activity", true);
		// FileUtil.write(fileName, "2 = unactivity", true);
	}

	private static String getStringData(List<Object> datas) {
		StringBuffer result = new StringBuffer();
		for (Object data : datas)
			result.append(data.toString());
		return result.toString();
	}

	protected static void setTrainingData() {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			long totalHours = Math.round(day.getTotalHours());
			int hourIndex = totalHours > 4 ? 1 : 1;
			double productive = day.getProductivePercent();
			int state = productive > 50d ? 1 : 2;
			latent.add(hourIndex);
			observe.add(state);
		}
	}

}
