import Entity.Pricing;
import Entity.Security;
import Entity.Ticker;
import Service.PricingService;
import Service.SecurityService;
import Service.TickerService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ticker> tickers = Arrays.asList(
                new Ticker("1", "AAPL", "NASDAQ", "Apple Inc."),
                new Ticker("2", "GOOGL", "NASDAQ", "Alphabet Inc."),
                new Ticker("3", "TSLA", "NYSE", "Tesla Inc.")
        );

        List<Pricing> pricings = Arrays.asList(
                new Pricing("1", "1", LocalDateTime.now(), 150, 155, 152),
                new Pricing("2", "2", LocalDateTime.now(), 2800, 2850, 2830),
                new Pricing("3", "3", LocalDateTime.now(), 750, 760, 755)
        );

        List<Security> securities = Arrays.asList(
                new Security("1", "Stock"),
                new Security("2", "Bond"),
                new Security("3", "Commodity")
        );

        TickerService tickerService = new TickerService();
        System.out.println("Grouped by Exchange: " + tickerService.groupByExchange(tickers));
        System.out.println("Collected to Map: " + tickerService.collectToMap(tickers));
        System.out.println("Joined Symbols: " + tickerService.joinTickerSymbols(tickers));

        PricingService pricingService = new PricingService();
        System.out.println("Average Close Price: " + pricingService.calculateAverageClosePrice(pricings));
        System.out.println("Partitioned by Price: " + pricingService.partitionByPrice(pricings, 1000));
        System.out.println("Highest Price: " + pricingService.findHighestPrice(pricings));

        SecurityService securityService = new SecurityService();
        System.out.println("Filtered Securities: " + securityService.filterByType(securities, "Stock"));
    }
}