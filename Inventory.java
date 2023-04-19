import java.util.*;

class Inventory {
  HashMap<Integer, Stock> productList = new HashMap<>();
  
  void addProduct(Stock stock) {
    productList.put(stock.product.id, stock);
  }

  int getQuantity(int productId) {
    return productList.get(productId).quantity;
  }

  int setQuantity(int productId, int required) {
    int available = productList.get(productId).quantity;
    int remaining = available - required > 0 ? available - required : 0;
    Stock s = productList.get(productId);
    s.quantity = remaining;
    productList.put(productId, s);
    return available - required > 0 ? required : available;
  }
}
