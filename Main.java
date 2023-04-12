import java.util.*;

class Product {
  String name;
  int id;
  double price;

  Product(String name, int id, double price) {
    this.name = name;
    this.price = price;
    this.id = id;
  }
}

class Stock {
  Product product;
  int quantity;

  Stock(Product p, int quantity) {
    this.product = p;
    this.quantity = quantity;
  }
}

class Inventory {
  HashMap<Integer, Product> productList = new HashMap<>();
  HashMap<Integer, Integer> quantity = new HashMap<>();

  void addProduct(String input) {
    Stock stock = parseProduct(input);
    productList.put(stock.product.id, stock.product);
    quantity.put(stock.product.id, stock.quantity);
  }

  Stock parseProduct(String input) {
    String splitted[] = input.split("\\|");
    int id = Integer.parseInt(splitted[0]);
    String name = splitted[1];
    int quantity = Integer.parseInt(splitted[2]);
    double price = Double.parseDouble(splitted[3]);

    return new Stock(new Product(name, id, price), quantity);
  }
}

public class Main {
  static void chooseTask(String input, Inventory i) {
    String splitted[] = input.split("=>");
    String task = splitted[0];
    String details = splitted[1];

    if (task.equals("INVENTORY")) {
      i.addProduct(details);
      System.out.println("Inventory updated");
    } else if (task.equals("SALE")) {
      
    } else if (task.equals("STOCK")) {

    }
  }

  static boolean toQuit(String input) {
    if (input.equals("q")) return true;
    return false;
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    Inventory i = new Inventory();
    while (true) {
      System.out.println("Press 'q' to quit \nWaiting for command : ");
      String input = sc.nextLine();
      if (toQuit(input)) return;
      chooseTask(input, i);
    }
  }
}
