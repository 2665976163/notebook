> ### markdown-开发

1. 解压 `editor.md-master.zip` 
2. 导入 web 项目
3. 编辑 html 页面
4. 编写 servlet 获取值



> 解压 `editor.md-master.zip`  AND 导入 web 项目

---

> editor.md-master.zip: [下载链接](http://pandao.github.io/editor.md/)

---

> 编写 html 页面使用

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>markdown</title>
        <link rel="stylesheet" href="editor/examples/css/style.css" />
        <link rel="stylesheet" href="editor/css/editormd.css" />
        <%-- 需要导入两个 CSS --%>
    </head>
    <body>
        
        <div id="test-editormd">
            <textarea style="display:none;">
            	<%-- 页面一开始显示的内容 --%>
            </textarea>
        </div>
        
       	<button onclick="save()" class="btn">save</button>
       	
        <%-- 导入jquery --%>
        <script src="editor/examples/js/jquery.min.js"></script>
        <%-- 导入 editormd 语法 --%>
		<script src="editor/editormd.min.js"></script>
        
        <script type="text/javascript">
			var editor;

            /** 初始化编辑器:
            		1. 需要注意的 path 目录指定的是 editor 中的 lib/
            */
            $(function() {
                editor = editormd("test-editormd", {
                    width   : "90%",
                    height  : 640,
                    syncScrolling : "single",
                    path    : "editor/lib/",
                    saveHTMLToTextarea : true
                });
            });
            
            /** 获取编辑器的内容 */
            function save() {
            	var markdown = editor.getMarkdown();
            	var HTML = editor.getHTML();                
                
            	console.log(markdown);
            	console.log(HTML);
            	
            	$.ajax({
            		url:"markdownDemo",
            		type:"post",
            		data:{
            			"markdown":markdown,
            			"HTML":HTML
            		},
            		success:function(data) {
            			alert(data);
            		}
            	});
            	
            }
            
        </script>
    </body>
</html>
```

> 编写 servlet 获取值

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		
		String markdown = request.getParameter("markdown");
		String HTML = request.getParameter("HTML");
		
		System.out.println(markdown);
		System.out.println(HTML);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("操作成功!");
		
}
```

> 初始化编辑器的值介绍

| 值                 | 描述                  |
| ------------------ | --------------------- |
| width              | 编辑器的宽            |
| height             | 编辑器的高            |
| syncScrolling      | 同步滚动              |
| path               | editor 中的 lib/ 目录 |
| saveHTMLToTextarea | 是否保存 HTML 数据    |

> 完整版的 html 页面

```html

<!DOCTYPE html>
<html lang="zh">
	<head>
        <meta charset="utf-8" />
        <title>Full example - Editor.md examples</title>
        <link rel="stylesheet" href="editor/examples/css/style.css" />
        <link rel="stylesheet" href="editor/css/editormd.css" />
        <!-- 导入: editor/examples/css/style.css, editor/css/editormd.css -->
    </head>
    <body>
        <div id="layout">
            <header>
                <h1>完整示例</h1>
                <p>Full example</p>
                <ul style="margin: 10px 0 0 18px;">
                    <li>Enable HTML tags decode</li>
                    <li>Enable TeX, Flowchart, Sequence Diagram, Emoji, FontAwesome, Task lists</li>
                    <li>Enable Image upload</li>
                    <li>Enable [TOCM], Search Replace, Code fold</li>
                </ul>            
            </header>
            <div class="btns">
                <button id="goto-line-btn">Goto line 90</button>
                <button id="show-btn">Show editor</button>
                <button id="hide-btn">Hide editor</button>
                <button id="get-md-btn">Get Markdown</button>
                <button id="get-html-btn">Get HTML</button>
                <button id="watch-btn">Watch</button>
                <button id="unwatch-btn">Unwatch</button>
                <button id="preview-btn">Preview HTML (Press Shift + ESC cancel)</button>
                <button id="fullscreen-btn">Fullscreen (Press ESC cancel)</button>
                <button id="show-toolbar-btn">Show toolbar</button>
                <button id="close-toolbar-btn">Hide toolbar</button>
                <button id="toc-menu-btn">ToC Dropdown menu</button>
                <button id="toc-default-btn">ToC default</button>
            </div>
            <div id="test-editormd"></div>
        </div>
        <script src="editor/examples/js/jquery.min.js"></script>
        <script src="editor/editormd.min.js"></script>
        <script type="text/javascript">
            var testEditor;
            
            $(function() {
                
                $.get('editor/examples/test.md', function(md){
                    testEditor = editormd("test-editormd", {
                        width: "90%",
                        height: 740,
                        path : 'editor/lib/',
                        theme : "dark",
                        previewTheme : "dark",
                        editorTheme : "pastel-on-dark",
                        markdown : md,
                        codeFold : true,
                        //syncScrolling : false,
                        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
                        searchReplace : true,
                        //watch : false,                // 关闭实时预览
                        htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
                        //toolbar  : false,             //关闭工具栏
                        //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                        emoji : true,
                        taskList : true,
                        tocm            : true,         // Using [TOCM]
                        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                        flowChart : true,             // 开启流程图支持，默认关闭
                        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                        imageUpload : true,
                        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                        // imageUploadURL : "",
                        onload : function() {
                            console.log('onload', this);
                            //this.fullscreen();
                            //this.unwatch();
                            //this.watch().fullscreen();

                            //this.setMarkdown("#PHP");
                            //this.width("100%");
                            //this.height(480);
                            //this.resize("100%", 640);
                        }
                    });
                });
                
                $("#goto-line-btn").bind("click", function(){
                    testEditor.gotoLine(90);
                });
                
                $("#show-btn").bind('click', function(){
                    testEditor.show();
                });
                
                $("#hide-btn").bind('click', function(){
                    testEditor.hide();
                });
                
                $("#get-md-btn").bind('click', function(){
                    alert(testEditor.getMarkdown());
                });
                
                $("#get-html-btn").bind('click', function() {
                    alert(testEditor.getHTML());
                });                
                
                $("#watch-btn").bind('click', function() {
                    testEditor.watch();
                });                 
                
                $("#unwatch-btn").bind('click', function() {
                    testEditor.unwatch();
                });              
                
                $("#preview-btn").bind('click', function() {
                    testEditor.previewing();
                });
                
                $("#fullscreen-btn").bind('click', function() {
                    testEditor.fullscreen();
                });
                
                $("#show-toolbar-btn").bind('click', function() {
                    testEditor.showToolbar();
                });
                
                $("#close-toolbar-btn").bind('click', function() {
                    testEditor.hideToolbar();
                });
                
                $("#toc-menu-btn").click(function(){
                    testEditor.config({
                        tocDropdown   : true,
                        tocTitle      : "目录 Table of Contents",
                    });
                });
                
                $("#toc-default-btn").click(function() {
                    testEditor.config("tocDropdown", false);
                });
            });
        </script>
    </body>
</html>
```

