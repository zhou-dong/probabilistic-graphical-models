package org.dongzhou.bayesian;

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
import norsys.neticaEx.aliases.Node;

public class BuildNet {

	static Logger logger = Logger.getLogger(BuildNet.class.getName());

	private static Net createNet(String netName) throws NeticaException {
		logger.info("Begin to create NET");
		Node.setConstructorClass("norsys.neticaEx.aliases.Node");
		new Environ(null);
		Net net = new Net();
		net.setName(netName);
		logger.info("Create Net finish");
		return net;
	}

	private static Net addNodeToNet(Net net) throws NeticaException {
		logger.info("Begin to add node to net");
		Node H = new Node("H", OverallHealth.getStates(), net);
		Node HA = new Node("HA", HeartAttack.getStates(), net);
		Node BH = new Node("BH", BrokenHip.getStates(), net);
		Node BB = new Node("BB", BrokenBones.getStates(), net);
		Node HS = new Node("HS", HospitalStay.getStates(), net);
		Node KS = new Node("KS", KneeSurgery.getStates(), net);
		Node HIS = new Node("HIS", HipSurgery.getStates(), net);
		Node BS = new Node("BS", BackSurgery.getStates(), net);
		Node HAS = new Node("HAS", HeartSurgery.getStates(), net);
		Node FS = new Node("FS", FallIn.getStates(), net);
		Node WF = new Node("WF", WorryFalling.getStates(), net);
		Node DD = new Node("DD", DepressionOrHopelessness.getStates(), net);
		Node DN = new Node("DN", NervousOrAnxious.getStates(), net);
		Node DW = new Node("DW", CanNotStopWorrying.getStates(), net);
		Node SL = new Node("SL", MoreThan30MinsToSleep.getStates(), net);
		Node SM = new Node("SM", TakeSleepMedication.getStates(), net);
		logger.info("Add node to net finished");
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
		return net;
	}

	public static void main(String[] args) throws Exception {
		Net net = createNet("HealthNet");
		addNodeToNet(net);
		System.out.println(net.getNodes());
	}

}
