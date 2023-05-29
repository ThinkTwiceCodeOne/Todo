# Todo
This is Todo list using Jquery and Java

This is a Todo List. To run this you need to put the folder in tomcat9/webapps.

Now you can start server and run by **localhost:8080/todo**

It has following feature

**User can SignUp**
**User can LogIn**
**User can add task in todo and move from New to Completed**
**Add task by writing in text box and click enter
**To save it you need to click save button**

Now 4 table you have to made in your computer. I used mysql. Also you need to change configuration in /com/todo/servlets. You've to change a little bit of JDBC code according to username and password

Table Info 

```
create table signUp
(
id int primary key auto_increment,
username varchar(35),
password varchar(35)
)
```



```
create table signUp
(
id int primary key auto_increment,
username varchar(35),
password varchar(35)
)
```

```create table newItem
(
user varchar(35),
task varchar(35)
)
```


```create table completedItem
(
user varchar(35),
task varchar(35)
)
```

That's what you need now you can start server and run it.

Thanks You !!
