import java.util.LinkedList;
import java.util.Queue;

public class Main extends RBT {

	public static void main(String[] args) {
		RBT tree = new RBT();
		int array[] = new int[15];
		
		
		
		for(int i = 0; i < array.length; i++) {
			array[i] = getRandomNumber(1, 100);
			for(int j = 0; j < i; j++) {
				if(array[i] == array[j]) {
					i--;
					break;
				}
			}
		}
		
		
		for (int i = 0; i < array.length; i++) {
			System.out.println("Inserted: " + array[i]);
			if(i == array.length - 1) {
				tree.RB_Insert(array[i]);
				tree.RB_Print(); 
				break;
			}
			tree.RB_Insert(array[i]);
			tree.RB_Print(); 
			tree.removeLeaves(tree.getRoot());
			

			
		}

		
		

		
		
		
		

	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
