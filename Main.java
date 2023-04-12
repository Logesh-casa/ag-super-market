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

class Offers {
  int offerId;
  int offerPercentage;

  Offers(int offerId, int offerPercentage) {
    this.offerId = offerId;
    this.offerPercentage = offerPercentage;
  }
}

class EachSell {
  int productId;
  int requiredQuantity;

  EachSell(int productId, int requiredQuantity) {
    this.productId = productId;
    this.requiredQuantity = requiredQuantity;
  }
}

class Sale {
  HashMap<Integer, Offers> offers = new HashMap<>();
  Inventory invObj;

  void sell(String input, Inventory i) {
    invObj = i;
    ArrayList<EachSell> productsToSell = sellParser(input);
    double total = 0;
    System.out.println("== Bill ==");

    for (EachSell p : productsToSell) {
      if (!checkAvailability(p)) {
        printAlert(p.productId);
      }

      int soldQuantity = invObj.setQuantity(p.productId, p.requiredQuantity);
      double actualPrice = invObj.productList.get(p.productId).price;
      double discountPrice = getDiscountPrice(p.productId, actualPrice);
      double net = soldQuantity * discountPrice;
      total += net;

      System.out.println(
        p.productId +
        " - " +
        invObj.productList.get(p.productId).name +
        " - " +
        soldQuantity +
        " - " +
        discountPrice +
        " - " +
        offers.getOrDefault(p.productId, new Offers(-1, -1)).offerId +
        " - " +
        net
      );
    }
    System.out.println("== Total ==\n" + total + "\n========");
  }

  void printAlert(int productId) {
    System.out.println(
      invObj.productList.get(productId).name + " Quantity is less in inventory"
    );
  }

  double getDiscountPrice(int productId, double actualPrice) {
    offers.put(1, new Offers(1, 10));
    return actualPrice - (actualPrice / offers.get(productId).offerPercentage);
  }

  boolean checkAvailability(EachSell p) {
    return p.requiredQuantity > invObj.getQuantity(p.productId) ? false : true;
  }

  ArrayList<EachSell> sellParser(String input) {
    String products[] = input.split(";");
    ArrayList<EachSell> productsToSell = new ArrayList<>();
    for (String eachProduct : products) {
      String eachSplitted[] = eachProduct.split("\\|");
      productsToSell.add(
        new EachSell(
          Integer.parseInt(eachSplitted[0]),
          Integer.parseInt(eachSplitted[1])
        )
      );
    }
    return productsToSell;
  }
}

class Inventory {
  HashMap<Integer, Product> productList = new HashMap<>();
  HashMap<Integer, Integer> quantity = new HashMap<>();

  void addProduct(String details) {
    Stock stock = parseProduct(details);
    productList.put(stock.product.id, stock.product);
    quantity.put(stock.product.id, stock.quantity);
  }

  Stock parseProduct(String details) {
    String splitted[] = details.split("\\|");
    int id = Integer.parseInt(splitted[0]);
    String name = splitted[1];
    int count = Integer.parseInt(splitted[2]);
    double price = Double.parseDouble(splitted[3]);

    return new Stock(new Product(name, id, price), count);
  }

  int getQuantity(int productId) {
    return quantity.getOrDefault(productId, -1);
  }

  int setQuantity(int productId, int required) {
    int available = quantity.get(productId);
    quantity.put(
      productId,
      available - required > 0 ? available - required : 0
    );
    return available - required > 0 ? required : available;
  }
}

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
      System.out.println(invObj.productList.get(id).name + " - " + available);
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
