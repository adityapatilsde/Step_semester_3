package WEEK5.assigment_problems;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        for (int i = 0; i < bookIds.length; i++) {

            String id = bookIds[i];

            if (id == null ||
                    !id.matches("BK-[0-9]{3}")) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index, String newId) {

        if (!newId.matches("BK-[0-9]{3}")) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] newBookIds = bookIds.clone();
        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class ImmutableLoanReceiptLedger {

    static int totalProcessed;

    static {
        totalProcessed = 0;
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }

            processed++;
            totalProcessed++;
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}