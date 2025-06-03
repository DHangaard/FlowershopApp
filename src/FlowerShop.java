import java.util.*;

public class FlowerShop {

    // ---------- Attributes ----------
    private List<Flower> shopSelection;
    private Map<Flower, Integer> bouquet;
    private String name;
    private TextUI ui;
    private double total;
    private boolean isFlowersArranged;
    private final double arrangedAsBouquetPrice = 50.0;


    // ---------- Constructor ----------
    FlowerShop(String name){

        shopSelection = new ArrayList<>();
        bouquet = new HashMap<>();

        this.name = name;
        this.ui = new TextUI();
        this.total = 0.0;
        this.isFlowersArranged = false;

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


    // ---------- Methods ----------
    public void runDialog(){
        boolean keepGoing = true;
        int choice;

        String welcomeMessage = "Welcome to the flowershop: " + this.name + "!" +
                                "\nChoose an option:" +
                                "\n1) Choose one flower" +
                                "\n2) Choose three flowers" +
                                "\n3) Checkout" +
                                "\n4) Exit";

        while (keepGoing) {
            choice = ui.promptInteger(welcomeMessage);
            switch (choice) {
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
                    ui.displayMessage("'" + choice + "' is not on the menu, please try again:\n");
            }
        }
    }


    public void addFLower(String name, double price) {
        shopSelection.add(new Flower(name, price));
    }


    public void displayBouquetByPrice() {
        shopSelection.sort(Comparator.comparing(Flower::getPrice));
        int counter = 1;

        for (Flower f : shopSelection) {
            ui.displayMessage(counter + ") " + f.toString());
            counter++;
        }
    }


    public void displayBouquetByName() {

        // Get info from HashMap
        for (Map.Entry<Flower, Integer> entry : bouquet.entrySet()) {
            String name = entry.getKey().getName();
            int amount = entry.getValue();
            double typeTotal = entry.getKey().getPrice() * amount;
            String typeTotalFormatted = formatPriceWithTwoDecimals(typeTotal);

            // Determine whether singular or plural
            String pieceOrPieces = amount > 1 ? " pieces of " : " piece of ";

            // Display buyers choice with price
            ui.displayMessage(amount + pieceOrPieces + name + ": " + typeTotalFormatted);
        }
    }


    private void chooseFlower() {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            displayBouquetByPrice();
            choice = ui.promptInteger("Choose a flower from the list: ");

            if (choice >= 1 && choice <= 10) {
                keepGoing = false;

            } else {
                ui.displayMessage("'" + choice + "' is not on the list, please try again:\n");
            }
        }

        Flower flower = shopSelection.get(choice - 1);

        // Check if flower already exists in Map, then add 1 instance to Map
        bouquet.put(flower, bouquet.getOrDefault(flower, 0) +1);

        // Display choice
        ui.displayMessage("You chose: " + flower.getName() + "\n");
    }


    private void chooseThreeFlowers() {
        int counter = 0;

        while (counter < 3) {
            chooseFlower();
            counter ++;
        }
    }


    private double calculateTotal() {
        double total = 0.0;

        for (Map.Entry<Flower, Integer> entry : bouquet.entrySet()){
            Flower flower = entry.getKey();
            int amount = entry.getValue();
            double typeTotal = flower.getPrice() * amount;
            total += typeTotal;
        }

        if (isFlowersArranged){
            total += arrangedAsBouquetPrice;
        }

        return total;
    }


    private void checkout() {
        // List chosen flowers
        ui.displayMessage("You have chosen the following: ");
        displayBouquetByName();

        // List if flowers are arranged
        if (isFlowersArranged) {
            ui.displayMessage("You have chosen to get your bouquet arranged");
        }

        // List current total
        this.total = calculateTotal();
        String formattedTotal = formatPriceWithTwoDecimals(total);
        ui .displayMessage("Current total: " + formattedTotal + "\n"); // Add empty line

        // Ask if buyer wants to pay for the bouquet to be arranged if more than one flower
        if (bouquet.size()>1 && !isFlowersArranged){
            arrangeFlowers();
        }

        // Finalise transaction
        if (ui.promptBinary("Do you want to finalize your purchase?")){
            ui.displayMessage("The total cost is: " + formattedTotal);
            exitProgram();
        }
    }


    private void arrangeFlowers(){
        String formmatedArrangeAsBouquetPrice = formatPriceWithTwoDecimals(arrangedAsBouquetPrice);
        String question = "Do you want us to arrange your flowers for a total of " + formmatedArrangeAsBouquetPrice;
        String offerAccepted = "Your flowers will be arranged beautifully!\nNew total: ";

        if (ui.promptBinary(question)) {
            this.total += arrangedAsBouquetPrice;
            String formattedTotal = formatPriceWithTwoDecimals(total);
            ui.displayMessage(offerAccepted + formattedTotal);
            this.isFlowersArranged = true;
        }

    }


    public String formatPriceWithTwoDecimals(double total) {
        String formattedTotal = String.format("%.2f,-", total);
        return formattedTotal;
    }


    private void exitProgram() {
        ui.displayMessage("Thank you!\nHave a nice day!");
        ui.closeScanner();
        System.exit(0);
    }
}
