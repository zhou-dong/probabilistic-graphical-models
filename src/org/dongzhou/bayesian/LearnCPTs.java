package org.dongzhou.bayesian;

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
	}

	private static void showNodes() throws Exception {
		showNode(H);
		showNode(SL);
	}

	private static void showNode(Node node) throws NeticaException {
		logger.info(node.getStateNames());
		float[] beliefs = node.getBeliefs();
		for (float b : beliefs)
			logger.info(b);
	}

	public static void main(String[] args) throws Exception {
		Net net = createNet("SleepingCPTs");
		addNodeToNet(net);
		addLinkToNet();
		learnCPTs(net);
		net.compile();
		showNodes();
	}

}