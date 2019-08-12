> 在application.yml文件中设置multipart location ，并重启项目

```yaml
spring:
  http:
    multipart:
      location: /data/upload_tmp

---

server
  tomcat:
     basedir: /tmp/tomcat

```
