package spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;

import spring.mvc.event.CustomEventPublisher;
import spring.mvc.model.EventBean;
import spring.mvc.model.Student;
import spring.mvc.service.IAccountTokenService;

@Controller
@RequestMapping("web/student")
public class StudentController {
   
	private static Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	CustomEventPublisher customEventPublisher;
	
	@Autowired
	IAccountTokenService accountTokenService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView student() {
		log.debug("Serving blank Student view");
		return new ModelAndView("student", "command", new Student());
	}
   
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute("student")Student student, ModelMap model
			, HttpServletRequest request) {
		log.debug("Serving addStudent view");
		
		Captcha captcha = Captcha.load(request, "exampleCaptcha");
	    boolean isHuman = captcha.validate(student.getCaptchaCode());
	    
	    log.debug("Serving addStudent view isHuman=" + isHuman);
	    
		model.addAttribute("name", student.getName());
		model.addAttribute("age", student.getAge());
		model.addAttribute("id", student.getId());
		
		EventBean eventSource = new EventBean();
		eventSource.setEvetName("Testing Spring-mvc");
		customEventPublisher.publish(eventSource);
		
		log.debug("Call from the serivce API..." + accountTokenService.generateToken(student.getName()));
		student.setHuman(isHuman);
		
		if(!isHuman)
			return new ModelAndView("student", "command", student);
		
		//return "result";
		return new ModelAndView("result", "command", model);
	}
}
