package engine.parser;

import java.util.ArrayList;

import engine.parser.instruction.Instruction;
import engine.parser.instruction.MacroInstruction;
import engine.parser.operand.Operand;
import engine.parser.operand.RegisterOperand;

public class Assembler {
	public Instruction[] parse(String asm) {
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();

		for (String instructionString : asm.split("[\\r\\n]+")) {
			String[] instructionParts = instructionString.split("[,\\s]+");

			String instructionFormat = null;
			Operand[] instructionOperands = new Operand[instructionParts.length - 1];

			for (int i = 1; i < instructionParts.length; i++) {
				instructionParts[i] = instructionParts[i].toUpperCase();
				char operandType = instructionParts[i].charAt(0);
				String operandData = instructionParts[i].substring(1);

				Operand instructionOperand = null;
				switch (operandType) {
				case 'R':
					instructionOperand = new RegisterOperand(Integer.parseInt(operandData));
					break;
				}

				instructionOperands[i - 1] = instructionOperand;
				instructionParts[i] = String.valueOf(operandType);

				if (instructionFormat == null) {
					instructionFormat = instructionParts[0] + " " + instructionParts[i];
				} else {
					instructionFormat += ", " + instructionParts[i];
				}
			}

			for (int i = 0; i < MacroInstruction.all.size(); i++) {
				MacroInstruction macroInstruction = MacroInstruction.all.get(i);
				if (macroInstruction.getFormat().equalsIgnoreCase(instructionFormat)) {
					Instruction instruction = new Instruction(macroInstruction, instructionOperands);
					instructions.add(instruction);
					break;
				}
			}
		}

		return instructions.toArray(new Instruction[instructions.size()]);
	}
}
