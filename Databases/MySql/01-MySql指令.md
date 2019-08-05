> ## 给 MySql 添加外链指令

> 1.执行以下命令分配新用户：

```sql
grant all privileges on *.* to '用户名'@'IP地址' identified by '密码';
```

>  2.执行完上述命令后用下面的命令刷新权限

```sql
flush privileges;
```

> 3.重启mysql服务

> #### Ubuntu

```shell
启动mysql：
方式一：sudo /etc/init.d/mysql start 
方式二：sudo service mysql start

停止mysql：
方式一：sudo /etc/init.d/mysql stop 
方式二：sudo service mysql stop

重启mysql：
方式一：sudo/etc/init.d/mysql restart
方式二：sudo service mysql restart
```

> #### 备份 与 恢复 MySql 数据库

> 备份数据库：`mysqldump` `-u` `用户名` `-p` `数据库名` `<数据表::不指定就是全部>` `>` `sql文件`

```shell
root@server:~/mysql$ mysqldump -u root -p database > database.sql
Enter password: 
```

> 恢复数据库：`mysql` `-u` `用户名` `-p` `<` `sql文件`

```shell
root@server:~/mysql$ mysql -u root -p < database.sql
Enter password: 
```

