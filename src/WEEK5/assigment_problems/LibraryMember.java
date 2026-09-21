package WEEK5.assigment_problems;

public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            securityAnswer = Integer.toHexString(answer.hashCode());
        }
    }

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        LibraryMember m2 =
                new LibraryMember("Priya Nair");

        System.out.println(m2.getMembershipId());

        LibraryMember m3 =
                new LibraryMember("LIB-8841", "Priya Nair");

        System.out.println(m3.getMembershipId());
    }
}