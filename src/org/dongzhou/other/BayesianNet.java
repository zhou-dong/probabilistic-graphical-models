package org.dongzhou.other;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class BayesianNet {

	public static void main(String args[]) throws NeticaException {

		Node.setConstructorClass("norsys.neticaEx.aliases.Node");
		new Environ(null);
		Net net = new Net();
		net.setName("BayesianNet");
		Node H = new Node("H", "a1,a2,a3,a4,a5", net);
		Node HA = new Node("HA", "a1,a2", net);
		Node BH = new Node("BH", "a1,a2", net);
		Node BB = new Node("BB", "a1,a2", net);
		Node HS = new Node("HS", "a1,a2", net);
		Node KS = new Node("KS", "a1,a2", net);
		Node HIS = new Node("HIS", "a1,a2", net);
		Node BS = new Node("BS", "a1,a2", net);
		Node HAS = new Node("HAS", "a1,a2", net);
		Node FS = new Node("FS", "a1,a2", net);
		Node WF = new Node("WF", "a1,a2", net);
		Node DD = new Node("DD", "a1,a2,a3,a4", net);
		Node DN = new Node("DN", "a1,a2,a3,a4", net);
		Node DW = new Node("DW", "a1,a2,a3,a4", net);
		Node SL = new Node("SL", "a1,a2,a3,a4,a5", net);
		Node SM = new Node("SM", "a1,a2,a3,a4,a5", net);

		DD.addLink(H);
		HA.addLink(H);
		BB.addLink(H);
		BH.addLink(H);
		HS.addLink(H);
		WF.addLink(FS);
		BS.addLink(FS);
		BS.addLink(BB);
		BB.addLink(FS);
		KS.addLink(FS);
		BH.addLink(FS);
		HS.addLink(KS);
		HS.addLink(BS);
		HS.addLink(HIS);
		HIS.addLink(BH);
		HAS.addLink(HS);
		HA.addLink(HAS);
		DN.addLink(BB);
		DN.addLink(WF);
		DN.addLink(BH);
		DN.addLink(DD);
		DN.addLink(HA);
		SL.addLink(DW);
		SL.addLink(DN);
		SL.addLink(DD);
		SL.addLink(HS);
		SL.addLink(HIS);
		DW.addLink(BB);
		DW.addLink(BH);
		DW.addLink(HA);
		DW.addLink(DN);
		DD.addLink(BB);
		DD.addLink(BH);
		DD.addLink(HA);
		DD.addLink(HS);
		SM.addLink(SL);

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader("dataprocess.csv"));
			writer = new BufferedWriter(new FileWriter("trainingFile.csv"));
			String line = "";
			int index = 0;
			while ((line = reader.readLine()) != null) {
				String[] states = line.split(",");
				StringBuffer sb = new StringBuffer();
				for (String state : states)
					if (index == 0)
						sb.append(state).append(" ");
					else
						sb.append("a").append(state).append(" ");
				writer.write(sb.toString());
				writer.newLine();
				index++;
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Streamer caseFile = new Streamer("trainingFile.csv");
		net.reviseCPTsByCaseFile(caseFile, net.getNodes(), 1.0);
		net.compile();

		List<String> wholeSet = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader("trainingFile.csv"));
			String line = "";
			while ((line = reader.readLine()) != null)
				wholeSet.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<String> testSet = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int randomNum = random.nextInt(wholeSet.size() - 1) + 1;
			testSet.add(wholeSet.get(randomNum));
		}
		int sameNumber = 0;
		for (String oCase : testSet) {
			String[] states = oCase.split(" ");
			H.finding().enterState(states[0]);
			HA.finding().enterState(states[1]);
			BH.finding().enterState(states[2]);
			BB.finding().enterState(states[3]);
			HS.finding().enterState(states[4]);
			KS.finding().enterState(states[5]);
			HIS.finding().enterState(states[6]);
			BS.finding().enterState(states[7]);
			HAS.finding().enterState(states[8]);
			// FS.finding().enterState(states[9]);
			WF.finding().enterState(states[10]);
			DD.finding().enterState(states[11]);
			DN.finding().enterState(states[12]);
			DW.finding().enterState(states[13]);
			SL.finding().enterState(states[14]);
			SM.finding().enterState(states[15]);

			int maxId = findMaxId(FS.getBeliefs());
			String maxState = FS.getStateNamesArray()[maxId];

			String observeState = states[9];
			if (maxState.equalsIgnoreCase(observeState))
				sameNumber++;

			H.finding().clear();
			HA.finding().clear();
			BH.finding().clear();
			BB.finding().clear();
			HS.finding().clear();
			KS.finding().clear();
			HIS.finding().clear();
			BS.finding().clear();
			HAS.finding().clear();
			FS.finding().clear();
			WF.finding().clear();
			DD.finding().clear();
			DN.finding().clear();
			DW.finding().clear();
			SL.finding().clear();
			SM.finding().clear();
		}
		System.out.println(FS.getStateNames());
		float[] beliefs = FS.getBeliefs();
		for (float belief : beliefs)
			System.out.print(belief + " ");
		System.out.println();
		System.out.println(sameNumber * 100 / testSet.size() + "%");
		net.finalize();
	}

	private static int findMaxId(float[] beliefs) {
		int result = 0;
		float max = -1;
		for (int i = 0; i < beliefs.length; i++)
			if (beliefs[i] > max) {
				max = beliefs[i];
				result = i;
			}
		return result;
	}

}
