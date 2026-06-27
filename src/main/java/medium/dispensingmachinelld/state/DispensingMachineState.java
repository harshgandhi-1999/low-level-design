package medium.dispensingmachinelld.state;

import medium.dispensingmachinelld.DispensingMachine;

public interface DispensingMachineState {

    // lets write down all the operations that can be performed which changes state of DispensingMachine
    void selectItems(DispensingMachine machine);
    void doPayment(DispensingMachine machine);
    void dispense(DispensingMachine machine);
}
