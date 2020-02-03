package webservice;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import ejb.MyCalculator;

@WebListener
public class lisener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PropertyConfigurator.configure("C:\\dev\\eclipse-projects\\apache-tomcat-8.5.50\\webapps\\config\\log.properties");
		new MyCalculator();
	}

}
