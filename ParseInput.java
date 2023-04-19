import java.util.*;

class ParseInput {
    static Stock parseCommandLineProduct(String details) {
        String splitted[] = details.split("\\|");
        int id = Integer.parseInt(splitted[0]);
        String name = splitted[1];
        int count = Integer.parseInt(splitted[2]);
        double price = Double.parseDouble(splitted[3]);

        return new Stock(new Product(name, id, price), count);
    }

    static ArrayList<Order> parseCommandLineSale(String input) {
        String products[] = input.split(";");
        ArrayList<Order> orders = new ArrayList<>();
        for (String eachProduct : products) {
            String eachSplitted[] = eachProduct.split("\\|");
            orders.add(
                new Order(
                Integer.parseInt(eachSplitted[0]),
                Integer.parseInt(eachSplitted[1])
                )
            );
        }
        return orders;
    }
}