package com.todo.pojo;
import java.util.*;
public class Save
{
public String user;
public ArrayList<String> newItem;
public ArrayList<String> completedItem;
public Save()
{
this.user=null;
this.newItem=null;
this.completedItem=null;
}
public void setUser(String user)
{
this.user=user;
}
public String getUser()
{
return this.user;
}
public void setNewItem(ArrayList<String> newItem)
{
this.newItem=newItem;
}
public ArrayList<String> getNewItem()
{
return this.newItem;
}
public void setCompletedItem(ArrayList<String> completedItem)
{
this.completedItem=completedItem;
}
public ArrayList<String> getCompletedItem()
{
return this.completedItem;
}
}
