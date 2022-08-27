import java.util.ArrayList;
import java.util.List;

public class ThreadClass implements Runnable{
	
	private ArrayList<Integer> oddArray = new ArrayList<>();
	private ArrayList<Integer> evenArray = new ArrayList<>();
	private List<Integer> generalArray = new ArrayList<>();
	
	public ThreadClass(List<Integer> generalArray) {
		this.generalArray = generalArray;
	}
	
	@Override
	public void run() {
			for(int i = 0; i < generalArray.size(); i++) {
				evenOrOdd(generalArray.get(i));
			}
			
			printEvenArray();
			printOddArray();
	}
	
	public void evenOrOdd(int number) {
		if(number % 2 == 0) {
			evenArray.add(number);
		}
		else {
			oddArray.add(number);
		}
	}
	
	public void printOddArray() {
		for(int i = 0; i < oddArray.size(); i++) {
			System.out.println(oddArray.get(i));
		}
	}
	
	public void printEvenArray() {
		for(int i = 0; i < evenArray.size(); i++) {
			System.out.println(evenArray.get(i));
		}
	}
}
