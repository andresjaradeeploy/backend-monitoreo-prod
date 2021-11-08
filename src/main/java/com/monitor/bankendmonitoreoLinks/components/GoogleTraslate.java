package com.monitor.bankendmonitoreoLinks.components;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;



public class GoogleTraslate {
	
	 public static void main(String[] arguments) throws IOException, GeneralSecurityException
	    {
	        Translate t = new Translate.Builder(
	                GoogleNetHttpTransport.newTrustedTransport()
	                , GsonFactory.getDefaultInstance(), null)
	                // Set your application name
	                .setApplicationName("Stackoverflow-Example")
	                .build();
	        Translate.Translations.List list = t.new Translations().list(
	                Arrays.asList(
	                        // Pass in list of strings to be translated
	                        "Hello World",
	                        "How to use Google Translate from Java"),
	                // Target language
	                "ES");

	        // TODO: Set your API-Key from https://console.developers.google.com/
	        list.setKey("5a4335713d9be76dca83c48c3c9e20ad064c462a");
	        TranslationsListResponse response = list.execute();
	        for (TranslationsResource translationsResource : response.getTranslations())
	        {
	            System.out.println(translationsResource.getTranslatedText());
	        }
	    }

}
