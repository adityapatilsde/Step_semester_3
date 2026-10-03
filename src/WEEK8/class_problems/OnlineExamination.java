package WEEK8.class_problems;

abstract class Question {
    int number;
    String text;

    Question(int number, String text) {
        this.number = number;
        this.text = text;
    }

    abstract boolean checkAnswer(String answer);
}

class MultipleChoiceQuestion extends Question {
    String correctAnswer;

    MultipleChoiceQuestion(int number, String text, String correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    String correctAnswer;

    TrueFalseQuestion(int number, String text, String correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String name;
    Question q1;
    Question q2;

    Examination(String name, Question q1, Question q2) {
        this.name = name;
        this.q1 = q1;
        this.q2 = q2;
    }
}

class Attempt {
    Student student;
    Examination exam;
    String answer1;
    String answer2;
    boolean submitted;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        submitted = false;
    }

    void answerQuestion(int number, String answer) {
        if (submitted) {
            System.out.println("Answers cannot be changed after submission.");
            return;
        }

        if (number == 1) {
            answer1 = answer;
        } else if (number == 2) {
            answer2 = answer;
        }

        System.out.println("Question " + number +
                " answered with '" + answer + "'.");
    }

    void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        System.out.println("Examination '" + exam.name +
                "' submitted successfully.");

        int correct = 0;

        if (answer1 != null && exam.q1.checkAnswer(answer1)) {
            correct++;
        }

        if (answer2 != null && exam.q2.checkAnswer(answer2)) {
            correct++;
        }

        System.out.println("Result for '" + exam.name +
                "' attempt: " + correct + "/2 correct");
    }
}

class Student {
    String name;
    Attempt attempt;

    Student(String name) {
        this.name = name;
    }

    Attempt startExam(Examination exam) {
        if (attempt != null) {
            System.out.println("Attempt already exists.");
            return attempt;
        }

        attempt = new Attempt(this, exam);

        System.out.println("Examination '" + exam.name +
                "' started by " + name + ".");

        return attempt;
    }
}

public class OnlineExamination {
    public static void main(String[] args) {

        Student student = new Student("Student");

        Question q1 = new MultipleChoiceQuestion(
                1, "Question 1", "A");

        Question q2 = new MultipleChoiceQuestion(
                2, "Question 2", "B");

        Examination exam = new Examination(
                "Math Quiz", q1, q2);

        Attempt attempt = student.startExam(exam);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();
    }
}