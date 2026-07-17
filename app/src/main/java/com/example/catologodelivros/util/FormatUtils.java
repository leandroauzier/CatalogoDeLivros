package com.example.catologodelivros.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class FormatUtils {

    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    private FormatUtils() {
    }

    public static String formatCurrency(double value) {
        return NumberFormat.getCurrencyInstance(LOCALE_PT_BR).format(value);
    }

    public static String formatDateTime(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm", LOCALE_PT_BR).format(date);
    }
}
