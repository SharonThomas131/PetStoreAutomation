package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//test-data//Userdata.xlsx";
		//XLUtility xl = new XLUtility(); /all static methods
		
		int rownum = XLUtility.getRowCount(path, "Sheet1");
		int colcount = XLUtility.getCellCount(path, "Sheet1", 1);
		
		String api_data[][] = new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) //leaving the header row
		{
			for(int j=0; j<colcount; j++)
			{
				api_data[i-1][j] = XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return api_data;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir")+"//test-data//Userdata.xlsx";
		
		int rownum = XLUtility.getRowCount(path, "Sheet1");

		String[] usernames = new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
			usernames[i-1] = XLUtility.getCellData(path, "Sheet1", i, 1);
		}
		
		return usernames;
	}

}
