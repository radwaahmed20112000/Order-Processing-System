package GUI;

public class Item {
    private String label;
    private int amount;
    private double cost;

    public Item(String label, int amount, double cost) {
        this.label = label;
        this.amount = amount;
        this.cost = cost;
    }

    public String getLabel() {
        return label;
    }

    public int getAmount() {
        return amount;
    }

    public double getCost() {
        return cost;
    }
}
