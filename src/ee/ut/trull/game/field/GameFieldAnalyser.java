package ee.ut.trull.game.field;

import ee.ut.trull.game.field.GameField.FieldSlot;

public class GameFieldAnalyser {

	public enum WinState {
		NO_WIN, CROSS_WIN, CIRCLE_WIN, DRAW
	}

	private GameField field;

	public GameFieldAnalyser(GameField field) {
		this.field = field;
	}

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

	private boolean checkDraw() {
		for (int y = 0; y<3; y++){		
			for (int x = 0; x<3; x++){
				if(field.getSlot(x, y) == FieldSlot.EMPTY)
					return false;
			}
		}
		return true;
	}

	private boolean compareSlots(GameField.FieldSlot[] slots) {
		for (GameField.FieldSlot slot : slots) {
			if (slot != slots[0]) {
				return false;
			}
		}
		return true;
	}
}
