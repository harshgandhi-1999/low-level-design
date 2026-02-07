package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.AddOn;

public class ExtraDosageDecorator extends AddOnDecorator{


    protected ExtraDosageDecorator(PricedItem pricedItem, AddOn addOn) {
        super(pricedItem, addOn);
    }
}
