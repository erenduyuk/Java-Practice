public class Book {
    private String name;
    private int pageNumber;
    private String author;
    private String publishingDate;

    public Book(String name, int pageNumber, String author, String publishingDate) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.author = author;
        this.publishingDate = publishingDate;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }
}
