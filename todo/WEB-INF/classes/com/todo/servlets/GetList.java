package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class GetList extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
Gson gson=new Gson();
try
{
pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
String rawData=b.toString();
GetListUser glu=gson.fromJson(rawData,GetListUser.class);
System.out.println(glu.getUsername());
ArrayList<String> newList=new ArrayList<String>();
ArrayList<String> completedList=new ArrayList<String>();
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Reebok@19970311");
PreparedStatement ps=connection.prepareStatement("select * from newItem where user=?");
ps.setString(1,glu.getUsername());
ResultSet rs=ps.executeQuery();
while(rs.next())
{
newList.add(rs.getString("task"));
}
ps=connection.prepareStatement("select * from completedItem where user=?");
ps.setString(1,glu.getUsername());
rs=ps.executeQuery();
while(rs.next())
{
completedList.add(rs.getString("task"));
}
ps.close();
connection.close();
Save save=new Save();
save.setNewItem(newList);
save.setCompletedItem(completedList);
String jsonObject=gson.toJson(save);
System.out.println(jsonObject);
pw.print(jsonObject);
pw.flush();
}catch(Exception e)
{
String result="{\"Result\":\"Failed\"}";
pw.print(result);
pw.flush();
System.out.println(e);
}
}
}
