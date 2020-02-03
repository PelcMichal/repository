package javap6;

import org.apache.log4j.Logger;

public class DataBeanHelper {
	private static final Logger LOGGER = Logger.getLogger(DataBeanHelper.class);
	public DataBeanHelper()
	{
		LOGGER.debug("bigin");
	}
	private DataBean dataBean;
	
	public void loadDataBean()
	{
		LOGGER.debug("bigin");
		
		dataBean = new DataBean("Karel", "Novak", "123 456 789");
	}

	public DataBean getDataBean() {
		LOGGER.debug("bigin");
		return dataBean;
	}
	
	
}
