package engine.component;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	private int[] rows;

	public Memory() {
		this(8);
	}

	public Memory(int s) {
		rows = new int[1 << s];

		for (int i = 0; i < rows.length; i++) {
			rows[i] = -1;
		}
	}

	public List<Integer> asList() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int row : rows) {
			if (row == -1) {
				break;
			}

			list.add(row);
		}
		return list;
	}

	public int get(int i) {
		return rows[i];
	}

	public void set(int i, int data) {
		rows[i] = data;
	}

	public int size() {
		return rows.length;
	}
}
