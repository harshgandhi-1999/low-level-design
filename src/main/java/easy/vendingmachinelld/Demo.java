package easy.vendingmachinelld;

public class Demo {
    public static void main(String[] args) {

        // Requirements:
        // 1. Display of all products with their price.
        // 2. Insert Money btn.
        // 3. Select Product
        // 4. Cancel - getRefund.
        // 5. refund if it is there
        // 6. dispense product

        // Classes:
        // 1. Product
        // 2. Inventory
        // 3. VendingMachineState

        VendingMachine vendingMachine = VendingMachine.getInstance();

        Product coke = new Product(1,"Coke",10);
        Product pepsi = new Product(2,"Pepsi",30);
        Product water = new Product(3,"Water",20);

        vendingMachine.getInventory().addProduct(coke,2);
        vendingMachine.getInventory().addProduct(pepsi,2);
        vendingMachine.getInventory().addProduct(water,1);


        vendingMachine.selectProduct(water);
        vendingMachine.insertCash(25);
        vendingMachine.dispenseProduct();
        vendingMachine.returnChange();
    }
}
