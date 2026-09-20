package WEEK3.class_problems;

class HRParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    HRParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    HRParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, HRParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            pay = manager.effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        if (slot != null) {
            return name + " | Pay: Rs " + pay
                    + " | Slot: " + slot.slotNo;
        } else {
            return name + " | Pay: Rs " + pay
                    + " | Slot: no parking assigned";
        }
    }
}

public class HRParkingMiniSystem {

    static HRParkingSlot findAvailableSlot(HRParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null &&
                    slots[i].occupiedCount < slots[i].capacity) {

                return slots[i];
            }
        }

        return null;
    }

    static void safeAllot(HRParkingSlot[] slots, String vehicleNo) {

        HRParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No parking slots available.");
        }
    }

    public static void main(String[] args) {

        HRParkingSlot[] parkingSlots = {
                new HRParkingSlot("A1", 1, 0),
                new HRParkingSlot("A2", 1, 0)
        };

        ManagerEmployee divyaEmployee =
                new ManagerEmployee(101, "Divya", 65000, 13000);

        Employee karanEmployee =
                new Employee(102, "Karan", 40000);

        Employee meeraEmployee =
                new Employee(103, "Meera", 10000);

        safeAllot(parkingSlots, "DIVYA101");
        HRParkingSlot divyaSlot = parkingSlots[0];

        safeAllot(parkingSlots, "KARAN102");
        HRParkingSlot karanSlot = parkingSlots[1];

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord(
                        "Divya", "E101", divyaEmployee, divyaSlot);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord(
                        "Karan", "E102", karanEmployee, karanSlot);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord(
                        "Meera", "E103", meeraEmployee, null);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}