> #### SpringMVC view

> 自定义视图

1. 定义类实现 View 接口
2. 在 SpringMVC 配置文件中配置`BeanNameViewResolver`
3. 编写 @RequestMapping 处理请求

```java
/**
 * @Component 放入 IOC 容器中
 */
@Component
public class HelloView implements View {

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter().write("<h1>success</h1>");
	}

}
```

> 在 SpringMVC 配置文件中配置`BeanNameViewResolver`

```xml
<!-- 配置 BeanNameViewResolver 视图解析器 : 使用视图的名字来解析视图 -->
<!-- 通过 order 属性来定义视图解析的优先级, order 值越小优先级越高  -->
<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
    <property name="order" value="110"></property>
</bean>
```

> 编写 @RequestMapping 处理请求

```java
@RequestMapping("helloView")
public String helloView () {
    System.out.println("com.znsd.springmvc.handlers.UpdateStudent.helloView()");
    return "helloView";
}
```


> 直接转发到目标页面

```html
<mvc:view-controller path="success" view-name="success"/>
<!-- path: 请求 , view-name: 视图名 -->
<mvc:annotation-driven></mvc:annotation-driven>
```

> 处理结果的方式

| 使用            | 描述             |
| --------------- | ---------------- |
| `forward:`view  | 转发到目标页面   |
| `redirect`:view | 重定向到目标页面 |

> #### 自定义类型转换器 String :

1. 定义类实现 : Converter<`原类型`, `目标类型`>
2. 在 SpringMVC 配置文件中配置

```java
/**
 * Converter<原类型, 目标类型>
 * 原类型数据格式: id`name`gender`age
 */
@Component
public class StudentConverter implements Converter<String, Student> {

	@Override
	public Student convert(String source) {
		if (source == null) return null;

		String[] split = source.split("`");

		if (split == null || split.length != 4) return null;

		Integer id = Integer.valueOf(split[0]);
		String name = split[1];
		String gender = split[2];
		Integer age = Integer.valueOf(split[3]);
		Student student = new Student(id, name, gender, age);

		System.out.println(source + ":convert:" + student);

		return student;
	}

}
```

```xml
<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>

<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
	<property name="converters">
		<set>
			<ref bean="studentConverter"/>
		</set>
	</property>
</bean>
```

1. 配置`ConversionServiceFactoryBean` 可以使用自定义的类型转换器
2. 配置`FormattingConversionServiceFactoryBean`可以使用自定义的类型转换器 和 字段格式化
