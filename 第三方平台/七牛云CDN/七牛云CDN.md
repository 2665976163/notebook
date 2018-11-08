> ### 七牛云CDN

1. [七牛云官网](https://portal.qiniu.com) 下载官方 [SDK](https://developer.qiniu.com/sdk)
2. 在官网 控制台创建 CDN 存储空间
3. 将 SDK jar 包导入完成后找到 : java-sdk-X.x.xx\examples\upload.java

> 在官网 控制台创建 CDN 存储空间

![createStorageSpace.png](img/createStorageSpace.png)

> 将 SDK jar 包导入完成后找到 : java-sdk-X.x.xx\examples\upload.java

> examples 下定义了很多操作的 案例这里我只试一个上传 : 需要更改的地方有

1. String ACCESS_KEY = "Access_Key";
2. String SECRET_KEY = "Secret_Key";
3. String bucketname = "Bucket_Name"; 之前创建的存储空间
4.  String key = "my-java.png"; 上传到七牛后保存的文件名
5. String FilePath = "/.../..."; 上传文件的路径
6. 执行 main 方法 OK

![ak-sk](img/AK-SK.png)
