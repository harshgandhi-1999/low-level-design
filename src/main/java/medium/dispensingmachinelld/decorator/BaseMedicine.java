package medium.dispensingmachinelld.decorator;

import medium.dispensingmachinelld.entities.Medicine;

public class BaseMedicine implements PricedItem{
    // Do NOT modify the Medicine entity you already created. Create a wrapper instead
    private Medicine medicine;

    public BaseMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public double getPrice() {
        return medicine.getBasePrice();
    }

    @Override
    public String getDescription() {
        return medicine.getDescription();
    }
}
