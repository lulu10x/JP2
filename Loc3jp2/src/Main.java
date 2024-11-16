
import Entity.RoomType;
import Service.BookingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        bookingService.initializeData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book a room");
            System.out.println("2. View bookings by customer");
            System.out.println("3. Calculate revenue by room type");
            System.out.println("4. Display room type with highest revenue in 2023");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.next();
                    System.out.print("Enter Room Type (Single, Double, Queen, Triple, Quad): ");
                    String roomTypeStr = scanner.next();
                    RoomType roomType = RoomType.valueOf(roomTypeStr);
                    bookingService.bookRoom(customerId, roomType);
                    break;
                case 2:
                    System.out.print("Enter Customer ID: ");
                    String cusId = scanner.next();
                    bookingService.displayBookingByCustomer(cusId);
                    break;
                case 3:
                    bookingService.calculateRevenueByRoomType();
                    break;
                case 4:
                    bookingService.displayHighestRevenueRoomType2023();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}