package MyPack1;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
	
	Connection con;
	CRUD() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");
		Statement st=con.createStatement();
		st.executeUpdate("create table CRUD(id number(10),name varchar2(20),salary number(10))");
		System.out.println("Table is created successfully!");
		System.out.println();
		
	}
	
	void insert() throws SQLException, NumberFormatException, IOException
	{
		PreparedStatement pst=con.prepareStatement("insert into CRUD values(?,?,?)");
		InputStreamReader ir=new  InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		System.out.println("Enter id:");
		Integer id=Integer.parseInt(br.readLine());
		pst.setInt(1, id);
		System.out.println("Enter name:");
		String name=br.readLine();
		pst.setString(2,name);
		System.out.println("Enter Salary:");
		Integer salary=Integer.parseInt(br.readLine());
		pst.setInt(3, salary);	
		pst.executeUpdate();
		System.out.println("Data is inserted successfully!");
		System.out.println();
	}
	
	void display() throws SQLException
	{
		Statement st=con.createStatement();
		ResultSet RS=st.executeQuery("select * from CRUD");
		while(RS.next())
		{
			System.out.println(RS.getInt(1)+" "+RS.getString(2)+" "+RS.getInt(3));
		}		
		System.out.println();
	}
	
	void update() throws SQLException, NumberFormatException, IOException
	{
		CallableStatement cs=con.prepareCall("{call updateRecord(?,?,?)}");
		InputStreamReader ir=new  InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		System.out.println("Enter id:");
		Integer id=Integer.parseInt(br.readLine());
		cs.setInt(1, id);
		System.out.println("Enter name:");
		String name=br.readLine();
		cs.setString(2,name);
		System.out.println("Enter Salary:");
		Integer salary=Integer.parseInt(br.readLine());
		cs.setInt(3, salary);		
		cs.execute();
		System.out.println("Data is updated Successfully!");
		System.out.println();
	}
	
	void delete() throws SQLException, NumberFormatException, IOException
	{
		CallableStatement cs=con.prepareCall("{call deleteRecord(?)}");
		InputStreamReader ir=new  InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		System.out.println("Enter id to delete:");
		Integer id=Integer.parseInt(br.readLine());
		cs.setInt(1, id);
		cs.execute();
		System.out.println("Data is deleted Successfully!");
		System.out.println();
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		CRUD obj=new CRUD();
		Integer choice;
		do
		{
		System.out.println("1.Insert Data");
		System.out.println("2.Update Data");
		System.out.println("3.Delete Data");
		System.out.println("4.Display Data");
		System.out.println("5.Exit");
		InputStreamReader ir=new  InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		System.out.println("Enter your Choice:");
		choice=Integer.parseInt(br.readLine());
		switch(choice)
		{
		case 1:obj.insert();
		break;
		case 2:obj.update();
		break;
		case 3:obj.delete();
		break;
		case 4:obj.display();
		break;
		case 5:System.out.println("Exiting...");
		break;
		default:System.out.println("Invalid Choice");
		}
		}
		while(choice!=5);
	}
}
