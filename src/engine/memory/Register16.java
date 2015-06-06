package engine.memory;

import java.util.ArrayList;

import engine.bus.Bus16;
import engine.component.Clock;

public class Register16 extends Clock.ImplListener {
	private ArrayList<Listener> listeners = new ArrayList<>();

	private int e;
	private int data;

	private Bus16 in, out;

	public void in(Bus16 in) {
		this.in = in;
	}

	public void out(Bus16 out) {
		this.out = out;
		out.addListener(new Bus16.Listener() {
			@Override
			public void onData(int data) {
				notifyOnData();
			}
		});
	}

	public void setE(int e) {
		this.e = e;

		notifyOnE();
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnE() {
		for (Listener listener : listeners) {
			listener.onE(this, e);
		}
	}

	private void notifyOnData() {
		for (Listener listener : listeners) {
			listener.onData(this, data);
		}
	}

	@Override
	public void onPositiveEdge() {
		if (in == null || out == null) {
			return;
		}

		if (e == 0b1) {
			data = in.getData();
			out.setData(data);
		}
	}

	public static interface Listener {
		void onE(Register16 register, int e);

		void onData(Register16 register, int data);
	}
}
