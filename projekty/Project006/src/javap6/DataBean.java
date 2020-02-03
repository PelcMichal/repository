package javap6;

import org.apache.log4j.Logger;

public class DataBean {
	
	private static final Logger LOGGER = Logger.getLogger(DataBean.class);
	
	private String name;
	private String surrname;
	private String phonenumber;
	
	
	public DataBean() {
		super();
		LOGGER.debug("bigin");
	}

	public DataBean(String name, String surrname, String phonenumber) {
		super();
		
		LOGGER.debug("bigin");
		
		this.name = name;
		this.surrname = surrname;
		this.phonenumber = phonenumber;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurrname() {
		return surrname;
	}
	public void setSurrname(String surrname) {
		this.surrname = surrname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
