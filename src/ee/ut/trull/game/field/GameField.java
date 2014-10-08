package ee.ut.trull.game.field;

public class GameField {

	public enum FieldSlot {
		EMPTY, CROSS, CIRCLE;

		@Override
		public String toString() {
			switch (this) {
			case EMPTY:
				return "-";
			case CROSS:
				return "X";
			case CIRCLE:
				return "O";
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	private FieldSlot[][] gameField;

	public GameField() {
		setGameField(initField());
	}

	public FieldSlot getSlot(int x, int y) {
		return gameField[x][y];
	}

	public FieldSlot[][] getLines() {
		FieldSlot[][] lines = new FieldSlot[][] { getCol(0), getCol(1),
				getCol(2), getRow(0), getRow(1), getRow(2), getDiag(0),
				getDiag(1) };
		return lines;
	}

	public FieldSlot[] getRow(int x) {
		return gameField[x];
	}

	public FieldSlot[] getCol(int x) {
		return new FieldSlot[] { gameField[0][x], gameField[1][x],
				gameField[2][x] };
	}

	public FieldSlot[] getDiag(int x) {
		return new FieldSlot[] { gameField[0][2 * x], gameField[1][1],
				gameField[2][2 - 2 * x] };
	}

	public void setSlot(int x, int y, FieldSlot value) {
		gameField[x][y] = value;
	}

	private void setGameField(FieldSlot[][] gameField) {
		this.gameField = gameField;
	}

	private static FieldSlot[][] initField() {
		FieldSlot field[][] = new FieldSlot[3][3];
		for (int i = 0; i < 9; i++)
			field[i % 3][i / 3] = FieldSlot.EMPTY;
		return field;
	}

	@Override
	public String toString() {
		String out = new String();
		for (FieldSlot[] r : initField()) {
			for (FieldSlot s : r) {
				out += s.toString();
			}
			out += "\n";
		}
		return out;
	}

	public void reset() {
		gameField = initField();
	}
}
