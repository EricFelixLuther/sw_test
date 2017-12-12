package swTest;
import java.util.Scanner;

public class app {

	public static Stack visited;
	public static int map_size;
	public static int[][] map;
	public static int field_val;
	public static int one_sum_before;  // black
	public static int two_sum_before;  // white
	public static int one_sum_after;   // black
	public static int two_sum_after;   // white
	public static int curr_field;
	
	public static class Stack {
		public int top;
		public int stack_size;
		public int[][] stack;

		public Stack(int size) {
			this.stack_size = size;
			stack = new int[size][2];
			for(int i = 0; i < size; i++) {
				stack[i][0] = -1;
				stack[i][1] = -1;
			}
			top=-1;
		}
		public void push(int[] cords){
			top += 1;
			stack[top] = cords;
		}
		public int[] pop(){
			int[] cords = {stack[top][0], stack[top][1]};
			top -= 1;
			return cords;
		}
		public boolean isEmpty(){
			return top==-1;
		}
		public boolean inStack(int x, int y){
			for(int i=0; i< this.stack_size; i++){
				if(stack[i][0] == x && stack[i][1] == y){
					return true;
				}
			}
			return false;
		}
		public void print(){
			for(int i = 0; i < this.top; i++){
				System.out.print(stack[i][0] + "," + stack[i][1] + "  ");
			}
			System.out.println();
		}
	}
	
	public static int walk(int startx, int starty, int player){
		if(visited.inStack(startx, starty)){
			//System.out.println("visited: " + startx + "," + starty);
			return player;  // Node already visited 
		}

		int[] cords = {startx, starty};
		visited.push(cords);
		if(player == 0 && map[startx][starty] != 0){ // Found a player
			player = map[startx][starty];
			return player;
		} else if (player != 0  && map[startx][starty] != 0 && map[startx][starty] != player){  // failed enclosure
			player = 3;
			//System.out.println("Failed enclosure @ " + startx + "," + starty);
			return player;
		}
			
			
		if(player == 0) {
			// Go down
			if(startx < map_size - 1){
				player = walk(startx + 1, starty, player);
			}
			// Go right
			if(starty < map_size - 1){
				player = walk(startx, starty + 1, player);
			}
			// Go up
			if(startx > 0){
				player = walk(startx - 1, starty, player);
			}
			// Go left
			if(starty > 0){
				player = walk(startx, starty - 1, player);
			}
		} else {
			// Go down
			if(startx < map_size - 1){
				walk(startx + 1, starty, player);
			}
			// Go right
			if(starty < map_size - 1){
				walk(startx, starty + 1, player);
			}
			// Go up
			if(startx > 0){
				walk(startx - 1, starty, player);
			}
			// Go left
			if(starty > 0){
				walk(startx, starty - 1, player);
			}
		}
		
		return player;
	}
	
	public static void init_values(Scanner sc) {
		map_size = sc.nextInt();
		map = new int[map_size][map_size];
		one_sum_before = 0;
		two_sum_before = 0;
		one_sum_after = 0;
		two_sum_after = 0;
	}
	
	public static void read_map(Scanner sc) {
		for(int i = 0; i < map_size; i++){
			for(int o = 0; o < map_size; o++){
				field_val = sc.nextInt();
				if(field_val == 1){
					one_sum_before += 1;
				} else if (field_val == 2){
					two_sum_before += 1;
				}
				map[i][o] = field_val;
			}
		}
	}
	
	public static void sum_map() {
		for(int i = 0; i < map_size; i++){
			for(int o = 0; o < map_size; o++){
				field_val = map[i][o];
				System.out.print(field_val);  // Print map
				if(field_val == 4){
					one_sum_after += 1;
				} else if (field_val == 5){
					two_sum_after += 1;
				}
			}
			System.out.println(); // Print map
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
			
			
			// Walk the map
			for(int i = 0; i < map_size; i++){
				for(int o = 0; o < map_size; o++){
					curr_field = map[i][o];
					if(curr_field == 0){
						// Check enclosure
						visited = new Stack(map_size*map_size);// - one_sum_before - two_sum_before);
						int player = walk(i, o, curr_field);
						System.out.println(player);
						while(visited.isEmpty() != true){
							int[] cords = visited.pop();
							int x = cords[0];
							int y = cords[1];
							if(map[x][y] == 0){
								map[x][y] = player + 3;
							}
						}
					}
				}
			}

			// Sum the map
			sum_map();
			
			System.out.println("#" + test_case + " " + ((two_sum_after - two_sum_before) - (one_sum_after - one_sum_before)));
			
		}
		
		sc.close();

	}

}
