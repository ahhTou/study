## 什么是Nginx？



## 反向代理

### 什么是正向代理

在客户端(浏览器)配置代理服务器，通过代理服务器进行互联网访问。

### 什么是反向代理

我们只需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器获取数据
后，在返回给客户端，此时反向代理服务器和目标服务器对外就是一个服务器， 暴露的是
代理服务器地址，隐藏了真实服务器IP地址。