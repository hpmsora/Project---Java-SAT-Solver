import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Start 
{
	public static void main(String[] args)
	{
		System.out.println();
		Scanner in = new Scanner(System.in);
		
		String sat_file = "C:" + "\\" + "Users" + "\\" + "nicpalme" + "\\" + "Documents" + "\\" + "SATtest" + "\\" + in.nextLine();
		List<String> sat_text = new ArrayList<>();
		List<variable> vList = new ArrayList<>();
		List<clause> listOfClauses = new ArrayList<>(); 
		int varNum;
		List<variable> givenL = new ArrayList<>();
		
		
		try
		{
			ReadFile file = new ReadFile(sat_file);
			String[] lines = file.OpenFile();
			
			int space1 = lines[0].indexOf(" ");
			String line0a = lines[0].substring(space1+1, lines[0].length());
			int space2 = line0a.indexOf(" ");
			String line0b = line0a.substring(space2+1, line0a.length());
			int space3 = line0b.indexOf(" ");
			String line0c = line0b.substring(0, space3);
//			System.out.println(lines[0]);
//			System.out.println(line0a);
//			System.out.println(line0b);
//			System.out.println(line0c);
			
			varNum = Integer.parseInt(line0c);
			
			int k = 0;
			while(k < varNum)
			{
				vList.add(new variable(Integer.toString(k+1)));
				k++;
			}
			
//			System.out.println(varNum);
			
			int i;
			for (i=0; i<lines.length; i++)
			{
				sat_text.add(lines[i]);
				
				if(i != 0)
				{
					listOfClauses.add(new clause(getVars(lines[i])));
//					System.out.println(listOfClauses.get(i-1).cToString());
				}
				
				System.out.println(sat_text.get(i));
				
			}
			
			
			int j = 0;
			while(j < listOfClauses.size())
			{
				String c = clauseToString(listOfClauses, j);
//				System.out.println("line " + (j+1) + " is: " + c);
//				System.out.println(listOfClauses.get(j).cToString());
				j++;
			}
		
			
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
/*		List<String> test = new ArrayList<>();
		test.add("1");
		test.add("2");
		test.add("3");
		clause c = new clause(test);
		System.out.println(c.cToString());
*/
		
		
//		System.out.println("Line 1 is " + v);
//		System.out.println(sat_text.get(3));
	
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Possible Solution");
		System.out.println("");
		
		
		checkNeg(listOfClauses);
		givens(listOfClauses, vList, givenL);
		entering(listOfClauses, vList);
		//verifyLC(listOfClauses);
		
		showVList(vList);
		boolean x;
		
		x = changing(listOfClauses, vList, givenL, 0);
		
		//System.out.println(x);
		if (x == false)
			System.out.println("NO");
		System.out.println("----------- ");
		
		/*verifyLC(listOfClauses);
		verifySAT(listOfClauses);
		System.out.println("----------- ");
		showVList(vList);
		if(verifySAT(listOfClauses))
		{
			showVList(vList);
		}
		else
		{
			System.out.println("No");
		}
		
		System.out.println("Second check: ");
		
		givens(listOfClauses, vList, givenL);
		showVList(vList);
		
		System.out.println("Third check: ");
		assignBool(listOfClauses, vList);
		checkNeg(listOfClauses);
		
		System.out.println("Fourth check: ");
		simplify(listOfClauses, vList);
		check(listOfClauses);
		
		System.out.println("Fifth check: ");
		verifyLC(listOfClauses);
		verifySAT(listOfClauses);
		
//		variable v = new variable("1");
	
		*/

		
	}
	
	
	
	
	
	
	public static List<variable> getVars(String s)
	{
		List<variable> listOfVars = new ArrayList<>();
		while(!s.equals("0"))
		{
				
				int space = s.indexOf(" ");
				
//				System.out.println(s);
//				System.out.println("space - " + space);
				
				listOfVars.add(new variable(s.substring(0, space)));			
				s = s.substring(space+1, s.length());
				
			
		}
		
		return listOfVars;
	}
	
	
	/*public static String varsToString(List<String> listOfVars)
	{
		String vars = "";
		int i=0;
		while(i < listOfVars.size())
		{
			vars = vars + " " + listOfVars.get(i);
			i++;
		}
		
		return vars;
	}*/
	
	public static void entering(List<clause> listOfClauses, List<variable> vList)
	{
		int i = 0;
		while (i < listOfClauses.size())
		{
			int j = 0;
			while (j < listOfClauses.get(i).size())
			{
				int m = 0;
				while (m < vList.size())
				{
					if(listOfClauses.get(i).get(j).naming().equals(vList.get(m).naming()))
					{
						listOfClauses.get(i).get(j).chngbool(vList.get(m).getbool());
						listOfClauses.get(i).get(j).negative();
					}
					m++;
				}
				j++;
			}
			i++;
		}
	}
	
	public static String clauseToString(List<clause> listOfClauses, int num)
	{
		String clause = "";
		int i=0;
		while(i < listOfClauses.size())
		{
			listOfClauses.get(num).cToString();
			i++;
		}
		
		return clause;
	}
	
	
	public static List<clause> assignBool(List<clause> listOfClauses, List<variable> vList)
	{
		int i = 0;
		while(i < listOfClauses.size())
		{
			int j = 0;
			while(j < listOfClauses.get(i).size())
			{
					int k = 0;
					while(k < vList.size())
					{
						if(listOfClauses.get(i).get(j).naming().equals(vList.get(k).naming()))
						{
							if(!vList.get(k).getbool())
							{
								listOfClauses.get(i).get(j).False();
							}
							else
							{
								listOfClauses.get(i).get(j).True();
							}
						}
						k++;
					}
					j++;
			}
			i++;
			
		}
		
		
		
		return listOfClauses;
	}
	
	
	public static List<clause> checkNeg(List<clause> listOfClauses)
	{
		int i = 0;
		while(i < listOfClauses.size())
		{
			int j = 0;
			while(j < listOfClauses.get(i).size())
			{
				listOfClauses.get(i).get(j).negative();
				//System.out.println(listOfClauses.get(i).get(j).toString());
				j++;
			}
			i++;
		}
		return listOfClauses;
	}
	
	public static void check(List<clause> listOfClauses)
	{
		int i = 0;
		while(i < listOfClauses.size())
		{
			
				System.out.println(listOfClauses.get(i).viewC());
				
			
			i++;
		}
	}
	
	
	public static List<variable> assignV(List<variable> vList, String s, boolean b)
	{
		int i = 0;
		while(i < vList.size())
		{
			if(vList.get(i).naming().equals(s))
			{
				if(b)
				{
					vList.get(i).True();
				}
				else
				{
					vList.get(i).False();
				}
			}
			i++;
		}
		return vList;
	}
	
	
	public static void showVList(List<variable> vList)
	{
		int i = 0;
		while(i < vList.size())
		{
			System.out.println(vList.get(i).toString());
			i++;
		}
		
	}
	
	
	public static List<clause> givens(List<clause> listOfClauses, List<variable> vList, List<variable> givenL)
	{
		int i = 0;
		while(i < listOfClauses.size())
		{
			if(listOfClauses.get(i).size() == 1)
			{
					givenL.add(listOfClauses.get(i).get(0));
					String s = listOfClauses.get(i).get(0).naming();
					boolean b = listOfClauses.get(i).get(0).getbool();
					assignV(vList, s, !b);
					
			}
			
			i++;
		}
		return listOfClauses;
	}
	
	public static List<clause> simplify(List<clause> listOfClauses, List<variable> vList)
	{
		List<clause> tempC = new ArrayList<>();
		int i = 0;
		while(i < listOfClauses.size())
		{
			
			int j = 0;
			while(j < listOfClauses.get(i).size())
			{
				int k = 0;
				while(k < vList.size())
				{
					if(listOfClauses.get(i).get(j).naming().equals(vList.get(k).naming()) && vList.get(k).getbool())
					{
						List<variable> temp = new ArrayList<>();
						if(!listOfClauses.get(i).get(j).getbool())
						{
							temp.add(listOfClauses.get(i).get(j));
							tempC.add(new clause(temp));
						}

					}
					k++;
				}
				j++;
			}
			i++;
		}
		listOfClauses = tempC;
		return listOfClauses;
	}
	
	public static void verifyLC(List<clause> listOfClauses)
	{
		
		int i = 0;
		while(i < listOfClauses.size())
		{
			System.out.println(listOfClauses.get(i).verifyC());
			i++;
		}
		
	}

	public static boolean verifySAT(List<clause> listOfClauses)
	{
		boolean b = true;
		int i = 0;
		while(i < listOfClauses.size())
		{
			if(!listOfClauses.get(i).solvedC())
			{
				b = false;
			}
			i++;
		}
		//System.out.println("SAT solved? " + b);
		return b;
		
	}

	public static boolean changing(List<clause> listOfClauses, List<variable> vList, List<variable> givenL, int pos)
	{
		 
		
/*			int i = 0;
			while(i < vList.size() && !verifySAT(listOfClauses))
			{
				String g = "";
				int j = 0;
				while(j < givenL.size())
				{
					if(vList.get(i).naming().equals(givenL.get(j).naming()))
					{
						g = g + givenL.get(j).naming();
					}
					
					j++;
				}
				
				if(!vList.get(i).naming().equals(g))
				{
					if(!vList.get(i).getbool())
					{
						vList.get(i).True();
					}	
					else
					{
						vList.get(i).False();
					}
				}
				
				
				listOfClauses = assignBool(listOfClauses, vList);
				
				i++;

			}
			
			
			return vList;*/
		entering(listOfClauses, vList);
		if (verifySAT(listOfClauses))
		{
			List<variable> zList = new ArrayList<>();
			int i = 0;
			while(i < vList.size())
			{
				zList.add(vList.get(i));
				i++;
			}
			showVList(zList);
			verifyLC(listOfClauses);
			return true;
			
		}
		else if (vList.size() <= pos+1)
		{
			return false;
		}
		else
		{
			int j = 0;
			int x = 0;
			while (j < givenL.size())
			{
				
				if(vList.get(pos).naming().equals(givenL.get(j).naming()))
				{
					pos++;
					x++;
					changing(listOfClauses, vList, givenL, pos);
				}
				j++;
			}
			if(x == 0){
				//showVList(vList);
				//verifyLC(listOfClauses);
				List<variable> lList = new ArrayList<>();
				int i = 0;
				while(i < vList.size())
				{
					lList.add(vList.get(i));
					i++;
				}
				
				if(!lList.get(pos).getbool())
				{
					lList.get(pos).True();
				}
				else
				{
					lList.get(pos).False();
				}
				pos++;
				return changing(listOfClauses, vList, givenL, pos) || changing(listOfClauses, lList, givenL, pos);
			}
			else
				return false;
		}
		
	}


}

