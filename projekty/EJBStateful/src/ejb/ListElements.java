package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful(mappedName = "stateful123")
@LocalBean
public class ListElements implements ListElementsRemote{
	List<Integer> values = new ArrayList<>();
	@Override
	public void addElement(Integer e) 
	{
		values.add(e);
	}
	@Override
	public void remElement(Integer e) 
	{
		values.remove(e);
	}
	@Override
	public List<Integer> getElements() 
	{
		return values;
	}

}
