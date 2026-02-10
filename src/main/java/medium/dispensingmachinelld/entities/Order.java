package medium.dispensingmachinelld.entities;

import java.util.List;

public class Order {

    private String id;
    private Medicine medicine;
    private List<AddOn> addons;
    private double totalAmount;
    private OrderStatus orderStatus;
}
