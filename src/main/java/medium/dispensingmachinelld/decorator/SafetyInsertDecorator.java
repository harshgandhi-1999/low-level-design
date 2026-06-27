package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.AddOn;

public class SafetyInsertDecorator extends AddOnDecorator{
    public SafetyInsertDecorator(PricedItem pricedItem, AddOn addOn) {
        super(pricedItem, addOn);
    }
}
