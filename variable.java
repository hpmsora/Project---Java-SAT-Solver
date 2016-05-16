
public class variable 
{
	String name;
	boolean bool;
	boolean neg;
//	boolean negated;
	
	public variable()
	{
		if(name.substring(0,1).equals("-"))
		{
			this.name = name.substring(1,name.length());
			this.neg = false;
		}
		else
		{
			this.name = name;
			this.neg = true;
		}
		this.bool = bool;
	//	this.negated = false;
		
	}
	public variable(String name)
	{
		if(name.substring(0,1).equals("-"))
		{
			this.name = name.substring(1,name.length());
			this.neg = false;
		}
		else
		{
			this.name = name;
			this.neg = true;
		}
		this.bool = bool;
	//	this.negated = true;
	}
	
	public void negative()
	{
		//if(!this.negated)
		//{
		//	this.negated = true;
			if(!this.neg)
			{
				if(this.bool)
				{
					this.bool = false;
				}
				else
				{
					this.bool = true;
				}
			}
			else
			{
				if(this.bool)
				{
					this.bool = true;
				}
				else
				{
					this.bool = false;
				}
			}
		//}
		//else
	//	{
	//		this.bool = bool;
	//	}
	}
	public void True()
	{
		this.bool = true;
	}
	public void False()
	{
		this.bool = false;
	}
	public String naming()
	{
		return this.name;
	}
	public boolean getbool()
	{
		return this.bool;
	}
	public void chngbool(boolean x)
	{
		this.bool = x;
	}
	public String toString()
	{
		String s = name + ": " + bool;
		return s;
	}
	
	
}
