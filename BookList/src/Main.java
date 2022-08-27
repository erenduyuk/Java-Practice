import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        Book b1 = new Book("Gece Yarısı Kütüphanesi", 283, "Matt Haig", "23.02.2022");
        Book b2 = new Book("Kaplanın Sırtında", 324, "Zülfü Livaneli", "25.06.2022");
        Book b3 = new Book("Şeker Portakalı", 182, "Jose Mauro De Vasconcelos", "06.09.2019");
        Book b4 = new Book("Ben Kirke", 408, "Madeline Miller", "25.01.2021");
        Book b5 = new Book("Veronika Ölmek İstiyor", 198, "Paulo Coelho", "06.08.2019");
        Book b6 = new Book("Simyacı", 184, "Paulo Coelho", "28.09.2021");
        Book b7 = new Book("Fareler Ve İnsanlar", 99, "John Steinbeck", "21.05.2020");
        Book b8 = new Book("Küçük Prens", 96, "Matt Haig", "13.01.2021");
        Book b9 = new Book("Gün Olur Asra Bedel", 283, "Cengiz Aytmatov", "02.06.2021");
        Book b10 = new Book("Fahrenheit 451", 208, "Ray Bradbury", "20.08.2021");

        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);
        bookList.add(b7);
        bookList.add(b8);
        bookList.add(b9);
        bookList.add(b10);

        Map<String, String> bookMap = new HashMap<>();
        bookList.forEach(book -> bookMap.put(book.getName(), book.getAuthor()));
        System.out.println("******* All Books ******");
        for(String book : bookMap.keySet()) {
            System.out.println("Book Name: " + book + ", Author: " + bookMap.get(book));
        }

        System.out.println();
        System.out.println("****** Books under 100 pages ******");
        List<Book> booklist2 = bookList.stream().filter(book -> book.getPageNumber() < 100).collect(Collectors.toList());
        booklist2.forEach(book -> System.out.println(book.getName()));

    }

}
