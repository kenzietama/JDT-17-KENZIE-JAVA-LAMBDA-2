package com.indivaragroup.lambda.exam.property.util;

import com.indivaragroup.lambda.exam.property.model.PropertyAsset;

import java.text.NumberFormat;
import java.util.Locale;

public class PropertyFormatter {
    private PropertyFormatter() {
        /* This utility class should not be instantiated */
        // ngikut sonarcube
    }

    public static void print(PropertyAsset p) {
        System.out.printf("[%s] %-28s | %-10s | %-15s | %18s | %3dm² | %s%n",
                p.getId(),
                p.getPropertyName(),
                p.getPropertyType(),
                p.getLocation(),
                formatRupiah(p.getPrice()),
                p.getLandArea(),
                p.isSold() ? "Terjual" : "Belum Terjual"
        );
    }

    public static String formatRupiah(double price) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp " + nf.format(price);
    }
}
