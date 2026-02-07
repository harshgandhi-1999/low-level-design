package medium.dispensingmachinelld.service;

import medium.dispensingmachinelld.entities.AddOn;
import medium.dispensingmachinelld.entities.Medicine;

import java.util.List;

class InventoryService {

    public boolean isAvailable(Medicine m, List<AddOn> addOns) { return true;}

    public void reserve(Medicine m, List<AddOn> addOns) {}

    public void release(Medicine m, List<AddOn> addOns) {}

}