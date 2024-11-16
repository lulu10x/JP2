package Service;

import Entity.Pricing;

import java.util.*;
import java.util.stream.Collectors;

public class PricingService {

    public double calculateAverageClosePrice(List<Pricing> pricings) {
        return pricings.stream()
                .collect(Collectors.averagingDouble(Pricing::getClosePrice));
    }


    public Map<Boolean, List<Pricing>> partitionByPrice(List<Pricing> pricings, double threshold) {
        return pricings.stream()
                .collect(Collectors.partitioningBy(p -> p.getClosePrice() > threshold));
    }


    public Optional<Pricing> findHighestPrice(List<Pricing> pricings) {
        return pricings.stream()
                .max(Comparator.comparingDouble(Pricing::getClosePrice));
    }


    public Map<String, Map<String, List<Pricing>>> multiLevelGrouping(List<Pricing> pricings) {
        return pricings.stream()
                .collect(Collectors.groupingBy(Pricing::getTickerId,
                        Collectors.groupingBy(p -> p.getDateTime().toLocalDate().toString())));
    }
}