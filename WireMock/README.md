WireMock——轻量级HTTP Mock服务器  

WireMock是一个非常轻量级的支持HTTP mock的服务，可以用于单元测试和测试环境模拟。
单元测试可以参考用户手册，这次主要介绍的是完全不需要一行代码的测试环境模拟。

下载standalone.jar
<dependency>
<groupId>com.github.tomakehurst</groupId>
<artifactId>wiremock</artifactId>
<version>1.5.7</version>
<classifier>standalone</classifier>
</dependency>

启动服务器，监听在8080端口
java -jar wiremock-1.5.7-standalone.jar

jinfeng@ubuntu:~/wiremock$ ./startserver.sh 
Created 1 stubRequestHandlers
2013-05-10 11:35:28.128:INFO::Logging to STDERR via wiremock.org.mortbay.log.StdErrLog
2013-05-10 11:35:28.296:INFO::jetty-6.1.x
2013-05-10 11:35:28.338:INFO::Started DelayableSocketConnector@0.0.0.0:8080
jinfeng@ubuntu:~/wiremock$ ./startserver.sh 
Created 1 stubRequestHandlers
2013-05-10 11:35:28.128:INFO::Logging to STDERR via wiremock.org.mortbay.log.StdErrLog
2013-05-10 11:35:28.296:INFO::jetty-6.1.x
2013-05-10 11:35:28.338:INFO::Started DelayableSocketConnector@0.0.0.0:8080

设置映射文件
在mappings目录下，创建请求处理文件mytest-mapping.json

{
    "request": {
        "method": "GET",
        "url": "/api/mytest"
    },
    "response": {
        "status": 200,
        "bodyFileName": "mytest.json",
        "headers": {
            "Content-Type": "application/json",
            "Cache-Control": "max-age=86400"
        }
    }
}

设置结果文件
在__files目录下，创建返回结果文件mytest.json

{
    "working": "YES"
}

实际返回
在浏览器输入地址: localhost:8080/api/mytest，即可返回定义的mytest.json的内容

url与urlPattern
url精确匹配成功的的url request
urlPattern支持正则匹配，例如：
想要访问的url类似：

http://192.168.144.12:8080/NBS?Action=describeVolume&name=myvolumename"
urlPattern定义为：

"urlPattern": "/NBS\\?Action=describeVolume&name=.*"

网络异常fault
返回一个网络异常，定义的mappings文件mappings/fault.json：

{
    "request": {
        "method": "GET",
        "url": "/fault"
    },
    "response": {
        "fault": "EMPTY_RESPONSE"
    }
}
其中，fault有3个取值

EMPTY_RESPONSE: Return a completely empty response.
MALFORMED_RESPONSE_CHUNK: Send an OK status header, then garbage, then close the connection.
RANDOM_DATA_THEN_CLOSE: Send garbage then close the connection.

服务延时delayed
mappings/delayed.josn
{
    "request": {
        "method": "GET",
        "url": "/delayed"
    },
    "response": {
        "status": 200,
        "fixedDelayMilliseconds": 2000
    }
}

Configuring via JSON
Once the server has started you can give it a spin by setting up a stub mapping via the JSON API:
动态增加请求

curl -X POST --data '{ "request": { "url": "/get/this", "method": "GET" }, "response": { "status": 200, "body": "Here it is!\n" }}' http://localhost:8080/__admin/mappings/new

Then fetching it back:

$ curl http://localhost:8080/get/this
Here it is!


Shutting Down
To shutdown the server, either call WireMock.shutdownServer() or post a request with an empty body to 
http://<host>:<port>/__admin/shutdown.