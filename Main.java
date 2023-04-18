import java.util.*;

public class Main {
   static void handleStock (Inventory invObj, String details) {
      int id = Integer.parseInt(details);
      int available = invObj.getQuantity(id);
      if (available != -1)
         System.out.println("\n" + invObj.productList.get(id).name + " - " + available + "\n");
      else 
        System.out.println("\nProduct unavailable\n".toUpperCase());
   }

    static void handleInventory (Inventory invObj, String details) {
        invObj.addProduct(ParseInput.parseCommandLineProduct(details));
        System.out.println("\nInventory updated.\n".toUpperCase());
    }

    static void handleSale (Inventory invObj, String details) {
        Sale saleObj = new Sale(invObj);
        saleObj.sell(ParseInput.parseCommandLineSale(details));
    }

  static void chooseTask(String input, Inventory invObj) {
    String splitted[] = input.split("=>");
    String task = splitted[0].toUpperCase();
    String details = splitted[1];
    
    switch (task) {
      case "INVENTORY":
        handleInventory(invObj, details);
        break;

      case "SALE":
        handleSale(invObj, details);
        break;

      case "STOCK":
        handleStock(invObj, details);
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

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Inventory invObj = new Inventory();

    while (true) {
      System.out.println("PRESS 'q' TO QUIT \nWAITING FOR COMMAND : \n");
      String input = sc.nextLine();
      if (toQuit(input)) break;
      chooseTask(input, invObj);
    }
  }
}