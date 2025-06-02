import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FlowerShop {

    // Attributes
    private ArrayList<Flower> shopSelection;
    private ArrayList<Flower> bouquet;
    private String name;
    private TextUI ui;
    private double total;
    private final double arrangedAsBouquetPrice = 50.0;

    // Constructor
    FlowerShop(String name){

        shopSelection = new ArrayList<>();
        bouquet = new ArrayList<>();

        this.name = name;
        this.ui = new TextUI();
        this.total = 0.0;

        Collections.addAll(shopSelection,
                            new Flower("Tulip", 20.0),
                            new Flower("Lily", 40.0),
                            new Flower("Orchid", 80.0),
                            new Flower("Sunflower", 20.0),
                            new Flower("Lavender", 20.0),
                            new Flower("Pansy", 80.0),
                            new Flower("Peony", 40.0),
                            new Flower("Hydrangea", 80.0),
                            new Flower("Anemone", 40.0));
    }

    // Methods
    public void runDialog(){
        boolean keepGoing = true;
        int menuChoice;
        char confirmChoice;

        String welcomeMessage = "Welcome to the flowershop: " + this.name + "!" +
                                "\nChoose an option:" +
                                "\n1) Choose one flower" +
                                "\n2) Choose three flowers" +
                                "\n3) Checkout" +
                                "\n4) Exit";

        while (keepGoing) {
            menuChoice = ui.promptInteger(welcomeMessage);
            switch (menuChoice) {
                case 1:
                    chooseFlower();
                    break;
                case 2:
                    chooseThreeFlowers();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    exitProgram();
                default:
                    ui.displayMessage("Please choose an option from the list (integer input): ");
            }
        }
    }


    public void addFLower(String name, double price) {
        shopSelection.add(new Flower(name, price));
    }


    public void displayBouquetByPrice() {
        shopSelection.sort(Comparator.comparing(Flower::getPrice));

        for (Flower f : shopSelection) {
            ui.displayMessage(f);
        }
    }


    public void displayBouquetByName() {
        bouquet.sort(Comparator.comparing(Flower::getName));

        for (Flower f : bouquet){
            ui.displayMessage(f);
        }
    }


    private void chooseFlower() {
        displayBouquetByPrice();

        int choice = ui.promptInteger("Choose a flower from the list");
        Flower flower = shopSelection.get(choice - 1);
        bouquet.add(flower);
    }


    private void chooseThreeFlowers() {
        displayBouquetByPrice();
        int counter = 0;

        while (counter < 3) {
            int choice = ui.promptInteger("Choose a flower from the list");
            Flower flower = shopSelection.get(choice - 1);
            bouquet.add(flower);
            counter ++;
        }
    }


    private double calculateTotal() {
        double total = 0.0;

        for (Flower f : bouquet){
            total += f.getPrice();
        }
        return total;
    }


    private void checkout() {
        // List chosen flowers
        ui.displayMessage("You have chosen the following: ");
        displayBouquetByName();

        // List current total
        ui .displayMessage("Current total: ");
        this.total = calculateTotal();
        String formattedTotal = ui.formatPriceWithTwoDecimals(total);
        ui.displayMessage(formattedTotal);

        // Ask if buyer wants to pay for the bouquet to be arranged if more than one flower
        if (bouquet.size()>1){
            arrangeFlowers();
        }

        // Finalise transaction
        if (ui.promptBinary("Do you want to finalize your purchase?")){
            exitProgram();
        } else {
            return;
        }

    }


    private void arrangeFlowers(){
        String formmatedArrangeAsBouquetPrice = ui.formatPriceWithTwoDecimals(arrangedAsBouquetPrice);
        String question = "Do you want us to arrange your flowers for a total of " + formmatedArrangeAsBouquetPrice;
        String offerAccepted = "Your flowers will be arranged beautifully!\nNew total: ";

        if (ui.promptBinary(question)){
            this.total += arrangedAsBouquetPrice;
            String formattedTotal = ui.formatPriceWithTwoDecimals(total);
            ui.displayMessage(offerAccepted + formattedTotal);
        }
    }


    private void exitProgram() {
        ui.displayMessage("Have a nice day!");
        System.exit(0);
    }
}
