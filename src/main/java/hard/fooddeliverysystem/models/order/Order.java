package hard.fooddeliverysystem.models.order;

import hard.fooddeliverysystem.models.User;
import hard.fooddeliverysystem.models.cart.Cart;
import hard.fooddeliverysystem.models.payment.Payment;
import hard.fooddeliverysystem.models.restaurant.Restaurant;
import hard.fooddeliverysystem.models.DeliveryPartner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final String id;
    private final User user;
    private final Restaurant restaurant;
    private final LocalDateTime orderDate;
    private Instant instant;
    private Cart cart;
    private OrderStatus orderStatus;
    private Payment payment;

    // Pricing breakdown
    private double baseTotal;
    private double totalDiscount;
    private double taxAmount;
    private double deliveryFee;
    private double finalTotal;

    // Delivery info
    private DeliveryPartner deliveryPartner;

    public Order(User user, Restaurant restaurant, Cart cart) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.restaurant = restaurant;
        this.orderDate = LocalDateTime.now();
        this.instant = Instant.now();
        this.cart = cart;
        this.orderStatus = OrderStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public DeliveryPartner getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }
}
