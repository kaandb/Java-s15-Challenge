package models;

import java.util.Objects;
import java.util.UUID;

public abstract class MemberRecord extends Person implements Payable {

    private final String memberId; // immutability
    private final int maxBookLimit; // immutability
    private int booksIssued; // Üyenin elindeki mevcut kitap sayısı

    public MemberRecord(String name) {
        super(name); // Person constructor
        this.memberId = UUID.randomUUID().toString(); // UUID
        this.maxBookLimit = 5; // Proje gereksinimlerinde istenen limit
        this.booksIssued = 0;
    }

    public String getMemberId() { return memberId; }
    // memberId için Setter yok.

    public int getMaxBookLimit() { return maxBookLimit; }
    // maxBookLimit için Setter yok.

    public int getBooksIssued() { return booksIssued; }
    // booksIssued için Setter yok, incBookIssued ve decBookIssued kullanıyoruz.

    public void incBookIssued() {
        if (booksIssued < maxBookLimit) {
            booksIssued++;
        } else {
            System.out.println("Limit dolu! Daha fazla kitap alamazsınız.");
        }
    }

    public void decBookIssued() {
        if (booksIssued > 0) {
            booksIssued--;
        }
    }

    // Interface metodunun uygulanması
    @Override
    public void payBill(double amount) {
        System.out.println("Sayın " + getName() + " (ID: " + memberId + ") -> İşlem tutarı: " + amount + " TL");
    }

    @Override
    public String toString() {
        return "Üye Adı: " + getName() + " | ID: " + memberId + " | Elindeki Kitap Sayısı: " + booksIssued + "/" + maxBookLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberRecord that = (MemberRecord) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}