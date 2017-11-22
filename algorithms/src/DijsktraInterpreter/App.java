package DijsktraInterpreter;

public class App {

	public static void main(String[] args) {
		Algorithm algorithm = new Algorithm();
		
		algorithm.interpretExpression("( ( 1 + 8 ) * ( 2 + 1 ) )");
		algorithm.result();

	}

}
