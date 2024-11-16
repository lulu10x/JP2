import Entity.OrderDetail;
import Service.BillingService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        Map<Integer, Double> products = new HashMap<>();
        products.put(121, 120.0);
        products.put(122, 125.0);
        products.put(125, 105.0);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<OrderDetail> orderDetails = readOrderDetailsFromFile("Data/OrderDetail.in.txt", sdf);


        BillingService billingService = new BillingService();
        billingService.generateBill(101, orderDetails, products, "Data/OrderCustomer.out.txt");

        System.out.println("Hóa đơn cho khách hàng 101 đã được tạo.");
    }


    private static List<OrderDetail> readOrderDetailsFromFile(String fileName, SimpleDateFormat sdf) throws IOException, ParseException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                int orderId = Integer.parseInt(fields[0]);
                int customerId = Integer.parseInt(fields[1]);
                int productId = Integer.parseInt(fields[2]);
                int quantity = Integer.parseInt(fields[3]);
                double price = Double.parseDouble(fields[4]);
                Date orderDate = sdf.parse(fields[5]);
                orderDetails.add(new OrderDetail(orderId, customerId, productId, quantity, price));
            }
        }
        return orderDetails;
    }
}