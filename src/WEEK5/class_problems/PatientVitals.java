package WEEK5.class_problems;

public class PatientVitals {

    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {

        readings = new double[initialReadings.length];
        count = 0;

        for (int i = 0; i < initialReadings.length; i++) {
            recordReading(initialReadings[i]);
        }
    }

    public void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count == readings.length) {

            double[] newReadings = new double[readings.length + 1];

            for (int i = 0; i < readings.length; i++) {
                newReadings[i] = readings[i];
            }

            readings = newReadings;
        }

        readings[count] = reading;
        count++;
    }

    public double getAverage() {

        if (count == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum = sum + readings[i];
        }

        return sum / count;
    }

    public double[] getAllReadings() {

        double[] copy = new double[count];

        for (int i = 0; i < count; i++) {
            copy[i] = readings[i];
        }

        return copy;
    }

    public static void main(String[] args) {

        PatientVitals v =
                new PatientVitals(new double[]{36.5, -2, 37.1});

        double[] readings = v.getAllReadings();

        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i] + " ");
        }
    }
}