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

    static ArrayList<Sell> parseCommandLineSale(String input) {
        String products[] = input.split(";");
        ArrayList<Sell> productsToSell = new ArrayList<>();
        for (String eachProduct : products) {
            String eachSplitted[] = eachProduct.split("\\|");
            productsToSell.add(
                new Sell(
                Integer.parseInt(eachSplitted[0]),
                Integer.parseInt(eachSplitted[1])
                )
            );
        }
        return productsToSell;
    }
}