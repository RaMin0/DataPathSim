package engine;

import engine.bus.Bus16;
import engine.bus.Bus32;
import engine.component.ALU;
import engine.component.AdapterIR;
import engine.component.Clock;
import engine.component.MultiplexerNx1;
import engine.memory.Register16;
import engine.memory.Register32;
import engine.memory.RegisterMemory;

public class DataPath {
	public Register16 cRegisterIR;
	public AdapterIR cAdapterIR;
	public MultiplexerNx1 cMuxRegisterSelect;
	public RegisterMemory cRegisterMemory;
	public Register32 cRegisterA;
	public Register32 cRegisterB;
	public ALU cALU;
	public Register32 cRegisterF;

	public Bus16 bIR;
	public Bus16 bIR_ADAPTER;
	public Bus32 bIR_RSEL_0;
	public Bus32 bIR_RSEL_1;
	public Bus32 bRSEL_REG;
	public Bus32 bREG_A_B;
	public Bus32 bA_ALU;
	public Bus32 bB_ALU;
	public Bus32 bALU_F;
	public Bus32 bF_REG;

	public DataPath() {
		initialize();
		connect();
	}

	private void initialize() {
		cRegisterIR = new Register16();
		cMuxRegisterSelect = new MultiplexerNx1(2);
		cRegisterMemory = new RegisterMemory();
		cRegisterA = new Register32();
		cRegisterB = new Register32();
		cALU = new ALU();
		cRegisterF = new Register32();
	}

	private void connect() {
		bIR = new Bus16();
		cRegisterIR.in(bIR);

		bIR_ADAPTER = new Bus16();
		cRegisterIR.out(bIR_ADAPTER);
		cAdapterIR = new AdapterIR();
		cAdapterIR.in(bIR_ADAPTER);

		bIR_RSEL_0 = new Bus32();
		cAdapterIR.out_reg_0(bIR_RSEL_0);
		cMuxRegisterSelect.in(0, bIR_RSEL_0);

		bIR_RSEL_1 = new Bus32();
		cAdapterIR.out_reg_1(bIR_RSEL_1);
		cMuxRegisterSelect.in(1, bIR_RSEL_1);

		bRSEL_REG = new Bus32();
		cMuxRegisterSelect.out(bRSEL_REG);
		cRegisterMemory.s(bRSEL_REG);

		bREG_A_B = new Bus32();
		cRegisterMemory.out(bREG_A_B);
		cRegisterA.in(bREG_A_B);
		cRegisterB.in(bREG_A_B);

		bA_ALU = new Bus32();
		cRegisterA.out(bA_ALU);
		cALU.in_a(bA_ALU);

		bB_ALU = new Bus32();
		cRegisterB.out(bB_ALU);
		cALU.in_b(bB_ALU);

		bALU_F = new Bus32();
		cALU.out_f(bALU_F);
		cRegisterF.in(bALU_F);

		bF_REG = new Bus32();
		cRegisterF.out(bF_REG);
		cRegisterMemory.in(bF_REG);
	}

	public void clock(Clock clock) {
		cRegisterIR.setE(0b1);

		clock.addListeners(new Clock.Listener[] {
				cRegisterIR,
				cRegisterMemory,
				cRegisterA, cRegisterB,
				cALU,
				cRegisterF
		});
	}
}
