package org.dongzhou.bayesian;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Node {

	public static enum OverallHealth {

		EXCELLENT(1, "EXCELLENT"), VERY_GOOD(2, "VERY_GOOD"), GOOD(3, "GOOD"), FAIR(4,
				"FAIR"), POOR(5, "POOR");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (OverallHealth obj : OverallHealth.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;
		private String name;

		private OverallHealth(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(OverallHealth.values());
		}
	}

	public static enum HeartAttack {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (HeartAttack obj : HeartAttack.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;
		private String name;

		private HeartAttack(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(HeartAttack.values());
		}
	}

	public static enum BrokenHip {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (BrokenHip obj : BrokenHip.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private BrokenHip(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(BrokenHip.values());
		}

	}

	public static enum BrokenBones {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (BrokenBones obj : BrokenBones.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private BrokenBones(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(BrokenBones.values());
		}

	}

	public static enum HospitalStay {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (HospitalStay obj : HospitalStay.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private HospitalStay(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(HospitalStay.values());
		}

	}

	public static enum KneeSurgery {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (KneeSurgery obj : KneeSurgery.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private KneeSurgery(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(KneeSurgery.values());
		}

	}

	public static enum HipSurgery {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (HipSurgery obj : HipSurgery.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private HipSurgery(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(HipSurgery.values());
		}

	}

	public static enum BackSurgery {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (BackSurgery obj : BackSurgery.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private BackSurgery(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(BackSurgery.values());
		}

	}

	public static enum HeartSurgery {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (HeartSurgery obj : HeartSurgery.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private HeartSurgery(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(HeartSurgery.values());
		}

	}

	public static enum FallIn {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (FallIn obj : FallIn.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private FallIn(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(FallIn.values());
		}

	}

	public static enum WorryFalling {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (WorryFalling obj : WorryFalling.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private WorryFalling(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(WorryFalling.values());
		}

	}

	public static enum DepressionOrHopelessness {

		NOT_AT_ALL(1, "NOT_AT_ALL"), SEVERAL_DAYS(2, "SEVERAL_DAYS"), MORE_THAN_HALF_DAYS(3,
				"MORE_THAN_HALF_DAYS"), NEARLY_EVERY_DAY(4, "NEARLY_EVERY_DAY");
		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (DepressionOrHopelessness obj : DepressionOrHopelessness.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;
		private String name;

		private DepressionOrHopelessness(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(DepressionOrHopelessness.values());
		}

	}

	public static enum NervousOrAnxious {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (NervousOrAnxious obj : NervousOrAnxious.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private NervousOrAnxious(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(NervousOrAnxious.values());
		}

	}

	public static enum CanNotStopWorrying {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (CanNotStopWorrying obj : CanNotStopWorrying.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private CanNotStopWorrying(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(CanNotStopWorrying.values());
		}

	}

	public static enum MoreThan30MinsToSleep {

		EVERY_NIGHT(1, "EVERY_NIGHT"), MOST_NIGHTS(2, "MOST_NIGHTS"), SOME_NIGHTS(3,
				"SOME_NIGHTS"), RARELY(4, "RARELY"), NEVER(5, "NEVER");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (MoreThan30MinsToSleep obj : MoreThan30MinsToSleep.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;
		private String name;

		private MoreThan30MinsToSleep(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(MoreThan30MinsToSleep.values());
		}

	}

	public static enum TakeSleepMedication {

		YES(1, "YES"), NO(2, "NO");

		private static final Map<Integer, String> map;

		static {
			map = new ConcurrentHashMap<>();
			for (TakeSleepMedication obj : TakeSleepMedication.values()) {
				map.put(obj.index, obj.name);
			}
		}

		private int index;

		private String name;

		private TakeSleepMedication(int index, String name) {
			this.index = index;
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static String getName(int index) {
			return map.get(index);
		}

		public static String getStates() {
			return Node.getStates(TakeSleepMedication.values());
		}

	}

	private static String getStates(Enum<?>[] arrays) {
		StringBuffer result = new StringBuffer();
		int x = 1;
		for (Enum<?> e : arrays) {
			result.append(e.toString());
			if (x != arrays.length)
				result.append(",");
			x++;
		}
		return result.toString();
	}

}