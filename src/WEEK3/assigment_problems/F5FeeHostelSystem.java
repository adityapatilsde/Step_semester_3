package WEEK3.assigment_problems;

public class F5FeeHostelSystem {

    public static void main(String[] args) {

        HostelRoom room1 = new HostelRoom("C-214", 2, 0);
        HostelRoom room2 = new HostelRoom("C-507", 2, 0);

        HostelFeeAccount fee1 =
                new HostelFeeAccount("RA101", 200000, 50000);

        HostelFeeAccount fee2 =
                new HostelFeeAccount("RA102", 200000, 20000);

        HostelFeeAccount fee3 =
                new HostelFeeAccount("RA103", 200000, 0);

        SrmStudent student1 =
                new SrmStudent("Ravi", "RA101", fee1);

        SrmStudent student2 =
                new SrmStudent("Anitha", "RA102", fee2);

        SrmStudent student3 =
                new SrmStudent("Karthik", "RA103", fee3);

        HostelRoom.safeAllot(
                new HostelRoom[]{room1, room2},
                student1.name
        );

        student1.room = room1;

        HostelRoom.safeAllot(
                new HostelRoom[]{room1, room2},
                student2.name
        );

        student2.room = room2;

        fee1.pay(10000);
        fee2.pay(-5000);
        fee3.pay(0);

        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());

        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
    }
}

