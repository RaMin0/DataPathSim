package engine.parser.instruction;

import engine.parser.operand.Operand;
import engine.parser.operand.RegisterOperand;

public class Instruction {
	private MacroInstruction macroInstruction;
	private Operand[] operands;

	public Instruction(MacroInstruction macroInstruction, Operand[] operands) {
		this.macroInstruction = macroInstruction;
		this.operands = operands;
	}

	public MacroInstruction getMacroInstruction() {
		return macroInstruction;
	}

	public Operand[] getOperands() {
		return operands;
	}

	public MemoryInstruction toMemoryInstruction() {
		MemoryInstruction memoryInsturction = new MemoryInstruction();

		memoryInsturction.setOpcode(MacroInstruction.all.indexOf(macroInstruction));

		for (int i = 0, ri = 0; i < operands.length; i++) {
			if (operands[i] instanceof RegisterOperand) {
				memoryInsturction.setOperand(ri++, ((RegisterOperand) operands[i]).getNumber());
			}
		}

		return memoryInsturction;
	}
}
