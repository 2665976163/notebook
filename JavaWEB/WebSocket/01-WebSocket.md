## WebSocket

​	WebSocket 与 Http 连接不同，Http 属于一种短连接的形式单页面打开时浏览器渲染完成连接就关闭了，而 WebSocket 会在页面打开直到页面关闭都会与服务器进行连接。

```properties
# 长连接状态
Request URL: ws://localhost:8080/WebSocket
Request Method: GET
Status Code: 101 
```

### 服务端需要

#### 在 pom 文件中添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

#### 编写服务端交互

```java
@Component
@ServerEndpoint(value = "/WebSocket")
public class WebSocketDemo {

	@OnOpen/** 页面打开时 */
	public void onOpen(Session session) {
		System.out.println(String.format("opOpen: {session: %s}", session));
	}

	@OnClose/** 页面关闭时 */
	public void onClose() {
		System.out.println(String.format("onClose: {this: %s}", this));
	}

	@OnMessage/** 监听消息 */
	public void onMessage(String message, Session session) {
		System.out.println(String.format("server and html: {message: %s, session: %s}",
                                         message, session));
	}

	@OnError/** 出现异常 */
	public void onError(Session session, Throwable error) {
		System.out.println(String.format("http href onError: {session: %s, error: %s}",
                                         session, error));
	}

}
```

### 前端页面交互

```html
<script type="text/javascript">
    var websocket = null;

    if('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:8080/WebSocket");
    } else{
        alert('当前浏览器不支持 WebSocket 建议跟换为: Google Chrome 浏览器!');
    }

    /** 监听异常 */
    websocket.onerror = function(){
        setMessageInnerHTML("error");
    }

    /** 发送打开事件 */
    websocket.onopen = function(event){
        setMessageInnerHTML("open");
    }

    /** 监听到交互信息 */
    websocket.onmessage = function(event){
        setMessageInnerHTML(event.data);
    }

    /** 提交关闭事件 */
    websocket.onclose = function(){
        setMessageInnerHTML("close");
    }

    /** 先服务器发送消息 */
    function send(){
        var message = document.getElementById('text').value;
        websocket.send(message);
    }

    function setMessageInnerHTML(innerHTML){
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    window.onbeforeunload = function(){
        websocket.close();
    }

    function closeWebSocket(){
        websocket.close();
    }

</script>
```

```javascript
websocket = new WebSocket("ws://localhost:8080/WebSocket");//写的是后端的 Socket 类的地址
```

