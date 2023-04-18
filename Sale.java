import java.util.*;

class Sale {
  HashMap<Integer, Offers> offers = new HashMap<>();
  Inventory invObj;

    Sale (Inventory i) {
        invObj = i;
        offers.put(1, new Offers(1, 10));
        offers.put(2, new Offers(2, 5));
    }

  void sell(ArrayList<Sell> productsToSell) {
    double total = 0;
    System.out.println("\n== Bill ==".toUpperCase());

    for (Sell p : productsToSell) {
      if (!checkAvailability(p)) 
        printAlert(p.productId);
      
      int soldQuantity = invObj.setQuantity(p.productId, p.requiredQuantity);
      double actualPrice = invObj.productList.get(p.productId).price;
      double discountPrice = getDiscountPrice(p.productId, actualPrice);
      double net = soldQuantity * discountPrice;
      total += net;

      printProductBillDetails(p, soldQuantity, discountPrice, net);
    }
    System.out.println("== Total ==\n".toUpperCase() + total + "\n========\n");
  }

   void printProductBillDetails (Sell p, int soldQuantity, double discountPrice, double net) {
      System.out.println(p.productId + " - " + invObj.productList.get(p.productId).name + " - " + soldQuantity + " - " + discountPrice + " - " + offers.getOrDefault(p.productId, new Offers(-1, -1)).offerId + " - " + net);
   }

  void printAlert(int productId) {
    System.out.println(invObj.productList.get(productId).name + " Quantity is less in inventory");
  }

  double getDiscountPrice(int productId, double actualPrice) {
    return actualPrice - (actualPrice / offers.get(productId).offerPercentage);
  }

  boolean checkAvailability(Sell p) {
    return p.requiredQuantity > invObj.getQuantity(p.productId) ? false : true;
  }
}