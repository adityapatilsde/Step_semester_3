package WEEK5.assigment_problems;

public class MembershipFieldReachChecker {

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode,
                             double finesOwed, String displayName) {

            membershipId = membershipId.trim();

            if (membershipId.length() < 4) {
                throw new IllegalArgumentException("Invalid membership ID");
            }

            this.membershipId = membershipId;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("DIFFERENT_PACKAGE")) {
                return "DENIED";
            }
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            int index = 0;

            if (modifier.equals("default")) {
                index = 1;
            } else if (modifier.equals("protected")) {
                index = 2;
            } else if (modifier.equals("public")) {
                index = 3;
            }

            if (classifyAccess(modifier, context).equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        String result = "";

        for (int i = 0; i < modifiers.length; i++) {

            if (i > 0) {
                result += " | ";
            }

            result += modifiers[i] + ": "
                    + allowed[i] + " allowed / "
                    + denied[i] + " denied";
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));

        LibraryMember member =
                new LibraryMember("LB94", "BR1", 0, "Priya Nair");

        System.out.println("Member created successfully");
    }
}


