package exception;

public class PropertyFileException extends java.lang.Exception {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	private final String propertyValue;
	
	public PropertyFileException(String message,String propertyName,String propertyValue) {
		super(message+propertyName+propertyValue);
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}
	public PropertyFileException(String propertyName,String propertyValue) {
		super("Property not found"+propertyName+propertyValue);
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}
	public PropertyFileException(String message) {
		super(message);
		this.propertyName ="";
		this.propertyValue ="";
	}
	public PropertyFileException() {
		super("Property not found");
		this.propertyName ="";
		this.propertyValue ="";
	}
	public String getPropertyName() {
		return propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
}
