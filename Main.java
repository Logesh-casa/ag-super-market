import java.util.*;

public class Main {
   static Store store = new Store();

   public static void main(String args[]) {
   Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("PRESS 'q' TO QUIT \nWAITING FOR COMMAND : \n");
      String input = sc.nextLine();
      if (toQuit(input)) break;
      chooseTask(input);
    }
  }
  
   static void handleStock (String details) {
      int id = Integer.parseInt(details);
      int available = store.getQuantity(id);
      String name = store.getName(id);
      System.out.println("\n" + name + " - " + available + "\n");
   }

    static void handleInventory (String details) {
        store.addProduct(ParseInput.parseCommandLineProduct(details));
        System.out.println("\nInventory updated.\n".toUpperCase());
    }

    static void handleSale (String details) {
        store.sell(ParseInput.parseCommandLineSale(details));
    }

  static void chooseTask(String input) {
    String splitted[] = input.split("=>");
    String task = splitted[0].toUpperCase();
    String details = splitted[1];

    switch (task) {
      case "INVENTORY":
        handleInventory(details);
        break;

      case "SALE":
        handleSale(details);
        break;

      case "STOCK":
        handleStock(details);
        break;

      default:
        System.out.println("\n** INVALID COMMAND **\n");
        break;
    }
  }

  static boolean toQuit(String input) {
    if (input.equals("q")) return true;
    return false;
  }
}