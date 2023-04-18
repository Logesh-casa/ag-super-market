import java.util.*;

public class Main {
  static void chooseTask(String input, Inventory invObj) {
    String splitted[] = input.split("=>");
    String task = splitted[0];
    String details = splitted[1];

    if (task.equals("INVENTORY")) {
      invObj.addProduct(details);
      System.out.println("Inventory updated.");
    } else if (task.equals("SALE")) {
      Sale saleObj = new Sale();
      saleObj.sell(details, invObj);
    } else if (task.equals("STOCK")) {
      int id = Integer.parseInt(details);
      int available = invObj.getQuantity(id);
      if (available != -1)
        System.out.println(invObj.productList.get(id).name + " - " + available);
      else 
        System.out.println("Product unavailable");
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
      System.out.println("Press 'q' to quit \nWaiting for command : ");
      String input = sc.nextLine();
      if (toQuit(input)) return;
      chooseTask(input, invObj);
    }
  }
}