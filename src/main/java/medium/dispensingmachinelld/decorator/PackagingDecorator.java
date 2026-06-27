package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.AddOn;

public class PackagingDecorator extends AddOnDecorator{

    public PackagingDecorator(PricedItem pricedItem, AddOn addOn) {
        super(pricedItem, addOn);
    }
}
