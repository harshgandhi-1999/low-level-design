package medium.dispensingmachinelld.service;

import medium.dispensingmachinelld.entities.AddOn;
import medium.dispensingmachinelld.entities.Medicine;

import java.util.List;

public class DispensingService {

    public boolean dispense(Medicine medicine, List<AddOn> addOns) {

        try {
            dispenseMedicine(medicine);

            for (AddOn addOn : addOns) {
                dispenseAddOn(addOn);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Partial dispensing failure");
            return false;
        }
    }

    private void dispenseMedicine(Medicine medicine) {
        // interact with hardware
    }

    private void dispenseAddOn(AddOn addOn) {
        // interact with hardware
    }
}
