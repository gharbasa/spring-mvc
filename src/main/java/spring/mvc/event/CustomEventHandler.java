package spring.mvc.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;

public class CustomEventHandler implements ApplicationListener<CustomEvent> {
	
	static Log log = LogFactory.getLog(CustomEventHandler.class.getName());
	
	public void onApplicationEvent(CustomEvent event) {
     	log.info("CustomEvent event handler..." + event.getSource().getEvetName());
   }
}
