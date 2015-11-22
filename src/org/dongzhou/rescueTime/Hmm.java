package org.dongzhou.rescueTime;

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

	private static Logger logger = Logger.getLogger(Hmm.class.getName());

	public static void main(String args[]) {
		// Forward.main(new String[2]);
		setPoints();
	}

	protected static void setPoints() {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			double productive = day.getProductivePercent();
			double other = day.getDistractingPercent() + day.getNeutralPercent();
			logger.info(productive);
			logger.info(other);
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

}
