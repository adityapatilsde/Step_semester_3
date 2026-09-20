package WEEK3.assigment_problems;

public class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {

        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i] != null && rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {

        HostelRoom[] availableRooms = new HostelRoom[2];

        availableRooms[0] = new HostelRoom("C-214", 3, 2);
        availableRooms[1] = new HostelRoom("C-507", 2, 2);

        safeAllot(availableRooms, "Divya");

        HostelRoom[] fullRooms = new HostelRoom[2];

        fullRooms[0] = new HostelRoom("C-214", 3, 3);
        fullRooms[1] = new HostelRoom("C-507", 2, 2);

        safeAllot(fullRooms, "Divya");
    }

}

