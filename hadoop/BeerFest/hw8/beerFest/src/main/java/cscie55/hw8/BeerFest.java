package cscie55.hw8;

/* Adapted from code in "Java Programming", Chapter 20
   by Yakov Fain
 */
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public class BeerFest {
    public  static class Beer {
        public final String name;
        public final String country;
        private float price;

        public Beer(String name, String country,float price){
            this.name=name;
            this.country=country;
            this.price=price;
        }

        public String toString(){
            return "Country: " + country +  " Name: " + name + ", price: " + price;
        }
        public float getPrice() {
            return price;
        }
        public void setPrice(float price) {
            this.price = price;
        }
    }
    public static List<Beer> beerQuery(List<Beer> beerList, Predicate <Beer> predicate) {
//        List<Beer> result = beerList.stream()
//                // ToDo Select Beers that match the predicate
//                .filter(beer -> predicate.test(beer).collect(Collectors.toList()));

        List<Beer> result = new ArrayList<Beer>();
        for(Beer beer: beerList){
            if(predicate.test(beer)) {
                result.add(beer);
            }
        }
              return result;
    }

    static List<Beer> loadCellar(){
        List<Beer> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

        return beerStock;
    }
    static Predicate<Beer> priceRangeQuery( float lower, float upper) {
        // ToDo: compose and return a Predicate that will express the selection criterion
        Predicate<Beer> beerPredicate = beer -> beer.getPrice() >= lower && beer.getPrice() <= upper;
        return beerPredicate;

    }
    static Predicate<Beer> countryQuery(String country) {
        // ToDo: compose and return a Predicate that will express the selection criterion
        Predicate<Beer> beerPredicate = beer -> beer.country.equals(country);
        return beerPredicate;
    }


    public static void main(String argv[]) {
        List<Beer> beerList = loadCellar();
        // Call beerQuery with a predicate for selecting a country
        beerQuery(beerList, countryQuery("Belgium")).forEach(System.out::println);
        // Call beerQuery with a predicate for a price range
        beerQuery(beerList, priceRangeQuery(5.00f, 8.25f)).forEach(System.out::println);
    }
}
