package easy.vendingmachinelld;

public class VendingMachine {

    private Inventory inventory;
    private VendingMachineState idleState;
    private VendingMachineState readyState;
    private VendingMachineState dispenseState;
    private VendingMachineState returnChangeState;

    private VendingMachineState currentState;

    private Product selectedProduct;

    private int totalPayment;

    private static VendingMachine INSTANCE;

    private VendingMachine(){
        inventory = new Inventory();
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);
        currentState = idleState;
        selectedProduct = null;
        totalPayment = 0;
    }

    public static synchronized VendingMachine getInstance(){
        if(INSTANCE == null){
            INSTANCE = new VendingMachine();
        }

        return INSTANCE;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void insertCash(int cash) {
        currentState.insertCash(cash);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    void setState(VendingMachineState state) {
        currentState = state;
    }

    VendingMachineState getIdleState() {
        return idleState;
    }

    VendingMachineState getReadyState() {
        return readyState;
    }

    VendingMachineState getDispenseState() {
        return dispenseState;
    }

    VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    Product getSelectedProduct() {
        return selectedProduct;
    }

    void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    void resetSelectedProduct() {
        selectedProduct = null;
    }

    int getTotalPayment() {
        return totalPayment;
    }

    void updateTotalAmount(int money) {
        totalPayment += money;
    }

    void resetPayment() {
        totalPayment = 0;
    }


}
