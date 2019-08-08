> ## linux 实用命令

```shell
tail -f logs/catalina.2019-01-08.log
```

> `tail -f` `日志文件`

> 后台启动程序

```shell
nohup java -jar XXX.jar &
```

> 检查 IP 与端口是否通

```shell
telnet localhost 22
```

> 两台 linux 之间拷贝文件

```shell
#将文件/文件夹`从远程拷至本地`(下载文件)
scp -r <root>@<ip>:<ip-path> .

#将文件/文件夹`从本地拷至远程`(发送文件)
scp -r <local-path> <root>@<ip>:<ip-path>
```

