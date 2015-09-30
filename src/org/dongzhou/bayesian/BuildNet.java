package org.dongzhou.bayesian;

import org.apache.log4j.Logger;

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

		Node h = new Node("H", "EXCELLENT, ", net);
		return net;
	}

	public static void main(String args[]) {

		try {
			createNet("Test");
		} catch (NeticaException e) {
			e.printStackTrace();
		}

	}

}
