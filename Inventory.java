import java.util.*;

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