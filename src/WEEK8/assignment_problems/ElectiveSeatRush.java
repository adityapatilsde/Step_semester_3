package WEEK8.assignment_problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

interface CreditRule {
    int getLimit();
}

class RegularRule implements CreditRule {
    public int getLimit() {
        return 24;
    }
}

class HonorsRule implements CreditRule {
    public int getLimit() {
        return 28;
    }
}

class ExchangeRule implements CreditRule {
    public int getLimit() {
        return 20;
    }
}

class Student {
    String name;
    int credits;
    CreditRule rule;

    Student(String name, int credits, CreditRule rule) {
        this.name = name;
        this.credits = credits;
        this.rule = rule;
    }

    boolean canTake(int electiveCredits) {
        return credits + electiveCredits <= rule.getLimit();
    }
}

class Elective {
    String name;
    int credits;
    int capacity;
    ArrayList<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean contains(Student s) {
        if (enrolled.contains(s) || waitlist.contains(s))
            return true;

        return false;
    }
}

class EnrollmentService {

    void enroll(Student s, Elective e) {

        if (e.contains(s)) {
            System.out.println("Enrollment failed: Student already enrolled or waitlisted.");
            return;
        }

        if (!s.canTake(e.credits)) {
            System.out.println("Enrollment failed: " + s.name
                    + " would exceed the credit limit ("
                    + (s.credits + e.credits) + "/"
                    + s.rule.getLimit() + ").");
            return;
        }

        if (e.enrolled.size() < e.capacity) {

            e.enrolled.add(s);
            s.credits += e.credits;

            System.out.println(s.name + " enrolled in " + e.name
                    + " (credits: " + s.credits + "/"
                    + s.rule.getLimit() + ").");
        }
        else {
            System.out.println(e.name + " is full.");

            e.waitlist.add(s);

            System.out.println(s.name + " added to waitlist (position "
                    + e.waitlist.size() + ").");
        }
    }

    void drop(Student s, Elective e) {

        if (!e.enrolled.contains(s))
            return;

        e.enrolled.remove(s);
        s.credits -= e.credits;

        System.out.println(s.name + " dropped " + e.name
                + " (credits: " + s.credits + "/"
                + s.rule.getLimit() + ").");

        promote(e);
    }

    void promote(Elective e) {

        while (!e.waitlist.isEmpty()
                && e.enrolled.size() < e.capacity) {

            Student s = e.waitlist.peek();

            if (s.canTake(e.credits)) {

                e.waitlist.remove();
                e.enrolled.add(s);
                s.credits += e.credits;

                System.out.println(s.name
                        + " promoted from waitlist and enrolled in "
                        + e.name + " (credits: " + s.credits + "/"
                        + s.rule.getLimit() + ").");
            }
            else {
                e.waitlist.remove();
            }
        }
    }
}

public class ElectiveSeatRush {

    public static void main(String[] args) {

        Elective cloud = new Elective(
                "Cloud Computing", 4, 2);

        Student asha =
                new Student("Asha", 20, new RegularRule());

        Student ravi =
                new Student("Ravi", 22, new HonorsRule());

        Student neha =
                new Student("Neha", 12, new ExchangeRule());

        Student kiran =
                new Student("Kiran", 22, new RegularRule());

        EnrollmentService service = new EnrollmentService();

        service.enroll(asha, cloud);
        service.enroll(ravi, cloud);
        service.enroll(neha, cloud);
        service.enroll(kiran, cloud);

        service.drop(asha, cloud);
    }
}