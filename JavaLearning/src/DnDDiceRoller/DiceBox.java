package DnDDiceRoller;

public class DiceBox {
	Dice[] dice;
	int i;

	public DiceBox(int[] dice){
		this.dice = new Dice[dice.length];
		for(i = 0; i < dice.length; i++){
			this.dice[i] = new Dice(dice[i]);
		}
	}
	
	public void shakeBox(){
		for(i = 0; i < this.dice.length; i++){
			this.dice[i].roll();
		}
	}
	
	public void checkResults(){
		for(i = 0; i < this.dice.length; i++){
			System.out.print(this.dice[i].checkRoll() + ", ");
		}
	}
	
	@Override
	public String toString(){
		String outString = new String();
		for(i = 0; i < this.dice.length; i++){
			outString += "K" + this.dice[i].getSides() + ": " + this.dice[i].checkRoll();
			if( i != this.dice.length - 1 ){
				outString += ", ";
			}
		}
		return outString;
	}
}
