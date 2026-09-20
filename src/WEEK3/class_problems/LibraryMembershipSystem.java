package WEEK3.class_problems;

class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    void printMember() {
        System.out.println(name + " - " + memberId
                + " - Books: " + booksIssued);
    }
}


class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println("Library: " + libraryName);
        System.out.println("Name: " + name);
        System.out.println("Member ID: " + memberId);
        System.out.println("Books Issued: " + booksIssued);
        System.out.println();
    }

    static void printTotalMembers() {
        System.out.println("Total Members: " + memberCount);
    }
}


public class LibraryMembershipSystem {

    public static void main(String[] args) {

        System.out.println("BROKEN VERSION");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println("Member 1:");
        member1.printMember();

        System.out.println("Member 2:");
        member2.printMember();

        System.out.println();

        System.out.println("FIXED VERSION");

        LibraryMember member3 =
                new LibraryMember("Aditi", 2);

        LibraryMember member4 =
                new LibraryMember("Rohan", 3);

        System.out.println("Member 1 Card:");
        member3.printMemberCard();

        System.out.println("Member 2 Card:");
        member4.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}