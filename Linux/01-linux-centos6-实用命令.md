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
scp -r /root/path/ <root>@<IP:192.168.10.83>:/root/path
```

