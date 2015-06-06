package engine.component;

import engine.bus.Bus32;

public class Printer extends Clock.ImplListener {
	private Bus32 in;

	public void in(Bus32 in) {
		this.in = in;
	}

	@Override
	public void onPositiveEdge() {
		if (in == null) {
			return;
		}

		int data = in.getData();
		System.out.println("[BIN] : " + String.format("%32s", Integer.toBinaryString(data)).replace(' ', '0'));
		System.out.println("[HEX] : " + String.format("%22s", "") + "0x" + String.format("%8s", Integer.toHexString(data)).replace(' ', '0').toUpperCase());
		System.out.println("[DEC] : " + String.format("%32s", data));
	}
}
