package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.AddOn;

public class AddOnDecorator implements PricedItem{

    // in decorator pattern we have two questions generally:
    // What am I wrapping?
    // What am I adding?
    protected final PricedItem pricedItem;  // this can be medicine or another decorator
    protected final AddOn addOn;

    protected AddOnDecorator(PricedItem pricedItem, AddOn addOn) {
        this.pricedItem = pricedItem;
        this.addOn = addOn;
    }

    @Override
    public double getPrice() {
        return pricedItem.getPrice() + addOn.getBasePrice();
    }

    @Override
    public String getDescription() {
        return pricedItem.getDescription() + " " + addOn.getName();
    }
}
