package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.DeliveryPartner;
import hard.fooddeliverysystem.models.User;
import hard.fooddeliverysystem.models.cart.Cart;
import hard.fooddeliverysystem.models.order.Order;
import hard.fooddeliverysystem.models.order.OrderStatus;
import hard.fooddeliverysystem.models.payment.Payment;
import hard.fooddeliverysystem.models.payment.PaymentStatus;
import hard.fooddeliverysystem.models.payment.PaymentStrategy;
import hard.fooddeliverysystem.models.restaurant.Restaurant;

import java.util.*;

public class FoodOrderService {

    private static volatile FoodOrderService instance = null;

    private final Map<User, List<Order>> userOrderListMap;
    private final PricingService pricingService;
    private final DeliveryAssignmentService deliveryAssignmentService;

    private FoodOrderService() {
        this.userOrderListMap = new HashMap<>();
        this.pricingService = new PricingService();
        this.deliveryAssignmentService = new DeliveryAssignmentService();
        // seed a demo partner
        this.deliveryAssignmentService.registerPartner(new DeliveryPartner("Partner-1"));
    }

    public static FoodOrderService getInstance() {
        if (instance == null) {
            synchronized (FoodOrderService.class) {
                if (instance == null) {
                    instance = new FoodOrderService();
                }
            }
        }
        return instance;
    }

    public PricingService getPricingService() {
        return pricingService;
    }

    public DeliveryAssignmentService getDeliveryAssignmentService() {
        return deliveryAssignmentService;
    }

    public List<Order> getAllUserOrders(User user) {
        return userOrderListMap.getOrDefault(user, new ArrayList<>());
    }

    public Order createOrder(User user, Restaurant restaurant, Cart cart, PaymentStrategy paymentStrategy) {
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order(user, restaurant, cart);

        // pricing
        PricingService.PricingBreakdown pb = pricingService.calculateBreakdown(cart);
        order.setBaseTotal(pb.getBaseTotal());
        order.setTotalDiscount(pb.getTotalDiscount());
        order.setTaxAmount(pb.getTaxAmount());
        order.setDeliveryFee(pb.getDeliveryFee());
        order.setFinalTotal(pb.getFinalTotal());

        Payment payment = PaymentService.getInstance().pay(order.getFinalTotal(), paymentStrategy);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        order.setPayment(payment);

        if (payment.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
            order.setOrderStatus(OrderStatus.CONFIRMED);
        } else if (payment.getPaymentStatus().equals(PaymentStatus.FAILURE)) {
            order.setOrderStatus(OrderStatus.CANCELLED);
        }

        userOrderListMap.computeIfAbsent(user, k -> new ArrayList<>()).add(order);

        // todo: need to check if we can call this service outside of this create order method.
        // todo: also the user can have dynamic location and also the saved addresses so what to do of this.
        deliveryAssignmentService.assign(restaurant.getAddress(), user.getAddress());
        return order;
    }

    public String generateBill(User user, String orderId) {
        List<Order> orders = userOrderListMap.get(user);
        if (orders == null) throw new NoSuchElementException("User not found");
        Optional<Order> orderOpt = orders.stream().filter(o -> o.getId().equals(orderId)).findFirst();
        if (orderOpt.isEmpty()) throw new NoSuchElementException("Order not found");

        Order order = orderOpt.get();
        BillService billService = new BillService();
        return billService.generateBill(order);
    }

    public boolean cancelOrder(User user, String orderId) {
        Optional<Order> orderOptional = userOrderListMap.get(user).stream().filter(orderItem -> orderItem.getId().equals(orderId)).findFirst();

        if (orderOptional.isEmpty()) {
            throw new NoSuchElementException("No order found with orderId: " + orderId);
        }

        Order order = orderOptional.get();

        if (order.getOrderStatus().equals(OrderStatus.PENDING) || order.getOrderStatus().equals(OrderStatus.CONFIRMED)) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            if (order.getDeliveryPartner() != null) {
                deliveryAssignmentService.setAvailable(order.getDeliveryPartner());
            }
            return true;
        }
        return false;
    }
}
