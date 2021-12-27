package com.monitor.bankendmonitoreoLinks.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;
import com.monitor.bankendmonitoreoLinks.repository.IOcrRepository;
import com.monitor.bankendmonitoreoLinks.service.IOcrService;

@Service
public class OcrServiceImp implements IOcrService {
	
	@Autowired
	private IOcrRepository ocrRepository;
	

	@Override
	public Ocr findOcrbypost(String idPost) {
		return ocrRepository.findOcrbyPost(idPost);
	}


	@Override
	public Ocr save(Ocr ocr) {
		return ocrRepository.save(ocr);
	}
	
	

}
