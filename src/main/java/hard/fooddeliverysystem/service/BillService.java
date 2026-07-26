package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.cart.CartItem;
import hard.fooddeliverysystem.models.order.Order;

public class BillService {

    public String generateBill(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("==== FOOD ORDER BILL ====\n");
        sb.append("Order ID: ").append(order.getId()).append('\n');
        sb.append("User: ").append(order.getUser().getName()).append(" <").append(order.getUser().getEmail()).append(">\n");
        sb.append("Restaurant: ").append(order.getRestaurant().getName()).append('\n');
        sb.append("Status: ").append(order.getOrderStatus()).append('\n');
        sb.append("\nItems:\n");
        for (CartItem item : order.getCart().getItems()) {
            int line = item.getFood().getPrice() * item.getQuantity();
            sb.append(" - ")
              .append(item.getFood().getName())
              .append(" x ")
              .append(item.getQuantity())
              .append(" @ ")
              .append(item.getFood().getPrice())
              .append(" = ")
              .append(line)
              .append('\n');
        }
        sb.append("\nBase Total: ").append(order.getBaseTotal()).append('\n');
        sb.append("Discount: -").append(order.getTotalDiscount()).append('\n');
        sb.append("Tax: ").append(order.getTaxAmount()).append('\n');
        sb.append("Delivery Fee: ").append(order.getDeliveryFee()).append('\n');
        sb.append("---------------------------\n");
        sb.append("Final Total: ").append(order.getFinalTotal()).append('\n');
        if (order.getDeliveryPartner() != null) {
            sb.append("Delivery Partner: ").append(order.getDeliveryPartner().getName()).append('\n');
        }
        sb.append("===========================\n");
        return sb.toString();
    }
}
