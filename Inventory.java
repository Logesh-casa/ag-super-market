import java.util.*;

class Inventory {
  HashMap<Integer, Product> productList = new HashMap<>();
  HashMap<Integer, Integer> quantity = new HashMap<>();
  
  void addProduct(Stock stock) {
    productList.put(stock.product.id, stock.product);
    quantity.put(stock.product.id, stock.quantity);
  }

  int getQuantity(int productId) {
    return quantity.getOrDefault(productId, -1);
  }

  int setQuantity(int productId, int required) {
    int available = quantity.get(productId);
    quantity.put(productId, available - required > 0 ? available - required : 0);
    return available - required > 0 ? required : available;
  }
}