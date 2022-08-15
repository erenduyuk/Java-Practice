
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyList<Integer> liste = new MyList<>();
		for(int i = 1; i < 11; i++) {
			liste.add(i);
		}
		
		
		System.out.println(liste.get(2));
		System.out.println(liste.size());
		System.out.println(liste.getCapacity());
		System.out.println(liste.toString());
		
		MyList<Integer> alt = liste.sublist(0, 3);
		System.out.println(alt.toString());
		
	}

}
