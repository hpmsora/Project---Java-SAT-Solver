import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile 
{
	private String path;
	
	public ReadFile(String file_path)
	{
		path = file_path;
	}
	
	int readLines() throws IOException
	{
		FileReader file = new FileReader(path);
		BufferedReader bf = new BufferedReader(file);
		
		String line;
		int line_num = 0;
		while((line = bf.readLine()) != null)
		{
			line_num++;
		}
		bf.close();
		return line_num;
	}
	
	public String[] OpenFile() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numLines = readLines();
		String[] textData = new String[numLines];
		
		int i;
		for(i=0; i<numLines; i++)
		{
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}

}
