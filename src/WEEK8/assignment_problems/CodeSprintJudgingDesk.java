package WEEK8.assignment_problems;

import java.util.ArrayList;

interface ScoringRule {
    double calculate(Score s);
}

class Innovation implements ScoringRule {
    public double calculate(Score s) {
        return s.idea * 0.5 + s.execution * 0.3 + s.presentation * 0.2;
    }
}

class Open implements ScoringRule {
    public double calculate(Score s) {
        return (s.idea + s.execution + s.presentation) / 3.0;
    }
}

class Student {
    String name;
    Student(String name) {
        this.name = name;
    }
}

class Score {
    int idea, execution, presentation;
    Score(int i, int e, int p) {
        idea = i;
        execution = e;
        presentation = p;
    }
}

class Judge {
    String name;
    Judge(String name) {
        this.name = name;
    }
}

class Track {
    String name;
    ScoringRule rule;

    Track(String name, ScoringRule rule) {
        this.name = name;
        this.rule = rule;
    }
}

class Project {
    String name;
    Score score;

    Project(String name) {
        this.name = name;
    }
}

class Team {
    String name;
    ArrayList<Student> members;
    Track track;
    Project project;

    Team(String name, ArrayList<Student> members, Track track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }

    void submit(String name) {
        if (project == null) {
            project = new Project(name);
            System.out.println("Project '" + name + "' submitted by " + this.name + ".");
        }
    }
}

class Hackathon {
    ArrayList<Team> teams = new ArrayList<>();
    int state = 0;

    void register(Team t) {
        if (t.members.size() < 2 || t.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Team old : teams)
            for (Student s1 : old.members)
                for (Student s2 : t.members)
                    if (s1.name.equals(s2.name)) {
                        System.out.println("Registration failed: Student already belongs to another team.");
                        return;
                    }

        teams.add(t);
        System.out.println("Team " + t.name + " registered (" +
                t.members.size() + " members, " + t.track.name + " track).");
    }

    void publish() {
        for (Team t : teams)
            if (t.project != null && t.project.score != null)
                System.out.printf("Final score: %.2f%n",
                        t.track.rule.calculate(t.project.score));

        state = 2;
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {

        Hackathon h = new Hackathon();

        Track innovation = new Track("Innovation", new Innovation());
        Track open = new Track("Open", new Open());

        Student a = new Student("Asha");
        Student r = new Student("Ravi");
        Student n = new Student("Neha");
        Student k = new Student("Kiran");

        ArrayList<Student> m1 = new ArrayList<>();
        m1.add(a); m1.add(r); m1.add(n);

        Team byteBusters = new Team("ByteBusters", m1, innovation);
        h.register(byteBusters);

        ArrayList<Student> m2 = new ArrayList<>();
        m2.add(k);

        h.register(new Team("SoloCoder", m2, open));

        byteBusters.submit("SmartAttend");

        byteBusters.project.score = new Score(8, 7, 9);
        System.out.println("Score recorded for 'SmartAttend'.");

        h.publish();

        if (h.state == 2)
            System.out.println("Rescore rejected: Results have already been published.");
    }
}