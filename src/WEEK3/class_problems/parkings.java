package WEEK3.class_problems;

class ParkingSlot {

    int slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(int slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println("Vehicle " + vehicleNo
                    + " allotted to slot " + slotNo);
        }
    }
}

public class parkings {

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for vehicle " + vehicleNo);
        }
    }

    public static void main(String[] args) {

        ParkingSlot[] availableSlots = {
                new ParkingSlot(1, 2, 1),
                new ParkingSlot(2, 2, 2)
        };

        ParkingSlot[] fullSlots = {
                new ParkingSlot(3, 2, 2),
                new ParkingSlot(4, 2, 2)
        };

        System.out.println("First allocation:");
        safeAllot(availableSlots, "TN01AB1234");

        System.out.println();

        System.out.println("Second allocation:");
        safeAllot(fullSlots, "TN02CD5678");
    }
}