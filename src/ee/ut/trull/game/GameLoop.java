package ee.ut.trull.game;

import ee.ut.trull.game.field.GameField;
import ee.ut.trull.game.field.GameField.FieldSlot;
import ee.ut.trull.game.field.GameFieldAnalyser;

public class GameLoop {

	private GameField field;
	private GameFieldAnalyser analyser;
	private GameState state = GameState.CROSS_MOVE;

	public enum GameState {
		CROSS_MOVE, CIRCLE_MOVE, CROSS_WIN, CIRCLE_WIN, DRAW
	}

	public GameLoop(GameField field) {
		this.field = field;
		analyser = new GameFieldAnalyser(field);
	}

	public GameField getField() {
		return field;
	}

	public GameState getState() {
		return state;
	}

	public void handlePress(int x, int y) {
		
		handlePlayerSelection(x, y);
		handleWin();
	}
	
	private void handlePlayerSelection(int x, int y) {
		if (field.getSlot(x, y) == FieldSlot.EMPTY){
			switch (state) {
			case CROSS_MOVE:
				field.setSlot(x, y, FieldSlot.CROSS);
				state = GameState.CIRCLE_MOVE;
				break;
			case CIRCLE_MOVE:
				field.setSlot(x, y, FieldSlot.CIRCLE);
				state = GameState.CROSS_MOVE;
				break;
			default:
				break;
			}
		}
	}

	private void handleWin(){
		switch (analyser.getState()) {
		case CIRCLE_WIN:
			state = GameState.CIRCLE_WIN;
			break;
		case CROSS_WIN:
			state = GameState.CROSS_WIN;
			break;
		case DRAW:
			state = GameState.DRAW;
			break;
		default:
			break;
		}
	}

	public void reset() {
		field.reset();
		state = GameState.CROSS_MOVE;
	}
}
