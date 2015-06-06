package engine.parser.instruction;

import java.util.ArrayList;

import engine.parser.instruction.MicroInstruction.Signal;
import engine.parser.operand.Operand;

public class MacroInstruction {
	public static ArrayList<MacroInstruction> all = new ArrayList<>();

	private String format;
	private Operand[] operands;
	private MicroInstruction[] microInstructions;

	public MacroInstruction(String format, MicroInstruction[] microInstructions) {
		this.format = format;
		this.microInstructions = microInstructions;
	}

	public String getFormat() {
		return format;
	}

	public Operand[] getOperands() {
		return operands;
	}

	public MicroInstruction[] getMicroInstructions() {
		return microInstructions;
	}

	static {
		all.add(new MacroInstruction(
				"MOV0 R",
				new MicroInstruction[] {
						new MicroInstruction(
								Signal.C0,
								Signal.EF
						),
						new MicroInstruction(
								Signal.WRITE
						)
				}));
		all.add(new MacroInstruction(
				"MOV1 R",
				new MicroInstruction[] {
						new MicroInstruction(
								Signal.EF
						),
						new MicroInstruction(
								Signal.WRITE
						)
				}));
		all.add(new MacroInstruction(
				"MOV2 R",
				new MicroInstruction[] {
						new MicroInstruction(
								Signal.EF
						),
						new MicroInstruction(
								Signal.WRITE
						),
						new MicroInstruction(
								Signal.EA,
								Signal.EB
						),
						new MicroInstruction(
								Signal.S1, Signal.S0,
								Signal.EF
						),
						new MicroInstruction(
								Signal.WRITE
						),
				}));
		all.add(new MacroInstruction(
				"ADD R, R",
				new MicroInstruction[] {
						new MicroInstruction(
								Signal.EA
						),
						new MicroInstruction(
								Signal.RSEL,
								Signal.EB
						),
						new MicroInstruction(
								Signal.S1, Signal.S0,
								Signal.EF
						),
						new MicroInstruction(
								Signal.WRITE
						)
				}));
	}
}
