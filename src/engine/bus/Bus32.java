package engine.bus;

import java.util.ArrayList;

public class Bus32 {
	private ArrayList<Listener> listeners = new ArrayList<>();

	private Bus16 h = new Bus16();
	private Bus16 l = new Bus16();

	public Bus32() {
		Bus16.Listener listener = new Bus16.Listener() {
			@Override
			public void onData(int data) {
				notifyOnData();
			}
		};

		h.addListener(listener);
		l.addListener(listener);
	}

	public Bus16 getH() {
		return h;
	}

	public Bus16 getL() {
		return l;
	}

	public int getData() {
		return h.getData() << 16 | l.getData();
	}

	public void setData(int data) {
		setDataHigh(data);
		setDataLow(data);
	}

	public int getDataHigh() {
		return h.getData();
	}

	public void setDataHigh(int data) {
		h.setData(data >> 16);
	}

	public int getDataLow() {
		return l.getData();
	}

	public void setDataLow(int data) {
		l.setData(data);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnData() {
		for (Listener listener : listeners) {
			listener.onData(getData());
		}
	}

	public static interface Listener {
		void onData(int data);
	}
}
