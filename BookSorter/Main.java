import java.util.Comparator;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeSet<Book> set = new TreeSet<>();
		
		set.add(new Book("a", 100));
		set.add(new Book("c", 300));
		set.add(new Book("e", 500));
		set.add(new Book("b", 200));
		set.add(new Book("d", 400));
		
		System.out.println("Sorted by name");	
		for(Book book: set) {
			System.out.println(book.getName());
		}
		
		
		TreeSet<Book> set2 = new TreeSet<>(new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				return o1.getNumberOfPages() - o2.getNumberOfPages();
			}
		});
		
		set2.add(new Book("a", 100));
		set2.add(new Book("c", 30000));
		set2.add(new Book("e", 500));
		set2.add(new Book("b", 200));
		set2.add(new Book("d", 4000));
		
		System.out.println("\nSorted by numer of pages");
		for(Book book: set2) {
			System.out.println(book.getName());
		}
	}

}
