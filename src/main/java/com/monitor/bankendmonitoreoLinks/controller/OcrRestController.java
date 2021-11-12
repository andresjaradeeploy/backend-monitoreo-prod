package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;
import com.monitor.bankendmonitoreoLinks.repository.IOcrRepository;



@RestController
@RequestMapping("/ocr")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200"})
public class OcrRestController {

	@Autowired
	private IOcrRepository iOcrRepository;

	@GetMapping("/{idPost}")
	public Ocr listarTagsByPost(@PathVariable String idPost) {
		return iOcrRepository.findOcrbyPost(idPost);
	}
}
