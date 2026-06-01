package com.indivaragroup.lambda.exam.property.service;

import com.indivaragroup.lambda.exam.property.data.PropertyDataProvider;
import com.indivaragroup.lambda.exam.property.model.PropertyAsset;
import com.indivaragroup.lambda.exam.property.util.PropertyFormatter;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PropertyService {
    private List<PropertyAsset> properties;
    public Map<String, DoubleSummaryStatistics> statistics;

    public PropertyService() {
        properties = PropertyDataProvider.propertySupplier.get();

        // CHALLENGE 2
        statistics = properties.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getPropertyType, Collectors.summarizingDouble(PropertyAsset::getPrice)));
    }

    // TASK 1
    public void displayAllProperties() {
        properties.forEach(PropertyFormatter::print);
    }

    // TASK 2
    public void filterUnSold() {
        List<PropertyAsset> copy = new ArrayList<>(properties);
        copy.removeIf(PropertyAsset::isSold);
        copy.forEach(PropertyFormatter::print);
    }

    // TASK 3
    public void sortCheapToExpensive() {
        properties.sort(Comparator.comparingDouble(PropertyAsset::getPrice));
        properties.forEach(PropertyFormatter::print);
    }

    // TASK 4
    Predicate<PropertyAsset> houseOnly = p -> p.getPropertyType().equalsIgnoreCase("rumah");
    Predicate<PropertyAsset> locationBekasiSelatan = p -> p.getLocation().equalsIgnoreCase("bekasi selatan");
    Predicate<PropertyAsset> priceUnder1B = p -> p.getPrice() < 1_000_000_000;
    Predicate<PropertyAsset> landAreaMoreThan100 = p -> p.getLandArea() > 100;
    Predicate<PropertyAsset> available = p -> !p.isSold();

    private void useFilter(Predicate<PropertyAsset> filter) {
        properties.stream()
                .filter(filter)
                .forEach(PropertyFormatter::print);
    }

    public void filterHouseOnly() {
        System.out.println("Filter RUMAH saja:");
        useFilter(houseOnly);
    }

    public void filterLocationBekasiSelatan() {
        System.out.println("Filter lokasi Bekasi Selatan:");
        useFilter(locationBekasiSelatan);
    }

    public void filterPriceUnder1B() {
        System.out.println("Filter harga dibawah 1M:");
        useFilter(priceUnder1B);
    }

    public void filterCombo() {
        System.out.println("Filter luas tanah > 100m^2 dan belum terjual:");
        properties.stream()
                .filter(landAreaMoreThan100.and(available))
                .forEach(PropertyFormatter::print);
        System.out.println();
    }

    // TASK 5
    public void transform() {
        properties.stream()
                .map(PropertyAsset::getPropertyName)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        properties.stream()
                .map(PropertyAsset::getPrice)
                .map(PropertyFormatter::formatRupiah)
                .forEach(System.out::println);
    }

    // TASK 6
    public void calculateTotalAvailablePropertyPrice() {
        Double total = properties.stream()
                .filter(p -> !p.isSold())
                .mapToDouble(PropertyAsset::getPrice)
                .sum();
        System.out.println("Total harga semua property yang belum terjual: " + PropertyFormatter.formatRupiah(total));

        OptionalDouble avgHousePrice = properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase("rumah"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga RUMAH: " + PropertyFormatter.formatRupiah(avgHousePrice.getAsDouble()));

        OptionalDouble avgRukoPrice = properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase("ruko"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga RUKO: " + PropertyFormatter.formatRupiah(avgRukoPrice.getAsDouble()));

        OptionalDouble avgTanahPrice = properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase("tanah"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga TANAH: " + PropertyFormatter.formatRupiah(avgTanahPrice.getAsDouble()));

        OptionalDouble avgApartPrice = properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase("apartemen"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga APARTEMEN: " + PropertyFormatter.formatRupiah(avgApartPrice.getAsDouble()));
    }

    // TASK 7
    public void groupingCollector() {
        properties.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getPropertyType))
                .forEach((type, propertyList) -> {
                    System.out.println("\n--- Tipe: " + type + " ---");
                    propertyList.forEach(PropertyFormatter::print);
                });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        properties.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation))
                .forEach((location, propertyList) -> {
                    System.out.println("\n--- Lokasi: " + location + " ---");
                    propertyList.forEach(PropertyFormatter::print);
                });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        properties.stream()
                .collect(Collectors.groupingBy(p -> p.isSold() ? "Sold" : "Available"))
                .forEach((status, propertyList) -> {
                    System.out.println("\n--- Status: " + status + " ---");
                    propertyList.forEach(PropertyFormatter::print);
                });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
    }

    // TASK 8
    Supplier<PropertyAsset> propertySupplier = PropertyAsset::new;

    public void createNewProperty(Supplier<PropertyAsset> supplier) {
        PropertyAsset property = supplier.get();
        properties.add(property);
        System.out.println("Properti baru ditambahkan");
    }

    Function<String, PropertyAsset> createPropertyWithID = PropertyAsset::new;

    // TASK 9
    public void chainSort() {
        properties.sort(
                Comparator
                        .comparing(PropertyAsset::getLocation)
                        .thenComparing(PropertyAsset::getPrice)
                        .reversed());
        properties.forEach(PropertyFormatter::print);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        properties.sort(
                Comparator
                        .comparing(PropertyAsset::getPropertyType)
                        .thenComparing(PropertyAsset::getBuildYear, Comparator.reverseOrder()));
        properties.forEach(PropertyFormatter::print);
    }

    // TASK 10
    public void generateReport() {
        StringBuilder builder = new StringBuilder();
        int totalProperties = properties.size();
        int soldProperties = (int) properties.stream().filter(PropertyAsset::isSold).count();
        int availableProperties = (int) properties.stream().filter(p -> !p.isSold()).count();
        List<PropertyAsset> list = properties.stream()
                .filter(p -> !p.isSold())
                .sorted(Comparator.comparing(PropertyAsset::getPrice))
                .toList();
        Double total = list.stream().mapToDouble(PropertyAsset::getPrice).sum();
        int i = 1;
        builder
                .append("======================================================================================\n")
                .append("LAPORAN ASSETS PROPERTY BEKASI REALTY GROUP\n")
                .append("======================================================================================\n")
                .append("\n")
                .append("Total Property\t\t: " + totalProperties + "\n")
                .append("Property Terjual\t: " + soldProperties + "\n")
                .append("Property Tersedia\t: " + availableProperties + "\n")
                .append("\n")
                .append("--- PROPERTY TERSEDIA (Sorted by Harga) ---\n");
        for (PropertyAsset property : list) {
            builder.append(String.format("%d. [%s] %-30s | %s \n", i, property.getId(), property.getPropertyName(), PropertyFormatter.formatRupiah(property.getPrice())));
            i++;
        }
        builder
                .append("\n")
                .append("--- TOTAL NILAI ASSETS TERSEDIA ---\n")
                .append("Total: " + PropertyFormatter.formatRupiah(total) + "\n")
                .append("\n")
                .append("--- DISTRIBUSI PER LOKASI ---\n");
        properties.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation, Collectors.counting()))
                .forEach((location, count) ->
                        builder.append((String.format("%-15s : %d property%n", location, count))));
        builder.append("======================================================================================\n");
        System.out.println(builder);
    }

    // CHALLENGE 1
    public BiFunction<String, String, List<PropertyAsset>> searchTypeAndLocation = (type, location) ->
            properties
                    .stream()
                    .filter(p->p.getPropertyType().equalsIgnoreCase(type) && p.getLocation().equalsIgnoreCase(location))
                    .toList();

    // CHALLENGE 2
    public void getStatistics() {
        statistics.forEach((type, stats) -> {
            System.out.println("--- Statistik Tipe: " + type + " ---");
            System.out.println("Jumlah      : " + stats.getCount());
            System.out.println("Harga Min   : " + PropertyFormatter.formatRupiah(stats.getMin()));
            System.out.println("Harga Max   : " + PropertyFormatter.formatRupiah(stats.getMax()));
            System.out.println("Rata-rata   : " + PropertyFormatter.formatRupiah(stats.getAverage()));
        });
    }

    // CHALLENGE 3
    public Optional<PropertyAsset> searchById(String id) {
        return properties.stream()
                .filter(p->p.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
