package ee.ut.trull.game.field;

public class GameFieldAnalyser {

	public enum WinState {
		NO_WIN, CROSS_WIN, CIRCLE_WIN
	}

	private GameField field;

	public GameFieldAnalyser(GameField field) {
		this.field = field;
	}

	public WinState getState() {
		
		return WinState.NO_WIN;
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
