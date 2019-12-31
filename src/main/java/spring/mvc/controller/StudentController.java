package spring.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mvc.event.CustomEventPublisher;
import spring.mvc.model.EventBean;
import spring.mvc.model.Student;

@Controller
public class StudentController {
   
	static Log log = LogFactory.getLog(StudentController.class.getName());
	
	@Autowired
	CustomEventPublisher customEventPublisher;
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView student() {
		log.debug("Serving blank Student view");
		return new ModelAndView("student", "command", new Student());
	}
   
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap model) {
		log.debug("Serving addStudent view");
		model.addAttribute("name", student.getName());
		model.addAttribute("age", student.getAge());
		model.addAttribute("id", student.getId());
		
		EventBean eventSource = new EventBean();
		eventSource.setEvetName("Testing Spring-mvc");
		customEventPublisher.publish(eventSource);
		
		return "result";
	}
}
