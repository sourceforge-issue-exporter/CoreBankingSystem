

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

/**
 * Servlet implementation class for Servlet: QueryResult
 *
 */
 public class QueryResult extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public QueryResult() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Index myIndex = new Index();
		ServletContext ctx=this.getServletContext();
		String login =ctx.getAttribute("loginsession").toString();
		DataSource dsource = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
			 stmt1=conn.createStatement();
			 per=ctx.getAttribute("UserID").toString();
			 ctx.setAttribute("loginsession", "T");
			 ctx.setAttribute("UserID",per);
			 query="select * from logininfo where logininfo.user='"+per+"'";
			 rset1=stmt.executeQuery(query);
			 
			 while(rset1.next())
			 {
				date=rset1.getString(2);
				time=rset1.getString(3);
				 break;
			 }
			 
			 float balance=0;
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]!='c')
			{
			if(perarray[0]=='e')
			
				//employee
				pw.println(myIndex.section1+myIndex.employee);
			else if(perarray[0]=='a')
				pw.println(myIndex.section1+myIndex.admin);
				String user = request.getParameter("user");
				String type_of_query = request.getParameter("taxtype");
				int x=user.indexOf("login");
				if(x==-1 && perarray[0]=='e')
				{
				pw.println("<td valign=\"top\" width=580><font size=3><B>Search Results</B></font><table>");
				if(type_of_query.equals("Search"))
				{
					ResultSet rst=stmt.executeQuery(user);
					ResultSetMetaData rsmd = rst.getMetaData();
				    int numCols = rsmd.getColumnCount();
				    
				    
					while(rst.next())
					{
						pw.println("<tr>");
						for(int r=1;r<=numCols;r++)
						{
						
						if(rsmd.getColumnTypeName(r).equals("VARCHAR"))	
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getString(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DOUBLE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getFloat(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("INTEGER"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getInt(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DATE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getDate(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("TIME"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getTime(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("BIGINT"))
							pw.println("<td style=\"border:1px #000000 solid\">"+rst.getLong(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("SMALLINT"))
							pw.println("<td style=\"border:1px #000000 solid\">"+rst.getShort(r)+"</td>");
						}
						
						
						pw.println("</tr>");
					}
				    
				}
			
				
				pw.println("</table></td>");
				}
				else
					pw.println("<td valign=\"top\" width=580><font size=3><B>Inappropriate Query</B></font></td>");
				if(perarray[0]=='a')
				{
				pw.println("<td valign=\"top\" width=580><font size=3><B>Search Results</B></font><table>");
				if(type_of_query.equals("Search"))
				{
					ResultSet rst=stmt.executeQuery(user);
					ResultSetMetaData rsmd = rst.getMetaData();
				    int numCols = rsmd.getColumnCount();
				    
				    
					while(rst.next())
					{
						pw.println("<tr>");
						for(int r=1;r<=numCols;r++)
						{
						
						if(rsmd.getColumnTypeName(r).equals("VARCHAR"))	
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getString(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DOUBLE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getFloat(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("INTEGER"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getInt(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DATE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getDate(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("TIME"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getTime(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("BIGINT"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getLong(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("SMALLINT"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getShort(r)+"</td>");
						
						}
						
						
						pw.println("</tr>");
					}
				    
				}
			
				else if(type_of_query.equals("Update"))
				{
					stmt.executeUpdate(query);
					pw.println("<td valign=\"top\" width=580><font size=3><B>Query Executed Successfully</B></font></td>");
				}
				
				pw.println("</table></td>");
				}
			
			}
			else
				pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				if(perarray[0]=='e')
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
				else if(perarray[0]=='a')
					pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			
			 }
			catch(Exception ex)
			{
				
			}
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		Index myIndex = new Index();
		ServletContext ctx=this.getServletContext();
		String login =ctx.getAttribute("loginsession").toString();
		DataSource dsource = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Connection conn = null;
		ResultSet rset = null;
		ResultSet rset1 = null;
		
		if(!login.equals("T"))
			pw.println(myIndex.section1+myIndex.section2+myIndex.section3+myIndex.section4);
		else
		{
			String per="";
			String date="",time="";
			String query="";
			try
			 {
			 InitialContext context = new InitialContext ();
			 dsource = (DataSource) context.lookup("java:comp/env/jdbc/MyDataSource");
			 conn = dsource.getConnection();
			 stmt=conn.createStatement();
			 stmt1=conn.createStatement();
			 per=ctx.getAttribute("UserID").toString();
			 ctx.setAttribute("loginsession", "T");
			 ctx.setAttribute("UserID",per);
			 query="select * from logininfo where logininfo.user='"+per+"'";
			 rset1=stmt.executeQuery(query);
			 
			 while(rset1.next())
			 {
				date=rset1.getString(2);
				time=rset1.getString(3);
				 break;
			 }
			 
			 float balance=0;
			char[] perarray = new char[per.length()];
			perarray=per.toCharArray();
			if(perarray[0]!='c')
			{
			if(perarray[0]=='e')
			
				//employee
				pw.println(myIndex.section1+myIndex.employee);
			else if(perarray[0]=='a')
				pw.println(myIndex.section1+myIndex.admin);
				String user = request.getParameter("user");
				String type_of_query = request.getParameter("taxtype");
				int x=user.indexOf("login");
				if(x==-1 && perarray[0]=='e')
				{
				pw.println("<td valign=\"top\" width=580><font size=3><B>Search Results</B></font><table>");
				if(type_of_query.equals("Search"))
				{
					ResultSet rst=stmt.executeQuery(user);
					ResultSetMetaData rsmd = rst.getMetaData();
				    int numCols = rsmd.getColumnCount();
				    
				    
					while(rst.next())
					{
						pw.println("<tr>");
						for(int r=1;r<=numCols;r++)
						{
						
						if(rsmd.getColumnTypeName(r).equals("VARCHAR"))	
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getString(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DOUBLE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getFloat(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("INTEGER"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getInt(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DATE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getDate(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("TIME"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getTime(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("BIGINT"))
							pw.println("<td style=\"border:1px #000000 solid\">"+rst.getLong(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("SMALLINT"))
							pw.println("<td style=\"border:1px #000000 solid\">"+rst.getShort(r)+"</td>");
						}
						
						
						pw.println("</tr>");
					}
				    
				}
			
				
				pw.println("</table></td>");
				}
				else
					pw.println("<td valign=\"top\" width=580><font size=3><B>Inappropriate Query</B></font></td>");
				if(perarray[0]=='a')
				{
				pw.println("<td valign=\"top\" width=580><font size=3><B>Search Results</B></font><table>");
				if(type_of_query.equals("Search"))
				{
					ResultSet rst=stmt.executeQuery(user);
					ResultSetMetaData rsmd = rst.getMetaData();
				    int numCols = rsmd.getColumnCount();
				    
				    
					while(rst.next())
					{
						pw.println("<tr>");
						for(int r=1;r<=numCols;r++)
						{
						
						if(rsmd.getColumnTypeName(r).equals("VARCHAR"))	
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getString(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DOUBLE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getFloat(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("INTEGER"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getInt(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("DATE"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getDate(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("TIME"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getTime(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("BIGINT"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getLong(r)+"</td>");
						else if(rsmd.getColumnTypeName(r).equals("SMALLINT"))
						pw.println("<td style=\"border:1px #000000 solid\">"+rst.getShort(r)+"</td>");
						
						}
						
						
						pw.println("</tr>");
					}
				    
				}
			
				else if(type_of_query.equals("Update"))
				{
					stmt.executeUpdate(query);
					pw.println("<td valign=\"top\" width=580><font size=3><B>Query Executed Successfully</B></font></td>");
				}
				
				pw.println("</table></td>");
				}
			
			}
			else
				pw.println("<html><title>Error Page!</title><body><font color=\"red\" size=3><B>Error! This page is not meant for you</font></B></body></html>");

				if(perarray[0]=='e')
				pw.println(myIndex.section6+"Employee"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
				else if(perarray[0]=='a')
					pw.println(myIndex.section6+"Administrator"+myIndex.section7+date+myIndex.section8+time+myIndex.section9);
			
			 }
			catch(Exception ex)
			{
				
			}
		}
	}   	  	    
}