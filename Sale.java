import java.util.*;

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