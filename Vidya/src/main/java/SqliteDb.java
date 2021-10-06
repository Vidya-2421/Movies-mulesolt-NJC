import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Scanner;

public class SqliteDb {
	static String filename="moviedetails.db";
	
	private static Connection connect() {  
        String url = "jdbc:sqlite:C:/sqlite/" + filename;  
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    }  
	
	public static void createDatabase() {  
	  Connection conn=connect();
		try {  
              if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("**Welcome to Movies database**");  
            }  
          } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }finally {
        	  if (conn != null) {
        		    try {
        		      conn.close(); 
        		    } catch (SQLException e) {
        		      
        		    }
        		   
                }
           }
        }
	
	public static void createTable()
	  {
		Connection conn=connect();  
	  try {
			 
		    String sql="create table IF NOT EXISTS movies(name varchar(20),actor varchar(20),actress varchar(20),director varchar(20),year integer(4))";
			Statement st=conn.createStatement();
			st.execute(sql);
			}catch(Exception e)
		    {
			e.printStackTrace();
		    }
		finally {
      	  if (conn != null) {
      		    try {
      		      conn.close(); 
      		    } catch (SQLException e) {
      		      
      		    }
      		  }
		}
		
	  }
	public static void insertRecord(String name,String actor,String actress,String dir,int year)
	{
		String sql = "INSERT INTO movies(name,actor,actress,director,year) VALUES(?,?,?,?,?)";
		Connection conn=connect();  
		try{  
            
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, name); 
            pstmt.setString(2, actor);  
            pstmt.setString(3, actress); 
            pstmt.setString(4, dir); 
            pstmt.setInt(5,year );
            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {
        	  if (conn != null) {
        		    try {
        		      conn.close(); 
        		    } catch (SQLException e) {
        		      
        		    }
        		  }
        }
	}
	 static void view()
     {
     	String sql="Select * from movies";
     	Connection conn = connect(); 
     	try {  
              
             Statement stmt  = conn.createStatement();  
             ResultSet rs    = stmt.executeQuery(sql); 
             int i=check();
             if(i!=0)
             {
             while (rs.next()) { 
            	   
                 System.out.println("Movie Name:"+rs.getString("name") +  "\n" +   
                                    "Actor     :"+rs.getString("actor") + "\n" +  
                                    "Actress   :"+rs.getString("actress")+"\n"+
                                    "Director  :"+rs.getString("director")+"\n"+
                                    "Year of release:"+rs.getInt("year")+"\n");  
                }  
             }
             else
             {
             	System.out.println("No records present,Please Enter record ");
             }
             rs.close();
     	     }catch (SQLException e) {  
             System.out.println(e.getMessage());  
         } finally {
         	  if (conn != null) {
        		    try {
        		      conn.close(); 
        		    } catch (SQLException e) {
        		      
        		    }
        		  }
         }
     	
     }
     static void search()
     {
    	 Scanner sc=new Scanner(System.in);
    	 Connection conn = connect(); 
    	 int j=0;
    	try { 
    		
            Statement stmt  = conn.createStatement();
            int i=check();
            if(i!=0)
            {
            	System.out.println("Enter actor name");
                String actor=sc.next();
                String sql="Select name from movies where actor="+"\""+actor+"\""+"Collate nocase";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    	System.out.println(rs.getString("name"));
                    	j++;
                       }
                System.out.println();
                if(j==0)
                	System.out.println("Actor not present");
                rs.close();
            }
            else
               System.out.println("Database empty,Please enter records to proceed")	;
            } catch (SQLException e) {
                  e.printStackTrace();
            }finally {
           	  if (conn != null) {
      		    try {
      		      conn.close(); 
      		    } catch (SQLException e) {
      		     
      		    }
      		  }
       } 
         }
     static int check()
     {
    	 int i=0;
    	 Connection conn=connect();
    	 try {
    		 Statement stmt  = conn.createStatement();
             ResultSet rs1=stmt.executeQuery("Select * from movies");
    		while(rs1.next())
            	i++;
            rs1.close();
    	 }
    	 
    	 catch(SQLException e)
    	        {
    	           e.printStackTrace();
    	        }
    	 finally {
          	  if (conn != null) {
     		    try {
     		      conn.close();
     		      
     		    } catch (SQLException e) {
     		      
     		    }
     		  }
      } 
    	return i;

}
}
