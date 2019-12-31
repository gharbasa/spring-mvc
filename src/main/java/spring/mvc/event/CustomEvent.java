package spring.mvc.event;

import org.springframework.context.ApplicationEvent;

import spring.mvc.model.EventBean;

public class CustomEvent extends ApplicationEvent {
	
	EventBean eventSource = null;
	
	public CustomEvent(EventBean source) {
      super(source);
      eventSource = source;
   }
	
   public EventBean getSource(){
      
	   return eventSource;
   }
}
