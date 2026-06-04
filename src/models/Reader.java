package models;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {

    private List<Book> books;

    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void purchaseBook(Book book) {
        books.add(book);
        System.out.println(getName() + ", '" + book.getName() + "' kitabını satın aldı.");
    }

    public void borrowBook(Book book) {
        books.add(book);
        System.out.println(getName() + ", '" + book.getName() + "' kitabını ödünç aldı.");
    }
    // UML'de sadece tek bir books var, o yüzden purchaseBook ve borrowBook kitapları aynı listeye ekliyor.

    public void returnBook(Book book) {
        books.remove(book);
    }

    public void showBook() {
        System.out.println("Okur " + getName() + " elindeki kitaplar:");
        if (books.isEmpty()) {
            System.out.println("Elinde hiç kitap yok.");
        } else {
            for (Book book : books) {
                System.out.println("- " + book.getName());
            }
        }
    }

    @Override
    public void whoYouAre() {
        System.out.println("Okur: " + getName());
    }
}