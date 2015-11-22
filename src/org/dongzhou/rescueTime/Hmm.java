package org.dongzhou.rescueTime;

import com.milowski.hmm.tools.Forward;

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

	public static void main(String args[]) {

		Forward.main(new String[2]);

		System.out.println("Hello world!");

	}
}
