package com.todo.pojo;
import java.util.*;
public class ResponseData
{
public boolean isSuccess;
public String message;
public ArrayList<String> newList;
public ArrayList<String> completedList;
public ResponseData()
{
this.message=null;
this.isSuccess=false;
this.newList=null;
this.completedList=null;
}
public void setMessage(String message)
{
this.message=message;
}
public String getMessage()
{
return this.message;
}
public void setIsSuccess(boolean isSuccess)
{
this.isSuccess=isSuccess;
}
public boolean getIsSuccess()
{
return this.isSuccess;
}
public void setNewList(ArrayList<String> newList)
{
this.newList=newList;
}
public ArrayList<String> getNewList()
{
return this.newList;
}
public void setCompletedList(ArrayList<String> completedList)
{
this.completedList=completedList;
}
public ArrayList<String> getCompletedList()
{
return this.completedList;
}
}
