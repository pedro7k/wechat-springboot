## 这是什么？

这是我们` JAVA`程序设计课要求实现的大作业，课题是任选的，我们两人开发了这款微信小程序-SCU约球，实现基础的发起活动，签到活动功能。
在这个版本中我们使用了`SpringBoot+mybatis`框架进行开发


## 简介

代码分为两个部分，一个是微信小程序的发布代码，第二个是部署于服务器上的`Java`代码。

- 微信小程序文件及文件夹主要组成
    - `image` 存放图片
    - `pages` 各个界面
    - `util.js` 获取日期时间
    - `app.js` 每次进入小程序时调用
    - `app.json` 注册每个页面
- Java代码组成
    分为了controller-service-repository三层架构
    - com.pedro.controller
        处理来自客户端的不同请求
    - com.pedro.service
        对请求进行分发处理
    - com.pedro.repository
        连接数据库，处理数据
    
## Demo

