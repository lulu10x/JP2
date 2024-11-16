package Service;
import Entity.Ticker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class TickerService {

        public Map<String, List<Ticker>> groupByExchange(List<Ticker> tickers) {
            return tickers.stream()
                    .collect(Collectors.groupingBy(Ticker::getExchange));
        }

        public Map<String, Ticker> collectToMap(List<Ticker> tickers) {
            return tickers.stream()
                    .collect(Collectors.toMap(Ticker::getSymbol, ticker -> ticker));
        }


        public String joinTickerSymbols(List<Ticker> tickers) {
            return tickers.stream()
                    .map(Ticker::getSymbol)
                    .collect(Collectors.joining(", "));
        }
    }

