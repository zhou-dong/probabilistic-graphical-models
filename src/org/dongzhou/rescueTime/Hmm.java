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

	private static List<Object> observe = new ArrayList<>();
	private static String trainingData = "rescue-time-model-training.data";

	protected static String sleepingModel = "sleep-model.xml";
	protected static String sleepingTrainedModel = "sleep-trained-model.xml";
	protected static String sleepingTestData = "sleep-test.data";

	private static void execute(Execute option, String model, String data) {
		logger.info("begin to execute HMM");
		switch (option) {
		case CREATE_DATA:
			setTrainingData();
			createTrainingFile(trainingData);
			break;
		case TRAIN:
			Train.main(new String[] { model, "0.5", "-f", data });
			break;
		case FORWARD:
			Forward.main(new String[] { model, data });
			break;
		case PREDICT:
			Predict.main(new String[] { model, data });
			break;
		case GENERATE:
			break;
		default:
			logger.info("sorry no this execute option");
			break;
		}
		logger.info("execute finish");

	}

	public static void main(String args[]) {

		// execute(Execute.CREATE_DATA, null, null);

		// execute(Execute.TRAIN, sleepingModel, trainingData);

		execute(Execute.FORWARD, sleepingTrainedModel, sleepingTestData);
	}

	protected static void createTrainingFile(String fileName) {
		FileUtil.write(fileName, getStringData(observe), false);
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
			double productive = day.getProductivePercent();

			boolean isTimeEnough = totalHours > 4 ? true : false;
			boolean isProductive = productive > 33d ? true : false;

			if (isTimeEnough && isProductive)
				observe.add('a');
			else
				observe.add('u');
		}
	}

}
