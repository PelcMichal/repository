package ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ListElementsRemote 
{
	void addElement(Integer e);
	void remElement(Integer e);
	
	List<Integer> getElements();
}
