package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class LogIn extends HttpServlet
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
LogInPojo lgnIn=gson.fromJson(rawData,LogInPojo.class);
String username=lgnIn.getUsername();
String password=lgnIn.getPassword();
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Reebok@19970311");
PreparedStatement ps=connection.prepareStatement("select * from signUp where username=? and password=?");
ps.setString(1,username);
ps.setString(2,password);
ResultSet rs=ps.executeQuery();
if(rs.next()==false) 
{
responseData.setIsSuccess(false);
responseData.setMessage("Invalid username or password");
String result=gson.toJson(responseData);
pw.print(result);
pw.flush();
}
else
{
HttpSession httpSession=request.getSession();
httpSession.setAttribute("username",username);
ps.close();
connection.close();
responseData.setIsSuccess(true);
String result=gson.toJson(responseData);
pw.print(result);
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
