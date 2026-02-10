package medium.dispensingmachinelld.service;

import medium.dispensingmachinelld.decorator.*;
import medium.dispensingmachinelld.entities.AddOn;
import medium.dispensingmachinelld.entities.Medicine;

import java.util.List;

public class PricingService {

    public PricedItem calculatePrice(Medicine medicine, List<AddOn> addOns) {

        PricedItem pricedItem = new BaseMedicine(medicine);

        // now we are calculating the final price using decorator pattern
        for (AddOn addOn : addOns) {
            pricedItem = wrapWithDecorator(pricedItem, addOn);
        }

        return pricedItem;
    }

    private PricedItem wrapWithDecorator(PricedItem item, AddOn addOn) {
        return switch (addOn.getAddOnType()) {
            case EXTRA_DOSAGE -> new ExtraDosageDecorator(item, addOn);
            case PROTECTIVE_PACKAGING -> new PackagingDecorator(item, addOn);
            case MEASURING_ACCESSORY -> new MeasuringAccessoryDecorator(item, addOn);
            case SAFETY_INSERT -> new SafetyInsertDecorator(item, addOn);
        };
    }
}
