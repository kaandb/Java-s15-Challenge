import models.*;
import services.Library;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sistem başlatılıyor ve örnek veriler yükleniyor...\n");

        Author author1 = new Author("George Orwell");
        Author author2 = new Author("Yuval Noah Harari");
        Author author3 = new Author("Adam Fawer");


        Book book1 = new StudyBook("1984", author1, 60.0, "5. Baskı", "2023-05-20");
        Book book2 = new Journal("Hayvan Çiftliği", author1, 45.0, "3. Baskı", "2023-08-10");
        Book book3 = new Magazine("Sapiens", author2, 80.0, "Eylül 2023", "2023-09-01");
        Book book4 = new StudyBook("Empati", author3, 55.0, "1. Baskı", "2024-01-15");

        library.newBook(book1);
        library.newBook(book2);
        library.newBook(book3);
        library.newBook(book4);

        Student student1 = new Student("Kaan Demirbağ");
        Faculty faculty1 = new Faculty("Enis Gayretli");
        library.addMember(student1);
        library.addMember(faculty1);

        System.out.println("\n--- YÜKLEME TAMAMLANDI ---\n");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== KÜTÜPHANE BANKOSU ===");
            System.out.println("1. Tüm Kitapları Listele");
            System.out.println("2. Tüm Üyeleri Listele");
            System.out.println("3. Yeni Kitap Ekle");
            System.out.println("4. Kitap Ödünç Ver");
            System.out.println("5. Kitap İade Al");
            System.out.println("6. Kitap Ara (İsim, Yazar, ID)");
            System.out.println("7. Kitap Güncelle");
            System.out.println("8. Kitap Sil");
            System.out.println("9. Kategoriye Göre Kitap Listele");
            System.out.println("10. Bir Yazarın Tüm Kitaplarını Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.showBook();
                    break;
                case 2:
                    library.showMembers();
                    break;
                case 3:
                    System.out.print("Kitap Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar Adı: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Fiyatı: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Kitap Türünü Seçiniz: 1- StudyBook | 2- Journal | 3- Magazine");
                    System.out.print("Seçiminiz: ");
                    String typeChoice = scanner.nextLine();

                    Author newAuthor = library.getOrCreateAuthor(authorName);
                    Book newBook;

                    if (typeChoice.equals("2")) {
                        newBook = new Journal(name, newAuthor, price, "1. Baskı", "2026-03-22");
                    } else if (typeChoice.equals("3")) {
                        newBook = new Magazine(name, newAuthor, price, "Ocak 2026", "2026-03-22");
                    } else {
                        newBook = new StudyBook(name, newAuthor, price, "1. Baskı", "2026-03-22");
                    }

                    library.newBook(newBook);
                    break;
                case 4:
                    System.out.print("Ödünç verilecek kitabın ID'si: ");
                    String lendBookId = scanner.nextLine();
                    System.out.print("Ödünç alacak üyenin ID'si: ");
                    String memberId = scanner.nextLine();
                    library.lendBook(lendBookId, memberId);
                    break;
                case 5:
                    System.out.print("İade edilecek kitabın ID'si: ");
                    String returnBookId = scanner.nextLine();
                    library.returnBook(returnBookId);
                    break;
                case 6:
                    System.out.print("Arama kelimesi (ID, İsim veya Yazar): ");
                    String query = scanner.nextLine();
                    System.out.println("Arama Sonuçları:");
                    for (Book b : library.searchBook(query)) {
                        System.out.println(b);
                    }
                    break;
                case 7:
                    System.out.print("Güncellenecek kitabın ID'si: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Kitabın Yeni Adı: ");
                    String newName = scanner.nextLine();
                    library.updateBook(updateId, newName);
                    break;
                case 8:
                    System.out.print("Silinecek kitabın ID'si: ");
                    String deleteId = scanner.nextLine();
                    library.deleteBook(deleteId);
                    break;
                case 9:
                    System.out.println("Kategoriler: 1- StudyBook | 2- Journal | 3- Magazine");
                    System.out.print("Seçiminiz: ");
                    String catChoice = scanner.nextLine();
                    if (catChoice.equals("1")) library.listBooksByCategory("StudyBook");
                    else if (catChoice.equals("2")) library.listBooksByCategory("Journal");
                    else if (catChoice.equals("3")) library.listBooksByCategory("Magazine");
                    else System.out.println("Geçersiz kategori.");
                    break;
                case 10:
                    System.out.print("Kitapları listelenecek yazarın adı: ");
                    String searchAuthor = scanner.nextLine();
                    library.showAuthorBooks(searchAuthor);
                    break;
                case 0:
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyin.");
            }
        }
        scanner.close();
    }
}