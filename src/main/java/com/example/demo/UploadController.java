package com.example.demo;

import static com.ot.akbp.rest.client.sample.client.util.Debug.print;
import static com.ot.akbp.rest.client.sample.client.util.Debug.printNewLine;
import static com.ot.akbp.rest.client.sample.client.util.Debug.printStep;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

import com.ot.akbp.commons.util.InputXaths;
import com.ot.akbp.commons.util.mapper.NodeToSysObjectMapper;
import com.ot.akbp.commons.util.mapper.Pair;
import com.ot.akbp.commons.util.rest.model.PlainRestObject;
import com.ot.akbp.commons.util.rest.model.RestObject;
import com.ot.akbp.commons.util.xml.DOMHelper;
import com.ot.d2rest.client.DCTMRestClient;
import com.ot.d2rest.client.DCTMRestClientBinding;
import com.ot.d2rest.client.DCTMRestClientBuilder;

@RestController
@RequestMapping("/upload")
public class UploadController {
	private static final Node NAMESPACENODE = GetNSDoc();

	@RequestMapping("/xmlFile")
	public ResponseEntity<InputStreamResource> uploadFileMulti(@RequestParam("xmlFile") MultipartFile uploadfile)
			throws Exception {

		System.out.println("UploadController.uploadFileMulti()");
		DOMHelper dom = new DOMHelper(uploadfile.getInputStream());
		dom.setNameSpaceNode(NAMESPACENODE);
		Map<String, Object> map = new HashMap();
		MAPPER.process(dom, map);
		printStep("create a folder under the Temp cabinet");
		map.put("object_name", "my_new_folder");
		DCTMRestClientBinding binding = DCTMRestClientBinding.JSON;

		DCTMRestClient client = DCTMRestClientBuilder.buildSilently(binding, "http://localhost:8080/d2rest", "corp",
				"dmadmin", "demo.demo");
		RestObject tempCabinet = client.getCabinet("Temp");
		RestObject newFolder = new PlainRestObject(map);

		RestObject createdFolder = client.createFolder(tempCabinet, newFolder);
		print(createdFolder);
		printNewLine();
		return ResponseEntity.ok().build();
	}

	public static final NodeToSysObjectMapper MAPPER = new NodeToSysObjectMapper(
			new SPair("title", InputXaths.DOCUMENTFOLDER_NUMBER), new SPair("subject", InputXaths.DOCUMENTFOLDER_TITLE),
			new SPair("authors", InputXaths.DOCUMENTFOLDER_COMMENTS),
			new SPair("keywords", InputXaths.DOCUMENTFOLDER_STATUS)
	/*
	 * new SPair(InputXmlElementNames.ACTIONBYID,
	 * InputXaths.DOCUMENTFOLDER_ACTIONBYID), new
	 * SPair(InputXmlElementNames.CREATEDDATE,
	 * InputXaths.DOCUMENTFOLDER_CREATEDDATE), new
	 * SPair(InputXmlElementNames.CREATEDBY, InputXaths.DOCUMENTFOLDER_CREATEDBY),
	 * new SPair(InputXmlElementNames.LOCATION, InputXaths.DOCUMENT_LOCATIONCODE),
	 * new SPair(InputXmlElementNames.DISCIPLINE,
	 * InputXaths.DOCUMENT_DISCIPLINECODE), new SPair(InputXmlElementNames.OWNER,
	 * InputXaths.DOCUMENTFOLDER_OWNER), new
	 * SPair(InputXmlElementNames.MODIFICATIONPROJECT,
	 * InputXaths.DOCUMENTFOLDER_CODE)
	 */);

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

	public static class SPair extends Pair<String, String> {

		public SPair(String first, String second) {
			super(first, second);
		}

	}
}
