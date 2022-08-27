import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> generalArray = new ArrayList<>();
		
		for(int i = 1; i <= 10000; i++) {
			generalArray.add(i);
		}
		
		int fromIndex = 0;
		int toIndex = generalArray.size() / 4;
		
		for(int i = 0; i < 4; i++) {
			List<Integer> subArray = new ArrayList<>();
			subArray = generalArray.subList(fromIndex, toIndex);
			fromIndex = toIndex + 1;
			toIndex += generalArray.size() / 4;
			
			ThreadClass tc = new ThreadClass(subArray);
			Thread t = new Thread(tc);
			t.start();
		}
	}
}
