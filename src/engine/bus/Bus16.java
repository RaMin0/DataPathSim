package engine.bus;

import java.util.ArrayList;

public class Bus16 {
	private int data;
	private ArrayList<Listener> listeners = new ArrayList<>();

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data & 0xFFFF;

		notifyOnData();
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnData() {
		for (Listener listener : listeners) {
			listener.onData(data);
		}
	}

	public static interface Listener {
		void onData(int data);
	}
}
