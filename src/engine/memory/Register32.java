package engine.memory;

import java.util.ArrayList;

import engine.bus.Bus32;
import engine.component.Clock;

public class Register32 extends Clock.ImplListener {
	private ArrayList<Listener> listeners = new ArrayList<>();

	private Register16 h, l;

	public Register32() {
		h = new Register16();
		l = new Register16();
	}

	public void in(Bus32 in) {
		h.in(in.getH());
		l.in(in.getL());
	}

	public void out(Bus32 out) {
		h.out(out.getH());
		l.out(out.getL());

		out.addListener(new Bus32.Listener() {
			@Override
			public void onData(int data) {
				notifyOnData(data);
			}
		});
	}

	public void setE(int e) {
		h.setE(e);
		l.setE(e);

		notifyOnE(e);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnE(int e) {
		for (Listener listener : listeners) {
			listener.onE(this, e);
		}
	}

	private void notifyOnData(int data) {
		for (Listener listener : listeners) {
			listener.onData(this, data);
		}
	}

	@Override
	public void onPositiveEdge() {
		h.onPositiveEdge();
		l.onPositiveEdge();
	}

	public static interface Listener {
		void onE(Register32 register, int e);

		void onData(Register32 register, int data);
	}
}
