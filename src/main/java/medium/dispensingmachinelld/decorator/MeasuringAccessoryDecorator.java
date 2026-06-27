package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.AddOn;

public class MeasuringAccessoryDecorator extends AddOnDecorator{
    public MeasuringAccessoryDecorator(PricedItem pricedItem, AddOn addOn) {
        super(pricedItem, addOn);
    }
}
