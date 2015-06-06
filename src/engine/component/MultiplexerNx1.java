package engine.component;

import engine.bus.Bus32;

public class MultiplexerNx1 {
	private int s;

	private Bus32[] in;
	private Bus32 out;

	public MultiplexerNx1(int n) {
		in = new Bus32[n];
	}

	public void in(final int i, Bus32 in) {
		this.in[i] = in;
		
		in.addListener(new Bus32.Listener() {
			@Override
			public void onData(int data) {
				if (out != null) {
					if (s == i) {
						out.setData(data);
					}
				}
			}
		});
	}

	public void out(Bus32 out) {
		this.out = out;
	}

	public void setS(int s) {
		this.s = s;
		
		if (in[s] == null || out == null) {
			return;
		}
		
		out.setData(in[s].getData());
	}
}
