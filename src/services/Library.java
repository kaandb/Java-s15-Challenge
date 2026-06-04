package services;

import models.*;

import java.util.*;

public class Library {

    private final Map<String, Book> bookCatalog;
    private final Map<String, MemberRecord> members;
    private final Map<String, MemberRecord> borrowedBooks;
    private final Set<Author> uniqueAuthors;

    public Library() {
        this.bookCatalog = new HashMap<>();
        this.members = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
        this.uniqueAuthors = new HashSet<>();
    }

    public void addMember(MemberRecord member) {
        members.put(member.getMemberId(), member);
        System.out.println("Üye eklendi: " + member.getName());
    }

    public void newBook(Book book) {
        bookCatalog.put(book.getBookID(), book);
        uniqueAuthors.add(book.getAuthor()); // Set olduğu için yazar tekrarlanmaz.
        book.getAuthor().newBook(book);

        System.out.println("Kitap kütüphaneye eklendi: " + book.getName());
    }

    public void showBook() {
        System.out.println("\n--- KÜTÜPHANE KİTAP LİSTESİ ---");
        if (bookCatalog.isEmpty()) {
            System.out.println("Kütüphanede hiç kitap yok.");
            return;
        }
        for (Book b : bookCatalog.values()) {
            System.out.println(b.toString()); // Book sınıfında ezdiğimiz toString çalışır
        }
    }

    public void showMembers() {
        System.out.println("\n--- KAYITLI ÜYELER ---");
        if (members.isEmpty()) {
            System.out.println("Sistemde kayıtlı üye bulunmamaktadır.");
            return;
        }
        for (MemberRecord m : members.values()) {
            System.out.println(m.toString());
        }
    }

    public Author getOrCreateAuthor(String authorName) { // Sisteme kitap eklenirken yazarın klonlanmasını önleyen kontrol metodu
        for (Author author : uniqueAuthors) {
            if (author.getName().equalsIgnoreCase(authorName)) {
                return author;
            }
        }
        return new Author(authorName);
    }

    public void showAuthorBooks(String authorName) {
        boolean authorFound = false;
        for (Author author : uniqueAuthors) {
            if (author.getName().equalsIgnoreCase(authorName)) {
                author.showBook();
                authorFound = true;
                break;
            }
        }
        if (!authorFound) {
            System.out.println("Hata: Sistemde '" + authorName + "' adında bir yazar bulunamadı.");
        }
    }

    public void lendBook(String bookId, String memberId) {
        Book book = bookCatalog.get(bookId);
        MemberRecord member = members.get(memberId);

        if (book == null || member == null) {
            System.out.println("Hata: Kitap veya Üye bulunamadı!");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Üzgünüz, bu kitap şu an başkası tarafından ödünç alınmış.");
            return;
        }
        if (member.getBooksIssued() >= member.getMaxBookLimit()) {
            System.out.println("Limit dolu! " + member.getName() + " daha fazla kitap alamaz.");
            return;
        }

        book.setAvailable(false);
        member.incBookIssued();
        borrowedBooks.put(book.getBookID(), member);

        member.payBill(book.getPrice()); // Polymorphism: Fatura kesimi
        System.out.println("Başarılı: '" + book.getName() + "' kitabı " + member.getName() + " adlı üyeye verildi.");
    }

    public void returnBook(String bookId) {
        Book book = bookCatalog.get(bookId);

        if (book == null || book.isAvailable()) {
            System.out.println("Hata: Bu kitap zaten kütüphanede veya sistemde yok.");
            return;
        }

        MemberRecord member = borrowedBooks.get(bookId);
        if (member != null) {
            book.setAvailable(true);
            member.decBookIssued();
            borrowedBooks.remove(bookId);

            System.out.println("İade Başarılı: Ücret iadeniz yapılıyor...");
            member.payBill(-book.getPrice());
        }
    }


    public void updateBook(String bookId, String newName) {
        Book book = bookCatalog.get(bookId);
        if (book != null) {
            book.setName(newName);
            System.out.println("Kitap adı güncellendi: " + book.getName());
        } else {
            System.out.println("Hata: Güncellenecek kitap bulunamadı.");
        }
    }

    public void deleteBook(String bookId) {
        if (borrowedBooks.containsKey(bookId)) {
            System.out.println("Hata: Bu kitap şu an bir üyede, sistemden silinemez!");
            return;
        }
        Book removedBook = bookCatalog.remove(bookId);
        if (removedBook != null) {
            System.out.println("Kitap sistemden başarıyla silindi: " + removedBook.getName());
        } else {
            System.out.println("Hata: Silinecek kitap bulunamadı.");
        }
    }

    public List<Book> searchBook(String query) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book b : bookCatalog.values()) {
            if (b.getBookID().equals(query) ||
                    b.getName().equalsIgnoreCase(query) ||
                    b.getAuthor().getName().equalsIgnoreCase(query)) {
                foundBooks.add(b);
            }
        }
        return Collections.unmodifiableList(foundBooks);
    }

    public void listBooksByCategory(String category) {
        System.out.println("Kategori: " + category);
        boolean found = false;

        for (Book b : bookCatalog.values()) {
            if (category.equalsIgnoreCase("StudyBook") && b instanceof StudyBook) {
                System.out.println(b.toString());
                found = true;
            }
            else if (category.equalsIgnoreCase("Journal") && b instanceof Journal) {
                System.out.println(b.toString());
                found = true;
            }
            else if (category.equalsIgnoreCase("Magazine") && b instanceof Magazine) {
                System.out.println(b.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Bu kategoride hiç kitap bulunamadı.");
        }
    }
}