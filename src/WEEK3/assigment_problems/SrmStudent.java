package WEEK3.assigment_problems;

public class SrmStudent {

    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    // classAverage is static because it calculates one average for the whole class,
    // while isEligible is not static because it checks one particular student's attendance.
    static double classAverage(SrmStudent[] students) {

        int total = 0;

        for (int i = 0; i < students.length; i++) {
            total = total + students[i].attendance;
        }

        return (double) total / students.length;
    }

    public static void main(String[] args) {

        SrmStudent[] students = new SrmStudent[5];

        students[0] = new SrmStudent("Ravi", "RA101", 82);
        students[1] = new SrmStudent("Anitha", "RA102", 68);
        students[2] = new SrmStudent("Karthik", "RA103", 91);
        students[3] = new SrmStudent("Meera", "RA104", 74);
        students[4] = new SrmStudent("Suresh", "RA105", 60);

        for (int i = 0; i < students.length; i++) {

            if (students[i].isEligible()) {
                System.out.println(students[i].name + " - " +
                        students[i].attendance + "% - Eligible");
            } else {
                System.out.println(students[i].name + " - " +
                        students[i].attendance + "% - Detained");
            }
        }

        System.out.printf("Class average: %.1f%%%n",
                SrmStudent.classAverage(students));
    }
}

