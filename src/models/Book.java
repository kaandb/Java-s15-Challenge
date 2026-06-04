package models;

import java.util.Objects;
import java.util.UUID;

public abstract class Book {
    private final String bookID;
    private String name;
    private Author author; // composition
    private double price;
    private boolean isAvailable; // status
    private String edition;
    private String dateOfPurchase;

    public Book(String name, Author author, double price, String edition, String dateOfPurchase) {
        this.bookID = UUID.randomUUID().toString(); // UUID
        this.name = name;
        this.author = author;
        this.price = price;
        this.isAvailable = true;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getBookID() { return bookID; }
    // bookID için Setter yok.

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }

    public String getDateOfPurchase() { return dateOfPurchase; }
    public void setDateOfPurchase(String dateOfPurchase) { this.dateOfPurchase = dateOfPurchase; }

    // POJO için gerekli metodlar: equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    @Override
    public String toString() {
        return "[ID: " + bookID + "] Kitap: " + name + " | Yazar: " + author.getName() + " | Durum: " + (isAvailable ? "Rafta" : "Ödünç Alındı");
    }
}
