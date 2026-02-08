package medium.dispensingmachinelld;

import medium.dispensingmachinelld.state.DispensingMachineState;
import medium.dispensingmachinelld.state.IdleState;

public class DispensingMachine {

    // instead of creating every time new object when changing the state inside concrete state classes,
    // we can also initialize all concrete state in this class and create getters here to use there in concrete state classes

//    private IdleState idleState = new IdleState();
//    private ItemSelectedState itemSelectedState = new ItemSelectedState();
//    private PaymentDoneState paymentDoneState = new PaymentDoneState();
//    private ItemDispensedState itemDispensedState = new ItemDispensedState();

    private DispensingMachineState machineState;

    // Here we can create service class object to interact with
//    PricingService pricingService;
//    InventoryService inventoryService;
//    PaymentService paymentService;
//    DispenseService dispenseService;

//    Medicine medicine;
//    List<AddOn> addOns;

    public DispensingMachine() {
        machineState = new IdleState();
    }

    public void setState(DispensingMachineState machineState){
        this.machineState = machineState;
    }

    public DispensingMachineState getState() {
        return machineState;
    }

    public void selectItem() {
        machineState.selectItems(this);
    }

    public void makePayment() {
        machineState.doPayment(this);
    }

    public void dispense() {
        machineState.dispense(this);
    }
}
