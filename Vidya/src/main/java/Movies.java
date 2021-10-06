import java.util.Scanner;
import java.util.regex.Pattern;
public class Movies {
	static SqliteDb db=new SqliteDb();
	public static void main(String[] args) { 
	        int ch;
	        SqliteDb db=new SqliteDb();
	    	db.createDatabase(); 
	        db.createTable();
	        Scanner sc=new Scanner(System.in);
	        do {
	        System.out.println("1.Enter record"+"\n"+"2.View records"+"\n"+"3.Search movies acted by an actor"+"\n"+"4.Exit"+"\n");
	        System.out.println("Enter ur choice");
	        ch=sc.nextInt();
	        switch(ch)
	        {
	           case 1:insertMovie();
	                  break;
	           case 2:db.view();
	                  break;
	           case 3:db.search();
	                  break;
	           case 4:System.exit(0);
	           default:System.out.println("Enter valid options");
	        }
	        }while(ch!=4);
	      } 
	 static void insertMovie()
     {
     	Scanner sc=new Scanner(System.in);
     	int n,digit;
     	System.out.println("Enter the number of record to be inserted");
     	try {
        n=sc.nextInt();
     	}catch(Exception e)
     	{
     	    System.out.println("Please Enter an number") ;
     	    return;
     	}
         for(int i=0;i<n;i++)
         {
         	System.out.println("Enter name of the movie:");
         	String name=sc.nextLine();
         	name+=sc.nextLine();
         	System.out.println("Enter actor name:");
         	String actor=sc.nextLine();
         	System.out.println("Enter actress name:");
         	String actress=sc.nextLine();
         	System.out.println("Enter director name:");
         	String dir=sc.nextLine();
         	System.out.println("Enter year of release :");
         	String year=sc.nextLine();
         	digit=checkyear(year);
         	
            db.insertRecord(name,actor,actress,dir,digit);
         }
      }
	 static int  checkyear(String year)
	 {
		 int digit;
		 Scanner sc=new Scanner(System.in);
		 while (true) {
			    if (Pattern.matches ("\\d{4}+", year)) {
			    	try {
	                digit = Integer.parseInt (year);
	                break;
			    	}catch(Exception e)
			    	{
			    		System.out.println("Please enter a 4 digit interger");
			    	}
	               }

	            System.out.println ("Error!(" + year + ") Please enter a 4 digit number: ");
	            year= sc.nextLine ();
	        }
		 return digit;
		 
	 }
	 
}
