package api.utilies;

import java.io.IOException;

import org.testng.annotations.DataProvider;


import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException {

    	String path = System.getProperty("user.dir")+"//testData/Userdata.xlsx";
        DataUtility util = new DataUtility(path);

        int totalRows = util.getRowCount("Sheet1");   // last row index
        int totalCells = util.getCellCount("Sheet1", 1); // column count

        System.out.println("totalRows----->"+totalRows);
        // exclude header row
        String apiData[][] = new String[totalRows][totalCells];

        for(int i = 1; i <= totalRows; i++)   // start from row 1 (skip header)
        {
            for(int j = 0; j < totalCells; j++)   // IMPORTANT < not <=
            {
            	apiData[i-1][j] = util.getCellData("Sheet1", i, j);
            }
        }

        return apiData;
    }
    
    
    @DataProvider(name="UserName")
    public String[] getUserNames() throws IOException {

    	String path = System.getProperty("user.dir")+"//testData/Userdata.xlsx";
        DataUtility util = new DataUtility(path);

        int totalRows = util.getRowCount("Sheet1");   // last row index

        // exclude header row
        String apiData[]= new String[totalRows];

        for(int i = 1; i <= totalRows; i++)   // start from row 1 (skip header)
        {
           
            	apiData[i-1] = util.getCellData("Sheet1", i, 1);
            
        }

        return apiData;
    }
}