package swTest;
import java.util.Scanner;

public class app {

	public static Stack current_plain;
	public static Int_Stack current_plain_players;
	public static int black_points;  // player 1
	public static int white_points;  // player 2
	public static int map_size;
	public static Node[][] map;

	/*
	 * Answer to 1st TC is 3 - 11 = -8
	 * Answer to 2nd TC is 0 - 0 = 0
	 */
	
	public static class Node {
		public int x;
		public int y;
		public int player;
		public boolean visited;
		
		public Node(int x, int y, int p) {
			this.x = x;
			this.y = y;
			this.player = p;
			this.visited = false;
		}
		@Override
		public String toString(){
			if(this.visited == true){
				return "P" + this.player + "O" + "@" + this.x + "," + this.y;
			} else {
				return "P" + this.player + "X" + "@" + this.x + "," + this.y;
			}
			 
			
		}
	}
	
	public static class Int_Stack {
		public int top;
		public int[] stack;
		public boolean is_homogenic;
		
		public Int_Stack(int size) {
			this.stack = new int[size];
			this.top = -1;
			this.is_homogenic = true;
		}
		public void push(int x){
			int last_item = this.peek();
			if(last_item >= 0) {
				if(last_item != x){
					this.is_homogenic = false;
			}
			}
			this.top += 1;
			this.stack[top] = x;
		}
		public int pop() {
			int last_item = this.stack[this.top];
			this.top -= 1;
			return last_item;
		}
		public int peek(){
			if(this.top >= 0){
				return this.stack[this.top];
			}
			return -1;
		}
		@Override
		public String toString() {
			String str = new String();
			for(int i = 0; i <= this.top; i++) {
				str += this.stack[i] + ", "; 
			}
			return str;
		}
	}
	
	public static class Stack {
		public int top;
		public Node[] stack;
		
		public Stack(int size) {
			this.stack = new Node[size];
			this.top = -1;
		}
		public void push(Node node){
			this.top += 1;
			this.stack[top] = node;
		}
		public Node pop() {
			Node last_item = this.stack[this.top];
			this.top -= 1;
			return last_item;
		}
		public boolean is_empty(){
			return this.top == -1;
		}
		public int size(){
			return this.top + 1;
		}
		@Override
		public String toString() {
			String str = new String();
			for(int i = 0; i <= this.top; i++) {
				str += this.stack[i] + ", "; 
			}
			return str;
		}
	}

	
	public static void walk(int x, int y){
		Node node = map[y][x];
		//System.out.println(node);
		if(node.visited == true){
			return;  // Node already visited 
		}

		if(node.player == 0) {
			node.visited = true;
			current_plain.push(node);
		} else {
			current_plain_players.push(node.player);
			return;
		}
		
		// Go right
		if(x < map_size - 1){
			walk(x + 1, y);
		}
		// Go down
		if(node.y < map_size - 1){
			walk(x, y + 1);
		}
		// Go left
		if(x > 0){
			walk(x - 1, y);
		}
		// Go up
		if(y > 0){
			walk(x, y - 1);
		}

		return;
	}
	
	public static void init_values(Scanner sc) {
		map_size = sc.nextInt();
		map = new Node[map_size][map_size];
		black_points = 0;
		white_points = 0;
	}
	
	public static void read_map(Scanner sc) {
		for(int y = 0; y < map_size; y++){
			for(int x = 0; x < map_size; x++){
				map[y][x] = new Node(x, y, sc.nextInt());
			}
		}
	}
	
	public static void print_map(){
		for(int y = 0; y < map_size; y++){
			for(int x = 0; x < map_size; x++){
				System.out.print(map[y][x] + "  ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream("swTest/src/swTest/black_and_white.txt"));
		Scanner sc = new Scanner(System.in);

		// Read TC
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++){
			// ALGORITHM
			
			// init values
			init_values(sc);
			

			// Read the map
			read_map(sc);
			
			//print_map();
			// Walk the map
			for(int y = 0; y < map_size; y++){
				for(int x = 0; x < map_size; x++){
					if(map[y][x].visited == false && map[y][x].player == 0){
						// Check enclosure
						current_plain = new Stack(map_size*map_size);
						current_plain_players = new Int_Stack(map_size*map_size);
						walk(x, y);
						if(current_plain_players.is_homogenic && current_plain_players.peek() == 1){
							black_points += current_plain.size();
						} else if (current_plain_players.is_homogenic && current_plain_players.peek() == 2){
							white_points += current_plain.size();
						}
					}
				}
			}
			//print_map();
			// Sum the map
			System.out.println("#" + test_case + " " + (black_points - white_points));
			
		}
		
		sc.close();

	}

}
