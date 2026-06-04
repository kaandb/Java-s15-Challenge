package models;

public class Faculty extends MemberRecord {

    public Faculty(String name) {
        super(name);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Akademisyen ismi " + getName() + " (ID: " + getMemberId() + ")");
    }
}
