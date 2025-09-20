1. Key Entities / Classes
1.1 User (Base class for employees/managers)
public class User {
    private String userId;
    private String name;
    private String email;

    // Constructor, Getters, and Setters
}

1.2 ConferenceRoom
public class ConferenceRoom {
    private String roomId;
    private String name;
    private int capacity;
    private List<String> equipmentList;
    private String location;

    // Constructor, Getters, Setters
}

1.3 TimeSlot
public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor, Getters, Overlap Check
    public boolean overlaps(TimeSlot other) {
        return !(this.endTime.isBefore(other.startTime) || this.startTime.isAfter(other.endTime));
    }
}

1.4 Booking
public class Booking {
    private String bookingId;
    private User user;
    private ConferenceRoom room;
    private TimeSlot slot;

    // Constructor, Getters, Setters
}

1.5 BookingManager
public class BookingManager {
    private Map<String, ConferenceRoom> rooms = new HashMap<>();
    private Map<String, List<Booking>> roomBookings = new HashMap<>();
    private Map<String, List<Booking>> userBookings = new HashMap<>();

    // Methods
    public void addConferenceRoom(ConferenceRoom room);
    public List<ConferenceRoom> searchAvailableRooms(TimeSlot slot, int capacity, List<String> equipment);
    public Booking bookRoom(String userId, String roomId, TimeSlot slot);
    public boolean cancelBooking(String bookingId);
    public List<Booking> getUserBookings(String userId);
}

2. Interfaces
SearchService
public interface SearchService {
    List<ConferenceRoom> search(TimeSlot slot, int capacity, List<String> equipment);
}

BookingService
public interface BookingService {
    Booking bookRoom(String userId, String roomId, TimeSlot slot);
    boolean cancelBooking(String bookingId);
    List<Booking> getBookingsForUser(String userId);
}

3. Relationships

User ↔ Booking: One-to-many

ConferenceRoom ↔ Booking: One-to-many

BookingManager aggregates Booking, ConferenceRoom

Composition used in TimeSlot within Booking

Services interface-based for future extendability


ER diagram

<img width="1454" height="776" alt="image" src="https://github.com/user-attachments/assets/ba4502c0-f42b-4243-9697-927040d19a4d" />

Sequence diagram
<img width="1554" height="1378" alt="image" src="https://github.com/user-attachments/assets/8f83a262-3100-49b0-93f1-551e9463c7ad" />

APIs to Implement

Operation	Method Signature

Add Room	void addConferenceRoom(ConferenceRoom room)

Search Rooms	List<ConferenceRoom> searchAvailableRooms(TimeSlot slot, int capacity, List<String> equipment)

Book Room	Booking bookRoom(String userId, String roomId, TimeSlot slot)

Cancel Booking	boolean cancelBooking(String bookingId)

View User Bookings	List<Booking> getUserBookings(String userId)
