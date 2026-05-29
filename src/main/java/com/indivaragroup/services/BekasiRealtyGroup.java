package com.indivaragroup.services;

import com.indivaragroup.services.dto.PropertyAsset;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BekasiRealtyGroup {
    private List<PropertyAsset> properties = new ArrayList<>();

    public BekasiRealtyGroup() {
        init();
        System.out.println("TASK 1");
        display.accept(properties);
    }

    // TASK 1
    public Consumer<List<PropertyAsset>> display = l -> l.forEach(PropertyAsset::print);

    // TASK 2
    public void filterUnSold() {
        List<PropertyAsset> copy = new ArrayList<>(properties);
        copy.removeIf(p->p.isSold());
        copy.forEach(PropertyAsset::print);
    }

    // TASK 3
    public void sortCheapToExpensive() {
        properties.sort(Comparator.comparingDouble(PropertyAsset::getPrice));
        properties.forEach(PropertyAsset::print);
    }

    // TASK 4
    Predicate<PropertyAsset> houseOnly = p->p.getPropertyType().equalsIgnoreCase("rumah");
    Predicate<PropertyAsset> locationBekasiSelatan = p->p.getLocation().equalsIgnoreCase("bekasi selatan");
    Predicate<PropertyAsset> priceUnder1B = p->p.getPrice() < 1_000_000_000;
    Predicate<PropertyAsset> landAreaMoreThan100 = p->p.getLandArea() > 100;
    Predicate<PropertyAsset> available = p->!p.isSold();

    public void useFilter(Predicate<PropertyAsset> filter) {
        List<PropertyAsset> filtered = properties.stream()
                .filter(filter)
                .collect(toList());

        filtered.forEach(PropertyAsset::print);
    }

    public void filterHouseOnly() {
        useFilter(houseOnly);
    }

    public void filterLocationBekasiSelatan() {
        useFilter(locationBekasiSelatan);
    }

    public void filterPriceUnder1B() {
        useFilter(priceUnder1B);
    }

    public void filterCombo() {
        List<PropertyAsset> filtered = properties.stream()
                .filter(landAreaMoreThan100.and(available))
                .collect(toList());

        filtered.forEach(PropertyAsset::print);
    }

    // TASK 5
    public void transform() {
        properties.stream()
                .map(PropertyAsset::getPropertyName)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        properties.stream()
                .map(PropertyAsset::getPrice)
                .map(PropertyAsset::formatRupiah)
                .forEach(System.out::println);
    }

    // TASK 6
    public void calculateTotalAvailablePropertyPrice() {
        Double total = properties.stream()
                .filter(p->!p.isSold())
                .mapToDouble(PropertyAsset::getPrice)
                .sum();
        System.out.println("Total harga semua property yang belum terjual: " + PropertyAsset.formatRupiah(total));

        OptionalDouble avgHousePrice = properties.stream()
                .filter(p->p.getPropertyType().equalsIgnoreCase("rumah"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga RUMAH: " + PropertyAsset.formatRupiah(avgHousePrice.getAsDouble()));

        OptionalDouble avgRukoPrice = properties.stream()
                .filter(p->p.getPropertyType().equalsIgnoreCase("ruko"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga RUKO: " + PropertyAsset.formatRupiah(avgRukoPrice.getAsDouble()));

        OptionalDouble avgTanahPrice = properties.stream()
                .filter(p->p.getPropertyType().equalsIgnoreCase("tanah"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga TANAH: " + PropertyAsset.formatRupiah(avgTanahPrice.getAsDouble()));

        OptionalDouble avgApartPrice = properties.stream()
                .filter(p->p.getPropertyType().equalsIgnoreCase("apartemen"))
                .mapToDouble(PropertyAsset::getPrice)
                .average();
        System.out.println("Rata-rata harga APARTEMEN: " + PropertyAsset.formatRupiah(avgApartPrice.getAsDouble()));
    }

    // TASK 7
    public void groupingCollector() {
        Map<String, List<PropertyAsset>> groupType = properties.stream()
                .collect(Collectors.groupingBy(
                        PropertyAsset::getPropertyType));
        groupType.forEach((type, propertyList) -> {
            System.out.println("\n--- Tipe: " + type + " ---");
            propertyList.forEach(PropertyAsset::print);
        });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        Map<String, List<PropertyAsset>> groupLocation = properties.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation));
        groupLocation.forEach((location, propertyList) -> {
            System.out.println("\n--- Lokasi: " + location + " ---");
            propertyList.forEach(PropertyAsset::print);
        });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
        Map<String, List<PropertyAsset>> soldStatusGroups = properties.stream()
                .collect(Collectors.groupingBy(p -> p.isSold() ? "Sold" : "Not Sold"));
        soldStatusGroups.forEach((status, propertyList) -> {
            System.out.println("\n--- Status: " + status + " ---");
            propertyList.forEach(PropertyAsset::print);
        });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
    }

    private void init() {
        PropertyAsset p01 = new PropertyAsset("P01", "Rumah Cluster Emerald", "RUMAH", "Bekasi Timur", 1200000000.0, 120, 90, false, 2020);
        properties.add(p01);
        PropertyAsset p02 = new PropertyAsset("P02", "Apartemen Grand Bekasi", "APARTEMEN", "Bekasi Barat", 650000000.0, 0, 45, true, 2019);
        properties.add(p02);
        PropertyAsset p03 = new PropertyAsset("P03", "Ruko Jalan Ahmad Yani", "RUKO", "Bekasi Selatan", 2500000000.0, 80, 160, false, 2018);
        properties.add(p03);
        PropertyAsset p04 = new PropertyAsset("P04", "Tanah Kavling Jatibening", "TANAH", "Bekasi Timur", 800000000.0, 200, 0, false, 0);
        properties.add(p04);
        PropertyAsset p05 = new PropertyAsset("P05", "Rumah Minimalis Pekayon", "RUMAH", "Bekasi Selatan", 950000000.0, 90, 70, true, 2021);
        properties.add(p05);
        PropertyAsset p06 = new PropertyAsset("P06", "Apartemen Meikarta Tower B", "APARTEMEN", "Bekasi Barat", 450000000.0, 0, 36, false, 2022);
        properties.add(p06);
        PropertyAsset p07 = new PropertyAsset("P07", "Ruko Galaxy Bekasi", "RUKO", "Bekasi Utara", 3100000000.0, 100, 200, true, 2017);
        properties.add(p07);
        PropertyAsset p08 = new PropertyAsset("P08", "Rumah Mewah Kemang Pratama", "RUMAH", "Bekasi Selatan", 4500000000.0, 300, 250, false, 2023);
        properties.add(p08);
    }
}


