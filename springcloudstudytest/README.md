# springcloudstudytest
SpringCloud学习代码
包含注册中心,配置中心,生产,消费,网关,客户端熔断器,通用模块

模块端口号：

lich-eureka-server 8888

lich-eureka-provider 8100

lich-eureka-provider2 8101

lich-eureka-consumer 8201

lich-config-server 7000

lich-gateway-zuul 8081

手动刷新配置文件
http://192.168.0.107:8081/refresh

查看加密状态
http://192.168.0.107:7000/encrypt/status

加密（post请求）
http://192.168.0.107:7000/encrypt

解密（post请求）
http://192.168.0.107:7600/decrypt

执行加解密需要下载jce_policy-8.zip 将里面的jar包替换到jdk/jre/lib/security下