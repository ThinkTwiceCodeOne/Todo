package com.todo.servlets;
import com.todo.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class LogOut extends HttpServlet
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
httpSession.invalidate();
responseData.setIsSuccess(true);
String jsonObject=gson.toJson(responseData);
pw.print(jsonObject);
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
