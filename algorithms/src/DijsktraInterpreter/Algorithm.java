package DijsktraInterpreter;

import java.util.Stack;

public class Algorithm {
	
	private Stack<String> operationStack;
	private Stack<Double> valuesStack;
	
	public Algorithm(){
		this.operationStack = new Stack<>();
		this.valuesStack = new Stack<>();
		
	}
	
	public void interpretExpression(String expression){
		String[] expressionArray = expression.split(" ");
		for(String s: expressionArray){
			if(s.equals("(")){
				//do nothing
			} else if (s.equals("+")) {
				this.operationStack.push(s);
			} else if (s.equals("*")) {
				this.operationStack.push(s);
			} else if (s.equals(")")) {
				String operation = this.operationStack.pop();
				if(operation.equals("+")){
					this.valuesStack.push(this.valuesStack.pop() + this.valuesStack.pop());
				} else if (operation.equals("*")) {
					this.valuesStack.push(this.valuesStack.pop() * this.valuesStack.pop());
				}
			} else {
				this.valuesStack.push(Double.parseDouble(s));
			}
		}
	}
	
	public void result(){
		System.out.println(this.valuesStack.pop());
	}
}
