package engine.memory;

import java.util.ArrayList;
import java.util.Arrays;

import engine.bus.Bus32;
import engine.component.Clock;
import engine.component.MultiplexerNx1;

public class RegisterMemory extends Clock.ImplListener {
	private ArrayList<Listener> listeners = new ArrayList<>();
	private Register32[] memory;
	private MultiplexerNx1 mux_out;

	private int s;
	private int w;

	private Bus32[] out_memory;

	public RegisterMemory() {
		memory = new Register32[8];
		out_memory = new Bus32[8];
		mux_out = new MultiplexerNx1(8);

		for (int i = 0; i < memory.length; i++) {
			memory[i] = new Register32();
			out_memory[i] = new Bus32();

			memory[i].addListener(new Register32.Listener() {
				@Override
				public void onE(Register32 register, int e) {

				}

				@Override
				public void onData(Register32 register, int data) {
					notifyOnData(register, data);
				}
			});

			memory[i].out(out_memory[i]);
			mux_out.in(i, out_memory[i]);
		}
	}

	public void s(Bus32 s) {
		s.addListener(new Bus32.Listener() {
			@Override
			public void onData(int data) {
				setS(data);
			}
		});
	}

	public void in(Bus32 in) {
		for (Register32 register : memory) {
			register.in(in);
		}
	}

	public void out(Bus32 out) {
		mux_out.out(out);
	}

	public void setS(int s) {
		this.s = s;
		mux_out.setS(s);

		notifyOnS();
	}

	public void setW(int w) {
		this.w = w;

		notifyOnW();
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyOnS() {
		for (Listener listener : listeners) {
			listener.onS(s);
		}
	}

	private void notifyOnW() {
		for (Listener listener : listeners) {
			listener.onW(w);
		}
	}

	private void notifyOnData(Register32 register, int data) {
		int i = Arrays.asList(memory).indexOf(register);
		for (Listener listener : listeners) {
			listener.onData(i, data);
		}
	}

	@Override
	public void onPositiveEdge() {
		for (int i = 0; i < memory.length; i++) {
			memory[i].setE(w == 0b1 && i == s ? 0b1 : 0b0);
			memory[i].onPositiveEdge();
		}
	}

	public static interface Listener {
		void onS(int s);

		void onW(int w);

		void onData(int i, int data);
	}
}
