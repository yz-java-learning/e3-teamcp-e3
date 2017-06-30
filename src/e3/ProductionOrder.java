package e3;

public class ProductionOrder implements Observer, DisplayElement {

    private static int orderSequence;
    protected int ID;
    protected double minQuantity;
    protected Observable inventory;

    public ProductionOrder(int minQty, Observable inventory) {
        this.minQuantity = minQty;
        this.inventory = inventory;

        // auto increment by one each time we make a ProductionOrder
        ID = ++orderSequence;

        Inventory currentInventory = (Inventory) this.inventory;
        currentInventory.registerObserver(this);
    }

    /**
     * If the availableQuantity in our inventory is greater than or equal to
     * the quantity we ordered then we update the inventories quantities
     *
     * @param availQty Available quantity in our inventory
     * @param ordQty   Order quantity we make
     */
    public void update(double availQty, double ordQty) {
        if (ordQty >= this.minQuantity) {
            Inventory currentInventory = (Inventory) this.inventory;
            currentInventory.updateQuantities(ordQty, 0);
        }
    }

    public void display(double dispQty) {
        System.out.println("Quantity: " + dispQty);
    }

    public String toString() {
        return ("Minimum Quantity: " + this.minQuantity + ", " +
                "Inventory: " + this.inventory);
    }
}
