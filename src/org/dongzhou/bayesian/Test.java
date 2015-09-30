package org.dongzhou.bayesian;

/* 
 * Demo.java
 *
 * Example use of Netica-J to build a Bayes net and use it for inference.
 *
 * Copyright (C) 1992-2007 by Norsys Software Corp.
 * The software in this file may be copied, modified, and/or included in 
 * derivative works without charge or obligation.
 * This file contains example software only, and Norsys makes no warranty that 
 * it is suitable for any particular purpose, or without defects.
 */

import norsys.netica.*;
import norsys.neticaEx.aliases.Node;

public class Test {
	public static void main(String[] args) {
		try {
			System.out.println("\nWelcome to Netica-J !\n");

			Node.setConstructorClass("norsys.neticaEx.aliases.Node");
			new Environ(null);

			Net net = new Net();

			String[] boolean_states = { "yes", "no" };

			Node A = new Node("A", String.join(",", boolean_states[0], boolean_states[1]), net);
			Node B = new Node("B", String.join(",", boolean_states[0], boolean_states[1]), net);
			Node C = new Node("C", String.join(",", boolean_states[0], boolean_states[1]), net);

			C.addLink(A);
			C.addLink(B);
			A.setCPTable(null, 0.8, 0.2);
			B.setCPTable(null, 0.5, 0.5);

			C.setCPTable("yes,yes", 0.4, 0.6);
			C.setCPTable("yes,no", 0.8, 0.2);
			C.setCPTable("no,yes", 0.6, 0.4);
			C.setCPTable("no,no", 0.2, 0.8);

			net.compile();

			String[][] testdata = { { "yes", "yes", "no" }, { "no", "no", "no" },
					{ "yes", "no", "yes" } };

			A.finding().enterState(testdata[0][0]);
			B.finding().enterState(testdata[0][1]);
			float[] CBeliefs = C.getBeliefs();
			int max_ind = getMax(CBeliefs);
			System.out.println("max_ind = " + max_ind);
			System.out.println("State with max prob is " + boolean_states[max_ind]);
			if (boolean_states[max_ind].equals(testdata[0][2]))
				System.out.println("Model is correct on observation 1");
			else
				System.out.println("Model is incorrect on observation 1");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static int getMax(float[] vals) {
		int i;
		float maxval = -1;
		int maxind = -1;
		for (i = 0; i < vals.length; i++) {
			if (vals[i] > maxval) {
				maxval = vals[i];
				maxind = i;
			}
		}
		return maxind;
	}

}
