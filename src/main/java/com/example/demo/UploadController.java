package com.example.demo;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ot.akbp.commons.util.InputXaths;
import com.ot.akbp.commons.util.xml.DOMHelper;

@RestController
@RequestMapping("/upload")
public class UploadController {
	private static final File UPLOAD_FOLDER_CSV = new File("C:\\InfoArchiveUploadedStuff");
	private static final Node NAMESPACENODE = GetNSDoc();

	@RequestMapping("/store")
	public ResponseEntity<InputStreamResource> uploadFileMulti(@RequestParam("xmlFile") MultipartFile uploadfile)
			throws JsonParseException, JsonMappingException, IOException, ParserConfigurationException, SAXException,
			TransformerException {

		System.out.println("UploadController.uploadFileMulti()");
		DOMHelper dom = new DOMHelper(uploadfile.getInputStream());
		dom.setNameSpaceNode(NAMESPACENODE);
		System.out.println(InputXaths.LISTOFDOCUMENTFOLDERS_VERSION + "--------"
				+ dom.selectText(InputXaths.LISTOFDOCUMENTFOLDERS_VERSION));
		


		return ResponseEntity.ok().build();

	}

	static Node GetNSDoc() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		// set up a document purely to hold the namespace mappings
		DOMImplementation impl = builder.getDOMImplementation();
		Document namespaceHolder = impl.createDocument("urn:workmateeam.com/wmbdex/draft1.0.0",
				"wmbdex:listOfDocumentFolders", null);
		Element root = namespaceHolder.getDocumentElement();
		root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:wmbdex", "urn:workmateeam.com/wmbdex/draft1.0.0");
		return root;

	}
}
