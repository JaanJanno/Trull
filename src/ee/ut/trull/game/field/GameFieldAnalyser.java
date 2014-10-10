package ee.ut.trull.game.field;

import ee.ut.trull.game.field.GameField.FieldSlot;

/**
 * @author Jaan Janno
 */

/**
 * Klass, mis analüüsib mänguvälja seisu.
 */

public class GameFieldAnalyser {

	/**
	 * Mänguseisu defineerib enum, mille
	 * väärtus võib olla kas, risti või ringi võit,
	 * viik või lõpuolukorra puudumine.
	 */
	
	public enum WinState {
		NO_WIN, CROSS_WIN, CIRCLE_WIN, DRAW
	}

	private GameField field; // Analüüsitava mänguvälja isend.

	public GameFieldAnalyser(GameField field) {
		this.field = field;
	}

	/**
	 * Analüüsib mänguvälja ja tagastab selle oleku.
	 */
	
	public WinState getState() {
		for (FieldSlot[] line: field.getLines()){
			if (compareSlots(line)){
				if(line[0] == FieldSlot.CROSS)
					return WinState.CROSS_WIN;
				if(line[0] == FieldSlot.CIRCLE)
					return WinState.CIRCLE_WIN;
			}
		}
		if (checkDraw()){
			return WinState.DRAW;
		}
		
		return WinState.NO_WIN;
	}
	
	/**
	 * Kontrollib kas tegemist on viigiga.
	 */

	private boolean checkDraw() {
		for (int y = 0; y<3; y++){		
			for (int x = 0; x<3; x++){
				if(field.getSlot(x, y) == FieldSlot.EMPTY)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Tagastab, kas sisendiks saadud ruutude massiivi kõik
	 * kõik elemendid on võrdsed.
	 */

	private boolean compareSlots(GameField.FieldSlot[] slots) {
		for (GameField.FieldSlot slot : slots) {
			if (slot != slots[0]) {
				return false;
			}
		}
		return true;
	}
}
