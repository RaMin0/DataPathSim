package engine.component;

import java.util.ArrayList;
import java.util.Arrays;

public class Clock {
	public static final Engine DEFAULT_ENGINE = new Engine() {
		public static final long SLEEP_MILLIS = 1000;

		private boolean operating;

		@Override
		public void operate(final Clock clock) {
			operating = true;

			new Thread(new Runnable() {
				@Override
				public void run() {
					do {
						clock.notifyOnPositiveEdge();
						clock.notifyOnNegativeEdge();						
						
						try {
							Thread.sleep(SLEEP_MILLIS);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (operating);
				}

			}).start();
		}

		@Override
		public void halt() {
			operating = false;
		}
	};

	private ArrayList<Listener> listeners = new ArrayList<>();;
	private Engine engine;

	public Clock() {
		this(DEFAULT_ENGINE);
	}

	public Clock(Engine engine) {
		this.engine = engine;
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void addListeners(Listener[] listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	public void notifyOnPositiveEdge() {
		for (Listener listener : listeners) {
			listener.onPositiveEdge();
		}
	}

	public void notifyOnNegativeEdge() {
		for (Listener listener : listeners) {
			listener.onNegativeEdge();
		}
	}

	public void start() {
		engine.operate(this);
	}

	public void stop() {
		engine.halt();
	}

	public static interface Listener {
		void onPositiveEdge();

		void onNegativeEdge();
	}

	public static interface Engine {
		void operate(Clock clock);

		void halt();
	}

	public static class ImplListener implements Listener {
		@Override
		public void onPositiveEdge() {
		}

		@Override
		public void onNegativeEdge() {
		}
	}
}
