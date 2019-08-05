> #### guns helloWorld

1. 从`github`中将代码弄下来:[guns](https://github.com/stylefeng/Guns)
2. 将其导入`eclipse`中, 对开发环境进行配置
   1. 配置数据库基本信息
   2. 配置开发环境
3. 运行`SpringBoot`启动类

> #### 配置数据库基本信息: 
>
> 找到: Guns-master\guns-admin\sql\guns.sql执行

> #### 配置开发环境
>
> 找到文件: Guns-master\guns-admin\src\main\resources\application.yml

```yaml
server:
  port: 8080

guns:
  swagger-open: true              #是否开启swagger (true/false)
  kaptcha-open: false             #是否开启登录时验证码 (true/false)
#  file-upload-path: d:/tmp       #文件上传目录(不配置的话为java.io.tmpdir目录)
  spring-session-open: false      #是否开启spring session,如果是多机环境需要开启(true/false)
  session-invalidate-time: 1800     #session失效时间(只在单机环境下生效，多机环境在SpringSessionConfig类中配置) 单位：秒
  session-validation-interval: 900  #多久检测一次失效的session(只在单机环境下生效) 单位：秒

spring:
  profiles:
    active: dev/** 现在当前项目的环境 */
  mvc:
    static-path-pattern: /static/**
    view:
      prefix: /WEB-INF/view
  devtools:
    restart:
      enabled: false
      additional-paths: src/main/java
      exclude: static/**,WEB-INF/view/**
  servlet:
    multipart:
      max-request-size: 100MB
      max-file-size: 100MB

mybatis-plus:
  typeAliasesPackage: com.stylefeng.guns.modular.system.model

log:
  path: guns-logs

---

spring:
  profiles: local
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    db-name: guns #用来搜集数据库的所有表
    filters: wall,mergeStat

#多数据源情况的配置
guns:
  muti-datasource:
    open: false
    url: jdbc:mysql://127.0.0.1:3306/guns_test?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    dataSourceNames:
      - dataSourceGuns
      - dataSourceBiz

---

spring:
  profiles: dev
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    db-name: guns #用来搜集数据库的所有表
    filters: wall,mergeStat

---

spring:
  profiles: test
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    filters: wall,mergeStat
```

> 运行`SpringBoot`启动类
>
> Guns-master\guns-admin\src\main\java\com\stylefeng\guns\GunsApplication.java

---

> 登录系统后生成代码:
>
> * 注意一个点生成成功后会有一个`sql`文件需要我们执行: 
>   * Guns-master\guns-admin\src\main\java\TblUser.sql
>   * 在角色管理中配置用户权限
>   * 若生成的管理中没有操作按钮, 退出重新登录就行了