package Service;

import Entity.OrderDetail;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingService {

    public void generateBill(int customerId, List<OrderDetail> orderDetails, Map<Integer, Double> products, String outputFileName) {

        List<Integer> customerOrderIds = orderDetails.stream()
                .filter(detail -> detail.getCustomerId() == customerId)
                .map(OrderDetail::getOrderId)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Mã đơn hàng của khách hàng: " + customerOrderIds);

        StringBuilder result = new StringBuilder();

        double totalAmount = orderDetails.stream()
                .filter(detail -> customerOrderIds.contains(detail.getOrderId()))
                .mapToDouble(detail -> {
                    Double productPrice = products.get(detail.getProductId());
                    if (productPrice == null) {
                        System.out.println("Không tìm thấy giá cho sản phẩm ID " + detail.getProductId());
                        return 0;
                    }
                    double subTotal = detail.getQuantity() * productPrice;

                    result.append(customerId)
                            .append("; ")
                            .append(detail.getOrderId())
                            .append("; ")
                            .append(detail.getProductId())
                            .append("; ")
                            .append(detail.getQuantity())
                            .append("; ")
                            .append(String.format("%.2f", productPrice))
                            .append("; ")
                            .append(String.format("%.2f", subTotal))
                            .append("\n");

                    return subTotal;
                })
                .sum();

        System.out.println("Tổng số tiền: " + totalAmount);

        result.append("TotalBillingAmount: ").append(String.format("%.2f", totalAmount));

        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(result.toString());
            System.out.println("Thanh toán đã được ghi vào file thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}