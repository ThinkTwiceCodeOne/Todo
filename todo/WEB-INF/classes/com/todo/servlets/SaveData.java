package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class SaveData extends HttpServlet
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
System.out.println(rawData);
Save save=gson.fromJson(rawData,Save.class);
String username=save.getUser();
ArrayList<String> newItem=save.getNewItem();
ArrayList<String> completedItem=save.getCompletedItem();
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Reebok@19970311");
PreparedStatement ps=null;
for(int i=0;i<newItem.size();i++)
{
ps=connection.prepareStatement("insert into newItem (user,task) values(?,?)");
ps.setString(1,username);
ps.setString(2,newItem.get(i));
ps.executeUpdate();
ps.close();
}
for(int i=0;i<completedItem.size();i++)
{
ps=connection.prepareStatement("insert into completedItem (user,task) values(?,?)");
ps.setString(1,username);
ps.setString(2,completedItem.get(i));
ps.executeUpdate();
ps.close();
}
connection.close();
String jsonString = "{\"Result\": \"Ok\"}";
JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
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
