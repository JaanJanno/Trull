package ee.ut.trull.game.ai;

import ee.ut.trull.game.field.GameField;
import ee.ut.trull.game.field.GameField.FieldSlot;

/**
 * @author Jaan Janno
 */

/**
 * Klass, milles sisalduvad reeglid, mille järgi
 * arvuti mängija vastu mängib.
 */

public class AiPlayer {
	
	private GameField field;
	
	public AiPlayer(GameField field) {
		this.field = field;
	}
	
	/**
	 * Igale käigunumbrile on olemas vastavad instruktsioonid,
	 * kuidas käituda.
	 */

	public void makeMove() {
		switch (getMoveNumber()) {
		case 1:
			move1(); break;
		case 2:
			move2(); break;
		case 3:
			move3(); break;
		case 4:
			move4(); break;
		case 5:
			move5(); break;
		case 6:
			move6(); break;
		case 7:
			move789(FieldSlot.CROSS, FieldSlot.CIRCLE); break;
		case 8:
			move789(FieldSlot.CIRCLE, FieldSlot.CROSS); break;
		case 9:
			move789(FieldSlot.CROSS, FieldSlot.CIRCLE); break;
		}
	}
	
	/**
	 * Kui võimalik, siis võidab. Peatab vastase võidu, kui
	 * vaja. Muidu läheb suvalisse kohta.
	 */

	private void move789(FieldSlot player, FieldSlot enemy) {
		if (tryWin(player) != 0) {
			field.setSlot(tryWin(player), player);
		} else if (tryWin(enemy) != 0) {
			field.setSlot(tryWin(enemy), player);
		} else {
			for (int i = 1; i < 10; i++) {
				if (field.getSlot(i) == FieldSlot.EMPTY) {
					field.setSlot(i, player);
					break;
				}
			}
		}
	}
	
	/**
	 * Kui võimalik, siis võidab. Peatab vastase võidu, kui
	 * vaja. Muidu rakendab find2().
	 */

	private void move6() { // CIRCLE
		if (tryWin(FieldSlot.CIRCLE) != 0) {
			field.setSlot(tryWin(FieldSlot.CIRCLE), FieldSlot.CIRCLE);
		} else if (tryWin(FieldSlot.CROSS) != 0) {
			field.setSlot(tryWin(FieldSlot.CROSS), FieldSlot.CIRCLE);
		} else {
			find2();
		}
	}
	
	/**
	 * Kui võimalik, teeb võidukäigu, kui ei
	 * blokeerib vastase võidu. Muidu läheb üles
	 * paremale (ruut 3) või alla vasakule. (ruut 7)
	 */

	private void move5() { // CROSS
		if (tryWin(FieldSlot.CROSS) != 0) {
			field.setSlot(tryWin(FieldSlot.CROSS), FieldSlot.CROSS);
		} else if (tryWin(FieldSlot.CIRCLE) != 0) {
			field.setSlot(tryWin(FieldSlot.CIRCLE), FieldSlot.CROSS);
		} else {
			if (field.getSlot(0, 2) == FieldSlot.EMPTY)
				field.setSlot(0, 2, FieldSlot.CROSS);
			else
				field.setSlot(2, 0, FieldSlot.CROSS);
		}
	}
	
	/**
	 * Blokeerib vajadusel vastase võidu.
	 * Seejärel kontrollib, kas on kahvli tekkimine
	 * võimalik. Kui ei, rakendab find2().
	 */

	private void move4() { // CIRCLE
		if (tryWin(FieldSlot.CROSS) != 0) {
			field.setSlot(tryWin(FieldSlot.CROSS), FieldSlot.CIRCLE);
		} else if (checkFork()) {

		} else {
			find2();
		}
	}
	
	/**
	 * Blokeerib kahvli tekkimise.
	 */

	private boolean checkFork() {
		if (field.getSlot(5) == FieldSlot.CROSS
				&& field.getSlot(9) == FieldSlot.CROSS) {
			field.setSlot(3, FieldSlot.CIRCLE);
			return true;
		}
		return false;
	}
	
	/**
	 * Üritab käia alla paremale. (ruut 9)
	 * Kui ei saa, käib üles paremale.
	 */

	private void move3() { // CROSS
		if (field.getSlot(2, 2) == FieldSlot.EMPTY) {
			field.setSlot(2, 2, FieldSlot.CROSS);
		} else {
			field.setSlot(2, 0, FieldSlot.CROSS);
		}
	}
	
	/**
	 * Proovib käia keskele (ruut 5). Kui ei saa,
	 * käib üles vasakule. (ruut 1)
	 */

	private void move2() { // CIRCLE
		if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
			field.setSlot(1, 1, FieldSlot.CIRCLE);
		} else {
			field.setSlot(0, 0, FieldSlot.CIRCLE);
		}
	}
	
	/**
	 * Käib keskele (ruut 5).
	 */

	private void move1() { // CROSS
		field.setSlot(1, 1, FieldSlot.CROSS);
	}
	
	/**
	 * Tagastab kas sisendiks antud kujundil
	 * on mõnes reas võimalik võita.
	 */

	private int tryWin(FieldSlot player) {

		if (isWon(field.getRow(0), player)) {
			if (field.getSlot(0, 0) == FieldSlot.EMPTY) {
				return 1;
			}
			if (field.getSlot(1, 0) == FieldSlot.EMPTY) {
				return 2;
			}
			if (field.getSlot(2, 0) == FieldSlot.EMPTY) {
				return 3;
			}
		}

		if (isWon(field.getRow(1), player)) {
			if (field.getSlot(0, 1) == FieldSlot.EMPTY) {
				return 4;
			}
			if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
				return 5;
			}
			if (field.getSlot(2, 1) == FieldSlot.EMPTY) {
				return 6;
			}
		}

		if (isWon(field.getRow(2), player)) {
			if (field.getSlot(0, 2) == FieldSlot.EMPTY) {
				return 7;
			}
			if (field.getSlot(1, 2) == FieldSlot.EMPTY) {
				return 8;
			}
			if (field.getSlot(2, 2) == FieldSlot.EMPTY) {
				return 9;
			}
		}

		if (isWon(field.getCol(0), player)) {
			if (field.getSlot(0, 0) == FieldSlot.EMPTY) {
				return 1;
			}
			if (field.getSlot(0, 1) == FieldSlot.EMPTY) {
				return 4;
			}
			if (field.getSlot(0, 2) == FieldSlot.EMPTY) {
				return 7;
			}
		}

		if (isWon(field.getCol(1), player)) {
			if (field.getSlot(1, 0) == FieldSlot.EMPTY) {
				return 2;
			}
			if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
				return 5;
			}
			if (field.getSlot(1, 2) == FieldSlot.EMPTY) {
				return 8;
			}
		}

		if (isWon(field.getCol(2), player)) {
			if (field.getSlot(2, 0) == FieldSlot.EMPTY) {
				return 3;
			}
			if (field.getSlot(2, 1) == FieldSlot.EMPTY) {
				return 6;
			}
			if (field.getSlot(2, 2) == FieldSlot.EMPTY) {
				return 9;
			}
		}

		if (isWon(field.getDiag(0), player)) {
			if (field.getSlot(0, 0) == FieldSlot.EMPTY) {
				return 1;
			}
			if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
				return 5;
			}
			if (field.getSlot(2, 2) == FieldSlot.EMPTY) {
				return 9;
			}
		}

		if (isWon(field.getDiag(1), player)) {
			if (field.getSlot(0, 2) == FieldSlot.EMPTY) {
				return 7;
			}
			if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
				return 5;
			}
			if (field.getSlot(2, 0) == FieldSlot.EMPTY) {
				return 3;
			}
		}

		return 0;
	}
	
	/**
	 * Tagasta, kas antud reas on võimalik võita.
	 */

	private boolean isWon(FieldSlot[] line, FieldSlot player) {
		int eCount = 0;
		int pCount = 0;
		for (int i = 0; i < 3; i++) {
			if (line[i] == FieldSlot.EMPTY)
				eCount++;
			if (line[i] == player)
				pCount++;
		}
		if (eCount == 1 && pCount == 2)
			return true;
		return false;
	}
	
	/**
	 * Kui võimalik valib keskmise ruudu, kui ei
	 * siis eelistatavalt suvalise mittenurkmise.
	 */

	private void find2() {
		if (field.getSlot(1, 1) == FieldSlot.EMPTY) {
			field.setSlot(1, 1, FieldSlot.CIRCLE);
		} else {
			if (field.getSlot(2) == FieldSlot.EMPTY)
				field.setSlot(2, FieldSlot.CIRCLE);
			else if (field.getSlot(4) == FieldSlot.EMPTY)
				field.setSlot(4, FieldSlot.CIRCLE);
			else if (field.getSlot(6) == FieldSlot.EMPTY)
				field.setSlot(6, FieldSlot.CIRCLE);
			else if (field.getSlot(8) == FieldSlot.EMPTY)
				field.setSlot(8, FieldSlot.CIRCLE);
			else if (field.getSlot(1) == FieldSlot.EMPTY)
				field.setSlot(1, FieldSlot.CIRCLE);
			else if (field.getSlot(3) == FieldSlot.EMPTY)
				field.setSlot(3, FieldSlot.CIRCLE);
			else if (field.getSlot(7) == FieldSlot.EMPTY)
				field.setSlot(7, FieldSlot.CIRCLE);
			else if (field.getSlot(9) == FieldSlot.EMPTY)
				field.setSlot(9, FieldSlot.CIRCLE);
		}
	}
	
	/**
	 * Arvutab, mitmes käik on käsil.
	 */

	private int getMoveNumber() {
		int num = 10;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (field.getSlot(x, y) == FieldSlot.EMPTY)
					num--;
			}
		}
		return num;
	}
}
