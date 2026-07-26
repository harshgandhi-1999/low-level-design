package hard.fooddeliverysystem.discount;

public class DiscountResult {
    private int discountAmount;
    private String description;

    public DiscountResult(int discountAmount, String description) {
        this.discountAmount = discountAmount;
        this.description = description;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
