package medium.dispensingmachinelld.state;

import medium.dispensingmachinelld.DispensingMachine;

public class PaymentDoneState implements DispensingMachineState{

    @Override
    public void selectItems(DispensingMachine machine) {
        throw new IllegalStateException("Payment has already been done for selected items");
    }

    @Override
    public void doPayment(DispensingMachine machine) {
        throw new IllegalStateException("Payment has already been done");
    }

    @Override
    public void dispense(DispensingMachine machine) {
        System.out.println("Dispensing items");
        machine.setState(new ItemDispensedState());
    }
}
