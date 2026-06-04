package models;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void showBook() {
        System.out.println(getName() + " tarafından yazılan kitaplar:");
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kayıtlı bir kitap bulunamadı.");
        } else {
            for (Book book : books) {
                System.out.println("- " + book.getName());
            }
        }
    }

    @Override
    public void whoYouAre() {
        System.out.println("Yazar: " + getName());
    }
}
