package org.dongzhou.rescueTime;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.milowski.hmm.tools.Forward;
import com.milowski.hmm.tools.Predict;
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
	private static String trainingData = "rescue-time-model-training.data";
	private static String data = "rescue-time-model.data";
	private static String model = "rescue-time-model.xml";
	private static String updatedModel = "rescue-time-model-update.xml";
	private static String[] hmmArgs = { updatedModel, data };

	public static void main(String args[]) {

		Execute execute = Execute.FORWARD;
		switch (execute) {
		case CREATE_DATA:
			setTrainingData();
			createTrainingFile(trainingData);
			break;
		case TRAIN:
			String[] trainArgs = { model, "0.5", "-f", trainingData };
			Train.main(trainArgs);
			break;
		case FORWARD:
			Forward.main(hmmArgs);
			break;
		case GENERATE:
			break;
		case PREDICT:
			Predict.main(hmmArgs);
			break;
		}
	}

	protected static void createTrainingFile(String fileName) {
		FileUtil.write(fileName, getStringData(observe), false);
		// FileUtil.write(fileName, getStringData(latent), true);
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
			int hourIndex = totalHours > 4 ? 1 : 2;
			double productive = day.getProductivePercent();
			char state = productive > 50d ? 'w' : 'p';
			latent.add(hourIndex);
			observe.add(state);
		}
	}

}
