package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.cart.Cart;
import hard.fooddeliverysystem.discount.Discount;

import java.util.Collections;
import java.util.List;

public class PricingService {

    private List<Discount> availableDiscounts = Collections.emptyList();
    private double taxRatePercent = 5.0; // e.g., 5% tax
    private int baseDeliveryFee = 30; // flat delivery fee

    public static class PricingBreakdown {
        private final int baseTotal;
        private final int totalDiscount;
        private final int taxAmount;
        private final int deliveryFee;
        private final int finalTotal;

        public PricingBreakdown(int baseTotal, int totalDiscount, int taxAmount, int deliveryFee) {
            this.baseTotal = baseTotal;
            this.totalDiscount = totalDiscount;
            this.taxAmount = taxAmount;
            this.deliveryFee = deliveryFee;
            this.finalTotal = Math.max(0, baseTotal - totalDiscount + taxAmount + deliveryFee);
        }

        public int getBaseTotal() { return baseTotal; }
        public int getTotalDiscount() { return totalDiscount; }
        public int getTaxAmount() { return taxAmount; }
        public int getDeliveryFee() { return deliveryFee; }
        public int getFinalTotal() { return finalTotal; }
    }

    public void setAvailableDiscounts(List<Discount> availableDiscounts) {
        this.availableDiscounts = (availableDiscounts == null) ? Collections.emptyList() : availableDiscounts;
    }

    public void setTaxRatePercent(double taxRatePercent) {
        this.taxRatePercent = Math.max(0, taxRatePercent);
    }

    public void setBaseDeliveryFee(int baseDeliveryFee) {
        this.baseDeliveryFee = Math.max(0, baseDeliveryFee);
    }

    public PricingBreakdown calculateBreakdown(Cart cart) {
        int baseTotal = cart.calculateBaseTotal();

        int totalDiscount = 0;
        if (availableDiscounts != null) {
            for (Discount discount : availableDiscounts) {
                if (discount != null && discount.isApplicable(cart)) {
                    totalDiscount += Math.max(0, discount.apply(cart).getDiscountAmount());
                }
            }
        }
        int taxableAmount = Math.max(0, baseTotal - totalDiscount);
        int taxAmount = (int) Math.floor((taxableAmount * taxRatePercent) / 100.0);
        int deliveryFee = baseDeliveryFee;

        return new PricingBreakdown(baseTotal, totalDiscount, taxAmount, deliveryFee);
    }

    public int calculateFinalPrice(Cart cart) {
        return calculateBreakdown(cart).getFinalTotal();
    }
}
