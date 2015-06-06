package engine.parser.instruction;

import java.util.HashMap;

public class MicroInstruction {
	public static enum Signal {
		RSEL, WRITE,
		EA,
		EB,
		S3, S2, S1, S0, C0,
		EF,
	}

	private HashMap<Signal, Integer> map = new HashMap<>();

	public MicroInstruction(Signal... signals) {
		set(signals);
	}

	public int get(Signal signal) {
		if (map.containsKey(signal)) {
			return map.get(signal);
		} else {
			return 0b0;
		}
	}

	public void set(Signal... signals) {
		for (Signal signal : signals) {
			map.put(signal, 0b1);
		}
	}

	@Override
	public String toString() {
		return "MicroInstruction [map=" + map + "]";
	}
}
