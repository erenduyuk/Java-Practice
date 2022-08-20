
public class Book implements Comparable<Book> {
	
	private String name;
	private int numberOfPages;
	
	public Book(String name, int numberOfPages) {
		this.name = name;
		this.numberOfPages = numberOfPages;
	}
	
	@Override
	public int compareTo(Book o) {
		return getName().compareTo(o.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	
	

}
