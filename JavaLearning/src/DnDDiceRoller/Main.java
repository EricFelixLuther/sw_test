package DnDDiceRoller;

public class Main {
	
	public static void main(String[] args) {
		//Dice die = new Dice(6);
		//die.roll();
		//System.out.println(die.checkRoll());
		int[] dices = {6, 6, 20, 20};
		DiceBox box = new DiceBox(dices);
		box.shakeBox();
		System.out.println(box);

	}

}
