package WEEK5.class_problems;

public class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;
    private static String hospitalName;

    static {
        hospitalName = "MediTrack";
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {

        for (int i = 0; i < medicationCodes.length; i++) {

            if (!medicationCodes[i].matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("Invalid medication code");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            this.medicationCodes[i] = medicationCodes[i];
        }
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {

        String[] copy = new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            copy[i] = medicationCodes[i];
        }

        return copy;
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (!newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code");
        }

        String[] copy = getMedicationCodes();
        copy[index] = newCode;

        return new DischargeSummary(patientId, copy);
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {

            if (summaries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summaries[i] instanceof CriticalCareDischargeSummary) {
                critical++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + critical + " critical-care | "
                + routine + " routine";
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}