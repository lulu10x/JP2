package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private List<Room> rooms = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public void initializeData() {
        rooms = List.of(
                new Room("RS001", RoomType.Single, 8.0),
                new Room("RD001", RoomType.Double, 12.0),
                new Room("RQ002", RoomType.Queen, 35.0),
                new Room("RT001", RoomType.Triple, 12.5),
                new Room("RQ001", RoomType.Quad, 20.5)
        );

        customers = List.of(
                new Customer("001", "Mr.Linus Tovaldo", "84125325346457"),
                new Customer("002", "Mr.Bill", "91124235346467"),
                new Customer("003", "Mr.Turing", "911423534646")
        );

        bookings = List.of(
                new Booking("1", "RS001", "001", LocalDateTime.parse("2023-03-15T09:30:15"), LocalDateTime.parse("2023-03-16T12:30:45")),
                new Booking("2", "RS001", "002", LocalDateTime.parse("2023-06-09T19:30:25"), LocalDateTime.parse("2023-06-10T11:25:15")),
                new Booking("3", "RD001", "002", LocalDateTime.parse("2023-03-11T10:10:05"), LocalDateTime.parse("2023-03-13T11:05:10")),
                new Booking("4", "RT001", "003", LocalDateTime.parse("2023-11-11T11:11:15"), LocalDateTime.parse("2023-11-13T11:15:15")),
                new Booking("5", "RT001", "001", LocalDateTime.parse("2023-10-25T09:20:25"), LocalDateTime.parse("2023-10-26T12:25:30")),
                new Booking("6", "RQ001", "001", LocalDateTime.parse("2023-08-18T18:25:35"), LocalDateTime.parse("2023-08-19T11:35:20"))
        );
    }

    public void bookRoom(String customerId, RoomType roomType) {
        rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .findFirst()
                .ifPresentOrElse(
                        room -> {
                            Booking booking = new Booking(
                                    UUID.randomUUID().toString(),
                                    room.getId(),
                                    customerId,
                                    LocalDateTime.now(),
                                    null
                            );
                            bookings.add(booking);
                            System.out.println("Booking successful: " + booking);
                        },
                        () -> System.out.println("No available room of type " + roomType)
                );
    }

    public void displayBookingByCustomer(String customerId) {
        bookings.stream()
                .filter(booking -> booking.getCustomerId().equals(customerId))
                .forEach(System.out::println);
    }

    public void calculateRevenueByRoomType() {
        bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> rooms.stream()
                                .filter(room -> room.getId().equals(booking.getRoomId()))
                                .map(Room::getRoomType)
                                .findFirst()
                                .orElse(null),
                        Collectors.summingDouble(booking -> {
                            Room room = rooms.stream()
                                    .filter(r -> r.getId().equals(booking.getRoomId()))
                                    .findFirst()
                                    .orElse(null);
                            if (room == null) return 0.0;
                            long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                            return hours * room.getPricePerHour();
                        })
                ))
                .forEach((roomType, revenue) -> System.out.println(roomType + ": $" + revenue));
    }

    public void displayHighestRevenueRoomType2023() {
        RoomType maxRoomType = bookings.stream()
                .filter(booking -> booking.getCheckIn().getYear() == 2023)
                .collect(Collectors.groupingBy(
                        booking -> rooms.stream()
                                .filter(room -> room.getId().equals(booking.getRoomId()))
                                .map(Room::getRoomType)
                                .findFirst()
                                .orElse(null),
                        Collectors.summingDouble(booking -> {
                            Room room = rooms.stream()
                                    .filter(r -> r.getId().equals(booking.getRoomId()))
                                    .findFirst()
                                    .orElse(null);
                            if (room == null) return 0.0;
                            long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
                            return hours * room.getPricePerHour();
                        })
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("RoomType with highest revenue in 2023: " + maxRoomType);
    }
}
