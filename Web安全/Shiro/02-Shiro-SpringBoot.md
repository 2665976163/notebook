> ## 02 Shiro SpringBoot.md

> 导入`pom`依赖

```xml
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.4.0</version>
</dependency>
```

> 添加`shiro`配置

```java
https://blog.csdn.net/weixin_38132621/article/details/80216056
```

> 在`realm`中获取`session`

```java
SecurityUtils.getSubject().getSession();

---
    
SecurityUtils.getSubject().logout();
```

> shiro 配置加密方式

```java
{
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    matcher.setHashAlgorithmName("MD5");
    matcher.setHashIterations(1314);
    setCredentialsMatcher(matcher);
}

@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("开始执行身份认证");
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        
    User user = userMapper.selectOne(queryWrapper);
    if (null == user) {
        throw new AccountException("用户名不正确");
    } else if (!UserStatuEnum.OK.getCode().equals(user.getStatu())){
        throw new AccountException("用户被状态波动");
    }
		
	ByteSource credentialsSalt = ByteSource.Util.bytes(String.valueOf(user.getId()));
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
    	user.getName(),
        user.getPassword(),
        credentialsSalt,
        getName()
	);
    return authenticationInfo;
}
```

> shiro 生成盐值密码

```java
String hashAlgorithmName = "MD5";
Object crdentials = "123456";
Object salt = String.valueOf(1);
int hashIterations = 1314;
Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
System.out.println(">>"+crdentials+">>"+hashAlgorithmName+">>"+salt+">"+hashIterations+">" + result + ":" + salt);
```

