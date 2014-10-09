package ee.ut.trull.game;

import ee.ut.trull.game.ai.AiPlayer;
import ee.ut.trull.game.field.GameField;
import ee.ut.trull.game.field.GameField.FieldSlot;
import ee.ut.trull.game.field.GameFieldAnalyser;

public class GameLoop {

	private GameField field;
	private GameFieldAnalyser analyser;
	private GameState state = GameState.CROSS_MOVE;
	private boolean aiBegins = false;
	private AiPlayer ai;

	public enum GameState {
		CROSS_MOVE, CIRCLE_MOVE, CROSS_WIN, CIRCLE_WIN, DRAW
	}

	public GameLoop(GameField field) {
		this.field = field;
		analyser = new GameFieldAnalyser(field);
		ai = new AiPlayer(field);
	}

	public GameField getField() {
		return field;
	}

	public GameState getState() {
		return state;
	}

	public void setAiBegins(boolean aiBegins) {
		this.aiBegins = aiBegins;
	}

	public void handlePress(int x, int y) {
		
		handlePlayerSelection(x, y);
		handleWin();
		if (aiBegins && state == GameState.CROSS_MOVE){
			ai.makeMove();
			state = GameState.CIRCLE_MOVE;
		}			
		if (!aiBegins && state == GameState.CIRCLE_MOVE){
			ai.makeMove();
			state = GameState.CROSS_MOVE;
		}
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

	public void init() {
		field.reset();	
		if(aiBegins){
			ai.makeMove();
			state = GameState.CIRCLE_MOVE;
		}
		else {
			state = GameState.CROSS_MOVE;
		}
	}
}
