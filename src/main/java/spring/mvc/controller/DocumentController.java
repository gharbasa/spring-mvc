package spring.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.entity.BaseDocument;
import spring.mvc.service.DocumentService;

@RestController
@RequestMapping("rest/document")
public class DocumentController {

	private static Logger log = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
		log.debug("Welcome to Document RestTemplate Example.");
        return "Welcome to Document RestTemplate Example.";
    }
	
    @RequestMapping(value="/all/{searchString}", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody List<BaseDocument> listAll(@PathVariable("searchString") String searchString) {//REST Endpoint.
    	log.debug("searchString ---> {}", searchString);
    	List<BaseDocument> list = documentService.searchAll(searchString);
    	log.debug("list size ---> {}", list.size());
    	return list;
    }
    
    @RequestMapping(value="/n-all/{searchString}", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody List<BaseDocument> listNativeAll(@PathVariable("searchString") String searchString) {//REST Endpoint.
    	log.debug("searchString native sql call ---> {}", searchString);
    	List<BaseDocument> list = documentService.searchNativeAll(searchString);
    	log.debug("native sql call list size ---> {}", list.size());
    	return list;
    }
    
    @RequestMapping(value="/fts-all/{searchString}", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody List<BaseDocument> ftsSearchAll(@PathVariable("searchString") String searchString) {//REST Endpoint.
    	log.debug("searchString native sql call ---> {}", searchString);
    	List<BaseDocument> list = documentService.searchNativeAll(searchString);
    	log.debug("native sql call list size ---> {}", list.size());
    	return list;
    }
    
}
