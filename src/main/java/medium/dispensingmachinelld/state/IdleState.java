package medium.dispensingmachinelld.state;

import medium.dispensingmachinelld.DispensingMachine;

public class IdleState implements DispensingMachineState{

    @Override
    public void selectItems(DispensingMachine machine) {
        System.out.println("Items are selected");
        machine.setState(new ItemSelectedState());
    }

    @Override
    public void doPayment(DispensingMachine machine) {
        throw new IllegalStateException("Select items first");
    }

    @Override
    public void dispense(DispensingMachine machine) {
        throw new IllegalStateException("Select items first");
    }
}
