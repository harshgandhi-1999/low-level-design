package easy.vendingmachinelld;

public interface VendingMachineState {

    void selectProduct(Product product);
    void insertCash(int money);

    void dispenseProduct();

    void returnChange();
}
