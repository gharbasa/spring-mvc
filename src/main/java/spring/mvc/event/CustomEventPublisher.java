package spring.mvc.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import spring.mvc.model.EventBean;

public class CustomEventPublisher implements ApplicationEventPublisherAware {
   
	//@Autowired
	private ApplicationEventPublisher publisher;
	
	public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	public void publish(EventBean eventSource) {
		CustomEvent ce = new CustomEvent(eventSource);
		publisher.publishEvent(ce);
	}
}
