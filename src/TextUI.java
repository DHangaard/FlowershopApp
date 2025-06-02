import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    // Attributes
    private Scanner scanner;

    // Methods
    // Display Methods
    public void displayMessage(String message) {
        System.out.println(message);
    }


    // Overloaded displayMessage that prints an objects toString()
    public void displayMessage(Object object) {
        System.out.println(object.toString());
    }


    public String formatPriceWithTwoDecimals(double total) {
        String formattedTotal = String.format("%.2f,-", total);
        return formattedTotal;
    }


    // Prompt methods
    public int promptInteger(String message) {
        scanner = new Scanner(System.in);
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
        scanner.close();
        return input;
    }


    public String promptText(String message) {
        scanner = new Scanner(System.in);

        displayMessage(message);
        String input = scanner.nextLine();

        scanner.close();
        return input;
    }


    public boolean promptBinary(String message) {
        scanner = new Scanner(System.in);
        boolean isChoosing = true;
        boolean isAnswerYes = false;
        String choice;

        while (isChoosing) {
            choice = promptText(message);
            if (choice.equalsIgnoreCase("y")){
                isAnswerYes = true;
                isChoosing = false;

            } else if (choice.equalsIgnoreCase("n")){
                isAnswerYes = false;
                isChoosing = false;

            } else if (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("n")) {
                displayMessage("Error: input either 'y' for yes or 'n' for no: ");
            }
        }
        scanner.close();
        return isAnswerYes;
    }
}
