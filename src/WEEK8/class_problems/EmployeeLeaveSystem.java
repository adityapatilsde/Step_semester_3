package WEEK8.class_problems;

abstract class LeaveEmployee {
    String name;

    LeaveEmployee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends LeaveEmployee {

    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends LeaveEmployee {

    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class ContractEmployee extends LeaveEmployee {

    ContractEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    LeaveEmployee employee;
    String startDate;
    String endDate;
    String status;

    LeaveRequest(LeaveEmployee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        status = "Pending";
    }

    void approve() {
        if (status.equals("Pending")) {
            status = "Approved";

            System.out.println("Leave request for " +
                    employee.name + " approved. Status: " + status);
        } else {
            System.out.println("Cannot approve request. Current status: "
                    + status);
        }
    }

    void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";

            System.out.println("Leave request for " +
                    employee.name + " rejected. Status: " + status);
        } else {
            System.out.println("Cannot reject request. Current status: "
                    + status);
        }
    }

    void makePending() {
        if (status.equals("Pending")) {
            System.out.println("Request is already Pending.");
        } else {
            System.out.println("Cannot change status: " + status +
                    " request cannot revert to Pending.");
        }
    }
}

class LeaveManager {

    void submitRequest(LeaveRequest request) {
        System.out.println("Leave request submitted by " +
                request.employee.name + " for " +
                request.startDate + " to " +
                request.endDate + ". Status: " +
                request.status);
    }

    void reviewRequest(LeaveRequest request, boolean approve) {
        if (approve) {
            request.approve();
        } else {
            request.reject();
        }
    }
}

public class EmployeeLeaveSystem {
    public static void main(String[] args) {

        LeaveEmployee john =
                new FullTimeEmployee("John Doe");

        LeaveEmployee jane =
                new PartTimeEmployee("Jane Smith");

        LeaveRequest johnRequest =
                new LeaveRequest(
                        john,
                        "2024-10-10",
                        "2024-10-12");

        LeaveRequest janeRequest =
                new LeaveRequest(
                        jane,
                        "2024-11-01",
                        "2024-11-05");

        LeaveManager manager = new LeaveManager();

        manager.submitRequest(johnRequest);

        manager.reviewRequest(johnRequest, true);

        manager.submitRequest(janeRequest);

        johnRequest.makePending();
    }
}