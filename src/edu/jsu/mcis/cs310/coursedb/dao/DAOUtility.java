package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
     
        try {
        
            if (rs != null) {
                
                // Get the metadata from the ResultSet
                ResultSetMetaData rsmd = rs.getMetaData();

                // Iterate through the ResultSet
                while (rs.next()) {
                    
                    // Create a JsonObject to hold information
                    JsonObject rowData = new JsonObject();
                    
                    // Iterate through each column in the current row of ResultSet
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        
                        // Obtain the name and value from the columns (and turn this into a String); Place data into JsonObject
                        String columnName = rsmd.getColumnName(i);
                        Object columnValue = rs.getObject(i).toString();
                        
                        rowData.put(columnName, columnValue);
                    }
                    
                    // Add the object to the JsonArray
                    records.add(rowData);
                }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
