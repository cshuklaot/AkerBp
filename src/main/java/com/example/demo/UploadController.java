package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/upload")
public class UploadController {
	private static final File UPLOAD_FOLDER_CSV = new File("C:\\InfoArchiveUploadedStuff");
	private File file;
	private MultipartFile uploadfile;
	private static final File UPLOADED_FOLDER_FILE = new File(UPLOAD_FOLDER_CSV, "files");

	@RequestMapping("/store")
	public ResponseEntity<InputStreamResource> uploadFileMulti( @RequestParam("xmlFile") MultipartFile uploadfile)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println("UploadController.uploadFileMulti()");
		//XML parsing.
		return ResponseEntity.ok().build();

	}
	
	private void saveUploadedFile(MultipartFile multiPfile) throws IOException {
		byte[] bytes = multiPfile.getBytes();
		this.file = getUniqueFile(multiPfile);
		Path path = this.file.toPath();
		Files.write(path, bytes);
	}

	private File getUniqueFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String basename = FilenameUtils.getBaseName(originalFilename);
		String extn = FilenameUtils.getExtension(originalFilename);

		File tFile = new File(UPLOADED_FOLDER_FILE, originalFilename);
		if (tFile.exists()) {
			int i = 1;
			while (true) {
				tFile = new File(UPLOADED_FOLDER_FILE, basename + "(" + i + ")." + extn);
				if (!tFile.exists()) {
					break;
				}
				i++;
			}
		}
		return tFile;
	}


}
