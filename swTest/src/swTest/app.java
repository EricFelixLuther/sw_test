package swTest;
import java.util.Scanner;

public class app {

	public static Stack visited;
	
	public static class Stack {
		public int top;
		public int size;
		public int[][] stack;

		public Stack(int size) {
			this.size = size;
			stack = new int[size][2];
			top=-1;
		}
		public void push(int[] cords){
			top += 1;
			stack[top] = cords;
		}
		public void pop(){
			if(!isEmpty()){
				top -= 1;
			}
		}
		public boolean isEmpty(){
			return top==-1;
		}
		public boolean inStack(int[] cords){
			for(int i=0; i< this.size; i++){
				if(stack[i] == cords){
					return true;
				}
			}
			return false;
		}
		public void flushStack(){
			top = -1;
			stack = new int[this.size][2];
		}
	}
	
	public static void walk(int startx, int starty, int player, int[][] map, int size){
		if(visited.isEmpty()){
			return;  // Failed enclosure
		}
		int[] cords = {startx, starty};
		if(visited.inStack(cords)){
			return;  // Node already visited 
		}
		
		if(player == 0 && map[startx][starty] != 0){ // Found a player
			player = map[startx][starty];
			return;
		} else if (player != 0  && map[startx][starty] != player){  // failed enclosure
			visited.flushStack();
			return;
		} else {
			visited.push(cords);
		}
			
			
		// Go down
		if(startx >= 0 && startx < size - 1){
			walk(startx + 1, starty, player, map, size);
		}
		// Go left
		if(starty > 0 && starty < size){
			walk(startx, starty - 1, player, map, size);
		}
		// Go up
		if(startx > 0 && startx < size - 1){
			walk(startx - 1, starty, player, map, size);
		}
		// Go right
		if(starty >= 0 && starty < size){
			walk(startx, starty + 1, player, map, size);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream("src/swTest/black_and_white.txt"));
		Scanner sc = new Scanner(System.in);
		
		int[][] map;
		int field_val;
		int one_sum_before;  // black
		int two_sum_before;  // white
		int one_sum_after;   // black
		int two_sum_after;   // white
		int curr_field;
		// Read TC
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++){
			// ALGORITHM
			
			// init values
			int size = sc.nextInt();
			map = new int[size][size];
			one_sum_before = 0;
			two_sum_before = 0;
			one_sum_after = 0;
			two_sum_after = 0;
			

			// Read the map
			for(int i = 0; i < size; i++){
				for(int o = 0; o < size; o++){
					field_val = sc.nextInt();
					if(field_val == 1){
						one_sum_before += 1;
					} else if (field_val == 2){
						two_sum_before += 1;
					}
					map[i][o] = field_val;
				}
			}
			
			
			// Walk the map
			for(int i = 0; i < size; i++){
				for(int o = 0; o < size; o++){
					curr_field = map[i][o];
					if(curr_field == 0){
						// Check enclosure
						visited = new Stack(size*size - one_sum_before - two_sum_before);
						int[] cords = {i, o};
						visited.push(cords);
						walk(i, o, curr_field, map, size);
					}
				}
			}
			
			// Sum the map
			for(int i = 0; i < size; i++){
				for(int o = 0; o < size; o++){
					field_val = map[i][o];
					if(field_val == 1){
						one_sum_after += 1;
					} else if (field_val == 2){
						two_sum_after += 1;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + ((two_sum_after - two_sum_before) - (one_sum_after - one_sum_before)));
			
		}
		
		sc.close();

	}

}
