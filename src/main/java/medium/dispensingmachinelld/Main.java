package medium.dispensingmachinelld;

public class Main {

//    Designing an Automated Medicine Dispensing System
//    Requirements
//    - The system should support dispensing multiple base medicines, such as tablets, capsules, and syrups.
//    - Each base medicine should have a base price and standard dosage unit.
//    - A medicine can be customized with optional add-ons, such as:
//      Additional dosage units
//      Protective packaging
//      Measuring accessories (e.g., droppers, spoons)
//      Safety or information inserts
//    - Each add-on should have its own cost and should contribute to the final price of the medicine.
//    - Users should be able to select a base medicine and then choose one or more optional add-ons before checkout.
//    - The system should calculate the final payable amount dynamically based on the selected medicine and its add-ons.
//    - Users should be able to make payment and receive change if applicable.
//    - After successful payment, the system should dispense the medicine along with the selected add-ons.
//    - The system should track inventory for both base medicines and add-ons independently.
//    - The system should support concurrent user requests while ensuring thread safety and consistency.
//    - The system should handle failure scenarios, such as:
//      Add-on out of stock
//      Partial dispensing failure
//      Payment failure
//      Inventory mismatch

    public static void main(String[] args) {
        // always go one by one in low level design.
        // 1. First define all the entities.

        // 2. Now we will observe that addons are optional and both are having some price and we have to get total price at the end
        // so this gives us the idea of decorator patterm -> every medicine can be added with addon and gives total price
        // we have created pricing service which uses this decorator to get final price

        // 3. Now we will go to inventory check and reserve the item. For this we create InventoryService


        // 4. After this we would move to payment flow. So we will create PaymentService
    }
}
