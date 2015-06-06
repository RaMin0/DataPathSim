package engine.component;

import engine.bus.Bus16;
import engine.bus.Bus32;

public class AdapterIR {
	private Bus32 out_opcode, out_reg_0, out_reg_1;

	public void in(Bus16 in) {
		in.addListener(new Bus16.Listener() {
			@Override
			public void onData(int data) {
				if (out_opcode != null) {
					out_opcode.setData((data >> 6) & 0x3FF);
				}

				if (out_reg_0 != null) {
					out_reg_0.setData((data >> 3) & 0x7);
				}

				if (out_reg_1 != null) {
					out_reg_1.setData(data & 0x7);
				}
			}
		});
	}

	public void out_opcode(Bus32 out_opcode) {
		this.out_opcode = out_opcode;
	}

	public void out_reg_0(Bus32 out_reg_0) {
		this.out_reg_0 = out_reg_0;
	}

	public void out_reg_1(Bus32 out_reg_1) {
		this.out_reg_1 = out_reg_1;
	}
}
