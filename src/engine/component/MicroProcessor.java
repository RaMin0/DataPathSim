package engine.component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import engine.DataPath;
import engine.parser.instruction.MacroInstruction;
import engine.parser.instruction.MicroInstruction;
import engine.parser.instruction.MicroInstruction.Signal;

public class MicroProcessor extends Clock.ImplListener {
	private DataPath dataPath;
	private int currentInstruction;
	private Iterator<Integer> instructions;
	private Iterator<MicroInstruction> microInstructions;

	public MicroProcessor(Memory memory, DataPath dataPath) {
		this.dataPath = dataPath;

		instructions = memory.asList().iterator();
		microInstructions = Arrays.asList(new MicroInstruction[0]).iterator();
	}

	@Override
	public void onPositiveEdge() {
		if (instructions == null || microInstructions == null) {
			return;
		}

		MicroInstruction microInstruction = new MicroInstruction();
		try {
			microInstruction = microInstructions.next();
		} catch (NoSuchElementException emi) {
			microInstructions = null;

			try {
				currentInstruction = instructions.next();
				dataPath.bIR.setData(currentInstruction);

				int opcode = currentInstruction >> 6;
				MacroInstruction macroInstruction = MacroInstruction.all.get(opcode);
				microInstructions = Arrays.asList(macroInstruction.getMicroInstructions()).iterator();
			} catch (NoSuchElementException ei) {
				instructions = null;
			}
		}
		
		dataPath.cMuxRegisterSelect.setS(microInstruction.get(Signal.RSEL));
		dataPath.cRegisterMemory.setW(microInstruction.get(Signal.WRITE));
		dataPath.cRegisterA.setE(microInstruction.get(Signal.EA));
		dataPath.cRegisterB.setE(microInstruction.get(Signal.EB));
		dataPath.cALU.setS(
				microInstruction.get(Signal.S3) << 3
						| microInstruction.get(Signal.S2) << 2
						| microInstruction.get(Signal.S1) << 1
						| microInstruction.get(Signal.S0)
				);
		dataPath.cALU.setC(microInstruction.get(Signal.C0));
		dataPath.cRegisterF.setE(microInstruction.get(Signal.EF));
	}
}
