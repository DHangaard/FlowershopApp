import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    // ---------- Attributes ----------
    private Scanner scanner;


    // ---------- Constructor ----------
    TextUI() {
       scanner = new Scanner(System.in);
    }


    // ---------- Methods ----------
    // Display Methods
    public void displayMessage(String message) {
        System.out.println(message);
    }


    // Prompt methods
    public int promptInteger(String message) {
        int input = 0;
        boolean keepGoing = true;

        while (keepGoing) {
            displayMessage(message);

            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Flush
                keepGoing = false;

            } catch (InputMismatchException e) {
                scanner.nextLine(); // Flush - flush in catch to avoid infinite loop
                displayMessage("Error: use an integer as input.\n Try again:");
            }
        }
        System.out.println(); // Empty line
        return input;
    }


    public String promptText(String message) {

        displayMessage(message);
        String input = scanner.nextLine();

        System.out.println(); // Empty line
        return input;
    }


    public boolean promptBinary(String message) {
        boolean keepGoing = true;
        boolean isAnswerYes = false;
        String choice;

        while (keepGoing) {
            choice = promptText(message);
            if (choice.equalsIgnoreCase("y")){
                isAnswerYes = true;
                keepGoing = false;

            } else if (choice.equalsIgnoreCase("n")){
                isAnswerYes = false;
                keepGoing = false;

            } else if (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("n")) {
                displayMessage("Error: input either 'y' for yes or 'n' for no: ");
            }
        }
        System.out.println(); // Empty line
        return isAnswerYes;
    }


    public void closeScanner() {
        scanner.close();
    }
}
