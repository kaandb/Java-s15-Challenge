package models;

public class Student extends MemberRecord {

    public Student(String name) {
        super(name);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Öğrenci ismi " + getName() + " (ID: " + getMemberId() + ")");
    }
}
