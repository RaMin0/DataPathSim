package engine.component;

import java.util.ArrayList;

import engine.bus.Bus32;

public class ALU extends Clock.ImplListener {
	private ArrayList<Listener> listeners = new ArrayList<>();

	private int s;
	private int c;

	private Bus32 a, b, f;

	public void in_a(Bus32 a) {
		this.a = a;
	}

	public void in_b(Bus32 b) {
		this.b = b;
	}

	public void out_f(Bus32 f) {
		this.f = f;
	}

	public void setS(int s) {
		this.s = s;

		notifyOnFn();
	}

	public void setC(int c) {
		this.c = c;

		notifyOnFn();
	}

	public String getFn() {
		String fn = null;

		switch (s) {
		case 0b0000:
			if (c == 0b0) {
				fn = "1";
			} else {
				fn = "0";
			}
			break;
		case 0b0001:
			if (c == 0b0) {
				fn = "B - A - 1";
			} else {
				fn = "B - A";
			}
			break;
		case 0b0010:
			if (c == 0b0) {
				fn = "A - B - 1";
			} else {
				fn = "A - B";
			}
			break;
		case 0b0011:
			if (c == 0b0) {
				fn = "A + B";
			} else {
				fn = "A + B + 1";
			}
			break;
		case 0b0100:
			if (c == 0b0) {
				fn = "B";
			} else {
				fn = "B + 1";
			}
			break;
		case 0b0101:
			if (c == 0b0) {
				fn = "/B";
			} else {
				fn = "/B + 1";
			}
			break;
		case 0b0110:
			if (c == 0b0) {
				fn = "A";
			} else {
				fn = "A + 1";
			}
			break;
		case 0b0111:
			if (c == 0b0) {
				fn = "/A";
			} else {
				fn = "/A + 1";
			}
			break;
		case 0b1000:
			fn = "A or B";
			break;
		case 0b1001:
			fn = "A xor B";
			break;
		case 0b1010:
			fn = "A equal B";
			break;
		case 0b1011:
			fn = "/A and B";
			break;
		case 0b1100:
			fn = "A or /B";
			break;
		case 0b1101:
			fn = "A or B";
			break;
		case 0b1110:
			fn = "/A or B";
			break;
		case 0b1111:
			fn = "A or /B";
			break;
		}

		if (fn != null) {
			fn = "F = " + fn;
		}

		return fn;
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnFn() {
		for (Listener listener : listeners) {
			listener.onFn(s, c, getFn());
		}
	}

	@Override
	public void onPositiveEdge() {
		if (a == null || b == null || f == null) {
			return;
		}

		int a = this.a.getData();
		int b = this.b.getData();
		int f = 0;

		switch (s) {
		case 0b0000:
			if (c == 0b0) {
				f = 1;
			} else {
				f = 0;
			}
			break;
		case 0b0001:
			if (c == 0b0) {
				f = b - a - 1;
			} else {
				f = b - a;
			}
			break;
		case 0b0010:
			if (c == 0b0) {
				f = a - b - 1;
			} else {
				f = a - b;
			}
			break;
		case 0b0011:
			if (c == 0b0) {
				f = a + b;
			} else {
				f = a + b + 1;
			}
			break;
		case 0b0100:
			if (c == 0b0) {
				f = b;
			} else {
				f = b + 1;
			}
			break;
		case 0b0101:
			if (c == 0b0) {
				f = ~b;
			} else {
				f = ~b + 1;
			}
			break;
		case 0b0110:
			if (c == 0b0) {
				f = a;
			} else {
				f = a + 1;
			}
			break;
		case 0b0111:
			if (c == 0b0) {
				f = ~a;
			} else {
				f = ~a + 1;
			}
			break;
		case 0b1000:
			f = a | b;
			break;
		case 0b1001:
			f = a ^ b;
			break;
		case 0b1010:
			f = a == b ? 1 : 0;
			break;
		case 0b1011:
			f = ~a & b;
			break;
		case 0b1100:
			f = a | ~b;
			break;
		case 0b1101:
			f = a | b;
			break;
		case 0b1110:
			f = ~a | b;
			break;
		case 0b1111:
			f = a | ~b;
			break;
		}

		this.f.setData(f);
	}

	public static interface Listener {
		void onFn(int s, int c, String fn);
	}
}
