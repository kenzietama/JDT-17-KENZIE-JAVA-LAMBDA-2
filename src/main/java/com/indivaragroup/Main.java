package com.indivaragroup;

import com.indivaragroup.services.BekasiRealtyGroup;

public class Main {
    public static void main(String[] args) {
        BekasiRealtyGroup developer = new BekasiRealtyGroup();

        // NB: MAAF MAS TUGAS SEBELUMNYA YANG SEMPAK ITU SUDAH PAKAI STREAM KARENA PRAKTIS AJA ENAK

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
        System.out.println("Filter RUMAH saja:");
        developer.filterHouseOnly();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Filter lokasi Bekasi Selatan:");
        developer.filterLocationBekasiSelatan();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Filter harga dibawah 1M:");
        developer.filterPriceUnder1B();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Filter luas tanah > 100m^2 dan belum terjual:");
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

    }
}