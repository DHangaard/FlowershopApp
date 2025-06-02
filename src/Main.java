public class Main {

    public static void main(String[] args) {

        FlowerShop flowerShop = new FlowerShop("Rose & Lily's Beautiful Bouquets");
        flowerShop.addFLower("Rose", 20);

        flowerShop.runDialog();

        // TODO Error handling when wrong input
    }
}