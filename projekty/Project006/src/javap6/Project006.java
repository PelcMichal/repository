package javap6;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Project006 {

	private static final Logger LOGGER = Logger.getLogger(DataBean.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("C:\\dev\\eclipse-projects\\Project006\\log\\properties\\log.properties");
		
		LOGGER.debug("*** BIGIN ***");
		
		DataBeanHelper dataBeanHelper;
		dataBeanHelper = new DataBeanHelper();
		dataBeanHelper.loadDataBean();
		
		DataBean dataBean;
		dataBean = dataBeanHelper.getDataBean();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Name: ");
		stringBuilder.append(dataBean.getName());
		stringBuilder.append(", Surrname: ");
		stringBuilder.append(dataBean.getSurrname());
		stringBuilder.append(", Phone: ");
		stringBuilder.append(dataBean.getPhonenumber());
		
		String outputString = stringBuilder.toString();
		
		System.out.println(outputString);
		
		LOGGER.debug("*** END ***");
		
	}

}
