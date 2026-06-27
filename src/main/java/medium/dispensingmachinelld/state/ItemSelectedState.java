package medium.dispensingmachinelld.state;

import medium.dispensingmachinelld.DispensingMachine;

public class ItemSelectedState implements DispensingMachineState{

    @Override
    public void selectItems(DispensingMachine machine) {
        throw new IllegalStateException("Items are already selected. Proceed with payment");
    }

    @Override
    public void doPayment(DispensingMachine machine) {
        System.out.println("Payment Accepted");
        machine.setState(new PaymentDoneState());
    }

    @Override
    public void dispense(DispensingMachine machine) {
        throw new IllegalStateException("Payment not done currently");
    }
}
