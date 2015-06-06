package engine.parser.instruction;

import java.util.ArrayList;

public class MemoryInstruction {
	private int opcode;
	private int[] operands;
	private Integer[] payload;

	public MemoryInstruction() {
		operands = new int[2];
		payload = new Integer[2];
	}

	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	public void setOperand(int i, int data) {
		operands[i] = data;
	}

	public void setPayload(int i, int data) {
		payload[i] = data;
	}

	public int[] toMemoryData() {
		ArrayList<Integer> memoryDataList = new ArrayList<>();

		memoryDataList.add(opcode << 6
				| operands[0] << 3
				| operands[1]);

		for (int i = 0; i < payload.length; i++) {
			if (payload[i] != null) {
				memoryDataList.add(payload[i]);
			}
		}

		int[] memoryData = new int[memoryDataList.size()];
		for (int i = 0; i < memoryDataList.size(); i++) {
			memoryData[i] = memoryDataList.get(i);
		}
		return memoryData;
	}
}
