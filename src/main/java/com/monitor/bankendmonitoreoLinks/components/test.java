package com.monitor.bankendmonitoreoLinks.components;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class test {
	public static void main(String[] args) {
		Double  bd = new Double(300000);

		NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US")); 
		System.out.println((bd.longValue()));

	}

}
