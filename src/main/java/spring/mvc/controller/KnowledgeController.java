package spring.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.dao.KnowledgeDao;
import spring.mvc.entity.Knowledge;

@RestController
@RequestMapping("rest/kd")
public class KnowledgeController {

	private static Logger log = LoggerFactory.getLogger(KnowledgeController.class);
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
		log.debug("Welcome to KD.");
        return "Welcome to KD.";
    }
	
    @RequestMapping(value="/search/{searchString}", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody List<Knowledge> listAll(@PathVariable("searchString") String searchString) {//REST Endpoint.
    	log.debug("searchString ---> {}", searchString);
    	List<Knowledge> list = knowledgeDao.ftsSearchAll(searchString);
    	log.debug("list size ---> {}", list.size());
    	return list;
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody Knowledge getDocument(@PathVariable("id") Long id) {//REST Endpoint.
    	log.debug("getDocument ---> {}", id);
    	Knowledge knowledge = knowledgeDao.getById(id);
    	return knowledge;
    }
    
    @RequestMapping(value="/", method = RequestMethod.POST) //,, produces="application/json" 
    public @ResponseBody Knowledge save(@RequestBody Knowledge knowledge) {//REST Endpoint.
    	log.debug("save knowledge ---> {}", knowledge.getId());
    	knowledgeDao.save(knowledge);
    	log.debug("saved knowledge ---> {}", knowledge.getId());
    	return knowledge;
    }
}
