package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class SignUp extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
Gson gson=new Gson();
ResponseData responseData=new ResponseData();
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
SignUpPojo sgnUp=gson.fromJson(rawData,SignUpPojo.class);
System.out.println(sgnUp.getUsername());
String username=sgnUp.getUsername();
String password=sgnUp.getPassword();
System.out.println(sgnUp.getPassword());
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Reebok@19970311");
PreparedStatement ps=connection.prepareStatement("select * from signUp where username=?");
ps.setString(1,username);
ResultSet rs=ps.executeQuery();
if(rs.next()) 
{
System.out.println("inside if");
responseData.setIsSuccess(false);
responseData.setMessage(username+" already exist");
String jsonObject=gson.toJson(responseData);
pw.print(jsonObject);
pw.flush();
}
else
{
ps=connection.prepareStatement("insert into signUp (username,password)values(?,?)");
ps.setString(1,username);
ps.setString(2,password);
ps.executeUpdate();
ps.close();
connection.close();
responseData.setIsSuccess(true);
String jsonObject = gson.toJson(responseData);
pw.print(jsonObject);
pw.flush();
}
}catch(Exception e)
{
responseData.setIsSuccess(false);
String result=gson.toJson(responseData);
pw.print(result);
pw.flush();
System.out.println(e);
}
}
}
