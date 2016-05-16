import java.util.ArrayList;
import java.util.List;


public class clause
{
	List<variable> vList = new ArrayList<>();
	
	public clause(List<variable> vList)
	{
		this.vList = vList;
	}
	
	public String cToString()
	{
		String s = "";
		
		int i = 0;
		while(i < this.vList.size())
		{
			s = s + " " + this.vList.get(i).naming();
			i++;
		}
		
		return s;
	}
	
	public String getS(int num)
	{
		String s = "";
		s = this.vList.get(num).toString();
		return s;
	}
	
	public variable get(int num)
	{
		return this.vList.get(num);
	}
	
	public int size()
	{
		int s;
		s = this.vList.size();
		return s;
	}
	
	
	public String viewC()
	{
		String c = "";
		c = c + "( ";
		int i = 0;
		while(i < this.vList.size())
		{
			c = c + this.vList.get(i) + " ";
			i++;
		}
		c = c + ")";
		
		return c;
	}
	
	public String verifyC()
	{
		boolean b = false;
		String s = this.viewC();
		int i = 0;
		while(i < this.vList.size())
		{
			if(this.vList.get(i).getbool())
			{
				b = true;
			}
			i++;
		}
		s = s + " ---> " + b;
		return s;
	}
	
	public boolean solvedC()
	{
		boolean b = false;
		int i = 0;
		while(i < this.vList.size())
		{
			if(this.vList.get(i).getbool())
			{
				b = true;
			}
			i++;
		}
		return b;
	}
	
}
