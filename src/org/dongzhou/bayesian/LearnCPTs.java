package org.dongzhou.bayesian;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

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

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Streamer;
import norsys.netica.gui.NetPanel;
import norsys.netica.gui.NodePanel;
import norsys.neticaEx.aliases.Node;

public class LearnCPTs {

	static Logger logger = Logger.getLogger(LearnCPTs.class.getName());

	static Node H, HA, BH, BB, HS, KS, HIS, BS, HAS, FS, WF, DD, DN, DW, SL, SM;

	private static Net createNet(String netName) throws NeticaException {
		logger.info("Begin to create NET");
		Node.setConstructorClass("norsys.neticaEx.aliases.Node");
		new Environ(null);
		Net net = new Net();
		net.setName(netName);
		logger.info("Create Net finish");
		return net;
	}

	private static void addNodeToNet(Net net) throws NeticaException {
		logger.info("Begin to add node to net");
		H = new Node("H", OverallHealth.getStates(), net);
		HA = new Node("HA", HeartAttack.getStates(), net);
		BH = new Node("BH", BrokenHip.getStates(), net);
		BB = new Node("BB", BrokenBones.getStates(), net);
		HS = new Node("HS", HospitalStay.getStates(), net);
		KS = new Node("KS", KneeSurgery.getStates(), net);
		HIS = new Node("HIS", HipSurgery.getStates(), net);
		BS = new Node("BS", BackSurgery.getStates(), net);
		HAS = new Node("HAS", HeartSurgery.getStates(), net);
		FS = new Node("FS", FallIn.getStates(), net);
		WF = new Node("WF", WorryFalling.getStates(), net);
		DD = new Node("DD", DepressionOrHopelessness.getStates(), net);
		DN = new Node("DN", NervousOrAnxious.getStates(), net);
		DW = new Node("DW", CanNotStopWorrying.getStates(), net);
		SL = new Node("SL", MoreThan30MinsToSleep.getStates(), net);
		SM = new Node("SM", TakeSleepMedication.getStates(), net);
		logger.info("Add node to net finished");
	}

	private static void addLinkToNet() throws NeticaException {
		logger.info("Begin to add link to net");
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
		logger.info("Add link to net finished");
	}

	public static void drawNet(Net net) throws Exception {
		JFrame frame = new JFrame(net.getName());
		net.compile();
		NetPanel netPanel = new NetPanel(net, NodePanel.NODE_STYLE_BELIEF_BARS);
		netPanel.setLinkPolicy(NetPanel.LINK_POLICY_BELOW);
		frame.getContentPane().add(new JScrollPane(netPanel));
		frame.setAlwaysOnTop(false);
		frame.setLocation(150, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(800, 500);
		frame.setSize(frame.getPreferredSize());
		frame.setVisible(true);
	}

	static void learnCPTs(Net net) throws NeticaException {
		logger.info("Begin to learn CPTs");
		Streamer caseFile = new Streamer(FileUtil.trainingFile);
		net.reviseCPTsByCaseFile(caseFile, net.getNodes(), 1.0);
		logger.info("Learn CPTs finished");
		net.compile();
	}

	private static void showNodes() throws Exception {
		showNode(H);
		showNode(SL);
		showNode(SM);
	}

	private static void showNode(Node node) throws NeticaException {
		logger.info("--------- begin to show node --------- ");
		logger.info(node.getStateNames());
		float[] beliefs = node.getBeliefs();
		StringBuffer sBeliefs = new StringBuffer();
		for (float b : beliefs)
			sBeliefs.append(b).append(" ");
		logger.info(sBeliefs);
		logger.info("Max state is: " + findMaxState(node));
	}

	private static String findMaxState(Node node) throws NeticaException {
		float[] beliefs = node.getBeliefs();
		int maxId = findMaxId(beliefs);
		return node.getStateNamesArray()[maxId];
	}

	private static Map<Node, Integer> nodeIndex;

	private static void initNodeIndex() {
		nodeIndex = new ConcurrentHashMap<>();
		nodeIndex.put(H, 1);
		nodeIndex.put(HA, 2);
		nodeIndex.put(BH, 3);
		nodeIndex.put(BB, 4);
		nodeIndex.put(HS, 5);
		nodeIndex.put(KS, 6);
		nodeIndex.put(HIS, 7);
		nodeIndex.put(BS, 8);
		nodeIndex.put(HAS, 9);
		nodeIndex.put(FS, 10);
		nodeIndex.put(WF, 11);
		nodeIndex.put(DD, 12);
		nodeIndex.put(DN, 13);
		nodeIndex.put(DW, 14);
		nodeIndex.put(SL, 15);
		nodeIndex.put(SM, 16);
	}

	private static void testCPTs(Node node) throws NeticaException {
		// IDnum H HA BH BB HS KS HIS BS HAS FS WF DD DN DW SL SM
		List<String> testSet = FileUtil.loadData(FileUtil.testFile);
		int checkSame = 0;
		for (String oneCase : testSet) {
			String[] states = oneCase.split(" ");
			String testData = states[nodeIndex.get(node)];
			String aimData = findMaxState(node);
			logger.info("Test: [" + testData + "] " + " Aim: [" + aimData + "]");
			if (testData.equalsIgnoreCase(aimData))
				checkSame++;
		}
		float percent = checkSame * 100 / (testSet.size() - 1);
		StringBuffer oLog = new StringBuffer();
		oLog.append("Same time: [").append(checkSame).append("] ");
		oLog.append("Data Size: [").append(testSet.size() - 1).append("] ");
		oLog.append("Percentage: [").append(percent).append("%]");
		logger.info(oLog);
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

	public static void main(String[] args) throws Exception {
		Net net = createNet("SleepingCPTs");
		addNodeToNet(net);
		addLinkToNet();
		learnCPTs(net);
		showNodes();
		initNodeIndex();
		testCPTs(BH);
	}

}
