public class Flower {

    // ---------- Attributes ----------
    private String name;
    private double price;

    // ---------- Constructor ----------
    Flower(String name, double price){
        this.name = name;
        this.price = price;
    }

    // ---------- Methods ----------
    @Override
    public String toString(){
        String formattedPrice = String.format("%.2f,-", this.price); // Display price with two decimals
        return this.name + ": " + formattedPrice;
    }

    // Accessors
    public String getName(){
        return this.name;
    }

    public double getPrice() {
        return price;
    }

    // Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
