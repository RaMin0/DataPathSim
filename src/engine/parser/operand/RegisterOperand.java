package engine.parser.operand;

public class RegisterOperand extends Operand {
	private int number;

	public RegisterOperand(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
