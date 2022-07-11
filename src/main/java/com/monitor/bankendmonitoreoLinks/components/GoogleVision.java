package com.monitor.bankendmonitoreoLinks.components;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;

import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleVision {
	public static void detectLabels() throws IOException {
		// TODO(developer): Replace these variables before running the sample.
		String filePath = "C:/Users/1012421355/Desktop/images/logoDeeploy.png";
		//detectLabels("C:/Users/1012421355/Desktop/images/213779898696022_6447924768614806.jpg");
		
	}

	public static void main(String[] args) {
		try {
			GoogleVision googleVision= new GoogleVision();
			List<String> tags= new ArrayList<>();
			//tags=detectLabels("C:/Users/1012421355/Desktop/images/213779898696022_6447924768614806.jpg");
			String ocr=googleVision.detectText("C:/Users/1012421355/Desktop/descarga.png");
			System.out.println("ocr"+ocr);
			
			/*for (String string : tags) {
				System.out.println("tag"+string);
			}*/
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	// Detects labels in the specified local image.
	public  List<String> detectLabels(String filePath) throws IOException {
		List<AnnotateImageRequest> requests = new ArrayList<>();
		List<String> tags= new ArrayList<>();
		
		tags.clear();
		
		ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);

		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					// System.out.format("Error: %s%n", res.getError().getMessage());
					return null;
				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					annotation.getAllFields().forEach((k, v) -> {
						//System.out.println("v" + v.toString());
						tags.add(v.toString());
						
					});

				}
			}
		}
		return tags;
	}

	

	// Detects text in the specified image.
	public String detectText(String filePath) throws IOException {
		List<AnnotateImageRequest> requests = new ArrayList<>();
		ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));
		String ocr=null;
		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);

		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					//System.out.format("Error: %s%n", res.getError().getMessage());
					
				}
				
				EntityAnnotation annotation=res.getTextAnnotations(0);
				ocr=annotation.getDescription().replaceAll("\\s"," ");
				
				System.out.println("Text: "+ annotation.getDescription().replaceAll("\\s"," "));
				
			}
		}
		return ocr;
	}
}
