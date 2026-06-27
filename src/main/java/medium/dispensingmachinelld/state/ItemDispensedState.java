package medium.dispensingmachinelld.state;

import medium.dispensingmachinelld.DispensingMachine;

public class ItemDispensedState implements DispensingMachineState{

    @Override
    public void selectItems(DispensingMachine machine) {
        throw new IllegalStateException("Selected items already dispensed. Start again!");
    }

    @Override
    public void doPayment(DispensingMachine machine) {
        throw new IllegalStateException("Payment has already been done for dispensed items");
    }

    @Override
    public void dispense(DispensingMachine machine) {
        throw new IllegalStateException("Items are already dispensed");
    }
}
