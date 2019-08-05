## IO 操作一行文件

> ### 读取文件

```java
BufferedReader input = new BufferedReader(new  InputStreamReader(new FileInputStream(new File("F:/file.txt"))));  
StringBuilder builder = new StringBuilder();
String line = null;  
while((line = input.readLine()) != null) {  
    builder.append(line);
    builder.append(String.format("%n"));
}  
input.close();
System.out.println(builder.toString());
```

> ### 写出文件

```java
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("F:/OutPut"))));   
List<String> list = new ArrayList<String>();
for (String context : list) {
    output.write(String.format("%s%n", context));
}
output.close();
```

