package com.indivaragroup;

import com.indivaragroup.lambda.exam.property.service.PropertyService;
import com.indivaragroup.lambda.exam.property.util.PropertyFormatter;

public class Main {
    public static void main(String[] args) {
        PropertyService developer = new PropertyService();

        // TASK 1
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 1");
        developer.displayAllProperties();

        // TASK 2
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 2");
        developer.filterUnSold();

        // TASK 3
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 3");
        developer.sortCheapToExpensive();

        // TASK 4
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 4");
        developer.filterHouseOnly();
        developer.filterLocationBekasiSelatan();
        developer.filterPriceUnder1B();
        developer.filterCombo();

        // TASK 5
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 5");
        developer.transform();

        // TASK 6
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 6");
        developer.calculateTotalAvailablePropertyPrice();

        //TASK 7
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 7");
        developer.groupingCollector();

        //TASK 8
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 8");

        //TASK 9
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 9");
        developer.chainSort();

        //TASK 10
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("TASK 10");
        developer.generateReport();

        //CHALLENGE 1
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("CHALLENGE 1");
        developer.searchTypeAndLocation.apply("RUMAH","Bekasi Selatan").forEach(PropertyFormatter::print);

        // CHALLENGE 2
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("CHALLENGE 2");
        developer.getStatistics();

        // CHALLENGE 3
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("CHALLENGE 3");
        developer.searchById("P01").ifPresentOrElse(PropertyFormatter::print, () -> System.out.println("Property tidak ditemukan"));
    }
}