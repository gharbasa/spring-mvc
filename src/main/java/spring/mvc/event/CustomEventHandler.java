package spring.mvc.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class CustomEventHandler implements ApplicationListener<CustomEvent> {
	
	private static Logger log = LoggerFactory.getLogger(CustomEventHandler.class);
	
	public void onApplicationEvent(CustomEvent event) {
     	log.info("CustomEvent event handler..." + event.getSource().getEvetName());
   }
}
