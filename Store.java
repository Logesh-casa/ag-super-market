import java.util.*;

class Store { 
   Inventory invObj;

    Store () {
      invObj = new Inventory();
    }

  void sell(ArrayList<Order> orders) {
    double total = 0;
    System.out.println("\n== Bill ==".toUpperCase());

    for (Order p : orders) {
      if (!checkAvailability(p)) 
        printAlert(p.productId);
      
      int soldQuantity = invObj.setQuantity(p.productId, p.requiredQuantity);
      double actualPrice = invObj.productList.get(p.productId).product.price;
      double net = soldQuantity * actualPrice;
      total += net;

      printProductBillDetails(p, soldQuantity, actualPrice, net);
    }
    System.out.println("== Total ==\n".toUpperCase() + total + "\n========\n");
  }

   void printProductBillDetails (Order p, int soldQuantity, double actualPrice, double net) {
      System.out.println(p.productId + " - " + invObj.productList.get(p.productId).product.name + " - " + soldQuantity + " - " + actualPrice + " - " + net);
   }

  void printAlert(int productId) {
    System.out.println(invObj.productList.get(productId).product.name + " Quantity is less in inventory");
  }

  boolean checkAvailability(Order p) {
    return p.requiredQuantity > invObj.getQuantity(p.productId) ? false : true;
  }

  String getName (int id) {
   return invObj.productList.get(id).product.name;
  }

  void addProduct (Stock stock) {
    invObj.addProduct(stock);
  }

  int getQuantity(int id) {
    return invObj.getQuantity(id);
  }
}
