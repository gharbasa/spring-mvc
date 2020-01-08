package spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.model.Student;

@RestController
@RequestMapping("rest/student")
public class RestStudentController {

	private static Logger log = LoggerFactory.getLogger(RestStudentController.class);
	
	@RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
		log.debug("Default get api in student-rest");
        return "Welcome to Student RestTemplate Example.";
    }
	
    @RequestMapping(value="/all", method = RequestMethod.GET) //,, produces="application/json" 
    public @ResponseBody List<Student> listAll() {//REST Endpoint.
    	log.debug("list all student-rests");
    	List<Student> students = new ArrayList<Student>();
    	Student student1 = new Student();
    	student1.setAge(100);
    	student1.setId(1);
    	student1.setName("1Abed");
    	students.add(student1);
    	
    	Student student2 = new Student();
    	student2.setAge(200);
    	student2.setId(2);
    	student2.setName("2Abed");
    	students.add(student2);
        return students;
    }
}
