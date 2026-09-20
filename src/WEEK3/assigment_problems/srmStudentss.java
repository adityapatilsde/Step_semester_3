package WEEK3.class_problems;

public class srmStudentss {

    static String brokenName;
    static String brokenRegNo;
    static int brokenAttendance;

    srmStudentss(String name, String regNo, int attendance) {
        brokenName = name;
        brokenRegNo = regNo;
        brokenAttendance = attendance;
    }

    // FIXED VERSION
    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 0;

    srmStudentss(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        srmStudentss broken1 =
                new srmStudentss("Ravi", "RA101", 82);

        srmStudentss broken2 =
                new srmStudentss("Meera", "RA102", 74);

        System.out.println(broken1.brokenName);
        System.out.println(broken2.brokenName);

        System.out.println();

        System.out.println("Fixed version:");

        srmStudentss student1 =
                new srmStudentss("Ravi", 82);

        srmStudentss student2 =
                new srmStudentss("Meera", 74);

        student1.printIdCard();
        student2.printIdCard();

        srmStudentss.printTotalAdmissions();
    }
}