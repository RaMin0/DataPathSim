package engine.component;

import engine.bus.Bus32;

public class RandomFeeder extends Clock.ImplListener {
	private Bus32 out;

	public void out(Bus32 out) {
		this.out = out;
	}

	@Override
	public void onPositiveEdge() {
		if (out == null) {
			return;
		}

		int data = (int) (Math.random() * 10);
		out.setData(data);
	}
}
