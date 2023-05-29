package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class Authenticate extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
Gson gson=new Gson();
ResponseData responseData=new ResponseData();
try
{
pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
HttpSession httpSession=request.getSession();
if(httpSession.getAttribute("username")==null)
{
responseData.setIsSuccess(false);
String jsonObject=gson.toJson(responseData);
pw.print(jsonObject);
}
else
{
String username=(String)httpSession.getAttribute("username");
responseData.setIsSuccess(true);
responseData.setMessage(username);
String jsonObject = gson.toJson(responseData);
pw.print(jsonObject);
}
pw.flush();
}catch(Exception e)
{
responseData.setIsSuccess(false);
String jsonObject=gson.toJson(responseData);
pw.print(jsonObject);
pw.flush();
System.out.println(e);
}
}
}
