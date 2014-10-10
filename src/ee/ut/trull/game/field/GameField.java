package ee.ut.trull.game.field;

/**
 * @author Jaan Janno
 */

/**
 * Klass, mis representeerib mänguvälja seisu.
 */

public class GameField {
	
	/**
	 * Enum, mis defineerib, kas mingi mänguvälja
	 * ruudul on rist, ring või tühjus.
	 */

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

	private FieldSlot[][] gameField; // Mänguväli on enumi FieldSlot 2d massiiv.

	public GameField() {
		setGameField(initField()); // Algne tühja välja initsialiseerimine.
	}
	
	/**
	 * Tagastab ruudu koorinaatidel x, y
	 */

	public FieldSlot getSlot(int x, int y) {
		return gameField[x][y];
	}
	
	/**
	 * Tagastab ruudu nr. i
	 */
	
	public FieldSlot getSlot(int i) {
		return gameField[(i - 1) % 3][(i - 1) / 3];
	}
	
	/**
	 * Kirjutab üle ruudu koordinaatidel x, y
	 */
	
	public void setSlot(int x, int y, FieldSlot value) {
		gameField[x][y] = value;
	}
	
	/**
	 * Kirjutab üle ruudu nr. i
	 */
	
	public void setSlot(int i, FieldSlot value) {
		gameField[(i - 1) % 3][(i - 1) / 3] = value;
	}
	
	/**
	 * Tagastab massiivi kõikvõimalikest kolmestest
	 * joontest mänguväljal.
	 */

	public FieldSlot[][] getLines() {
		FieldSlot[][] lines = new FieldSlot[][] { getCol(0), getCol(1),
				getCol(2), getRow(0), getRow(1), getRow(2), getDiag(0),
				getDiag(1) };
		return lines;
	}
	
	/**
	 * Tagastab kõik tulbad mänguväljal.
	 */

	public FieldSlot[] getCol(int x) {
		return gameField[x];
	}
	
	/**
	 * Tagastab kõik read mänguväljal.
	 */

	public FieldSlot[] getRow(int x) {
		return new FieldSlot[] { gameField[0][x], gameField[1][x],
				gameField[2][x] };
	}
	
	/**
	 * Tagastab kõik diagonaalid mänguväljal.
	 */

	public FieldSlot[] getDiag(int x) {
		return new FieldSlot[] { gameField[0][2 * x], gameField[1][1],
				gameField[2][2 - 2 * x] };
	}
	
	/**
	 * Kirjutab üle kogu mänguvälja.
	 */

	private void setGameField(FieldSlot[][] gameField) {
		this.gameField = gameField;
	}
	
	/**
	 * Initsialiseerib mänguvälja tuhjade ruutudega.
	 */

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
	
	/**
	 * Kutsub välja initsialiseerimise.
	 */

	public void reset() {
		gameField = initField();
	}
}
