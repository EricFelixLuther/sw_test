package DnDDiceRoller;

public class Dice {

	private int sides;
	private int value;

	public Dice(int sides){
		this.sides = sides;
	}
	
	public void roll(){
		//System.out.println(System.currentTimeMillis()%(this.sides + 1)-1);
		this.value = (int)(Math.random() * this.sides) + 1;
	}
	
	public int checkRoll(){
		return this.value;
	}
	
	public int getSides(){
		return this.sides;
	}
}
