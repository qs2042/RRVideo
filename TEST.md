
# 电商模式
```
B2B     Business to Business    商家与商家建立的商业关系, 如阿里巴巴
B2C     Business to Consumer    供应商直接将商品卖给用户, 商对客模式, 直接面向消费者销售产品和服务
                                如苏宁易购, 京东, 天猫, 小米商城, PDD(即商业零售)
C2B     Customer to Business    即消费者对企业, 先有消费者需求产生, 而后有企业按需求组织生产
C2C     Customer to Consumer    客户之间自己把东西放到网上去卖, 如淘宝和咸鱼
O2O     Online To Offline       将线下商务与互联网结合在一起, 让互联网成为线下交易的前台
                                线上快速支付, 线下优质服务, 如饿了么, 美团, 京东到家
```

# 微服务 && 集群,分布式节点
```
[微服务]
微服务架构的风格, 就像是把一个单独的应用程序开发为一套小服务
每个小服务运行在自己的进程中, 并使用轻量级机制通信(通常是HTTP API)
这些服务围绕业务能力来构建, 并通过完全自动化部署机制来独立部署
这些服务使用不同的编程语言书写, 以及不同数据储存技术, 并保持最低限度的集中式管理
人话: 拒绝大型单体应用, 基于业务边界进行服务微化拆分, 各个服务独立部署运行

[集群&分布式&节点]
集群是个物理形态, 分布式是个工作方式
只要是一堆机器, 就可以叫集群, 他们是不是一起协作着干活, 这个谁也不知道
其他人定义:
    <分布式系统原理与规范>
    "分布式系统是若干独立计算机的集合, 这些计算机对于用户来说就像单个相关系统"
    分布式系统(Distributed System)是建立在网络之上的软件系统
分布式是指将不同业务分布在不同的地方
集群指的是将几台服务器集中在一起, 实现同一业务
例子: 京东就是一个分布式系统, 众多业务运行在不同的机器上, 所有业务构成一个大型的业务集群
      而每一个小的业务(例如用户系统), 当访问压力大的时候, 一台服务器是不够的
      这时应该将用户系统部署到多个服务器上, 也就是每一个业务系统也可以做集群化
分布式中的每一个节点, 都可以做集群, 而集群不一定就是分布式的
节点: 集群中的一个服务器
```

# 远程调用 && 负载均衡 && 服务注册/发现, 注册/配置中心
```
[远程调用]
在分布式系统中, 各个服务可能处于不同主机, 但是服务之间不可避免的需要互相调用
而这就被称为远程调用
远程调用的方式有好几种, 这里就不多阐述
SpringCloud中使用HTTP+JSON的方式完成远程调用

[负载均衡]
分布式系统中, A服务需要调用B服务, B服务在多台机器中都存在, A调用任意一个服务器均可完成功能
为了使每一个服务器都不要太忙或者太闲, 我们需要负载均衡的调用每一个服务器, 以此提升网站的健壮性
场景的负载均衡算法:
    1.轮询, 为第一个请求选择健康池中的第一个后端服务器, 然后按照顺序往后依次选择, 直到最后一个, 然后循环
    2.最小连接, 优先选择连接数最少, 也就是压力最小的后端服务器, 在会话较长的情况下可以考虑采取这种方式
    3.散列, 根据请求源的IP的散列(hash), 来选择要转发的服务器
        这种方式可以一定程度上保证特定用户能连接到相同的服务器
        如果应用需要处理状态要求用户连接到和之前相同的服务器, 那么就可以用这种
假设A服务有一个, B服务有三个
轮询方式: 第一次B1, 第二次B2, 第三次B3, 第四次B1
最小连接: B1, B2, B3哪个连接数最少就选择哪个
散列方式: ...

[服务注册/发现 & 注册中心]
A服务需要调用B服务, 但是A服务并不知道B服务当前在哪几台服务器有
也不知道那些服务是正常的, 那些那些服务已经下线
解决这个问题的方法之一: 引入注册中心

注册中心
↓
A1服务, A2服务, A3服务
B1服务, B2服务, B3服务
C1服务, C2服务, C3服务

服务注册: B服务上线后, 告知注册中心我上线了
服务发现: A服务想调用B服务, 去注册中心询问B服务在那台机器


[配置中心]
每一个服务最终都有大量的配置, 并且每个服务都可能部署在多台机器上
但是有时候需要经常变更配置, 我们可以让每个服务正在配置中心获取自己的配置

配置中心用来集中管理微服务的配置信息


[服务熔断 & 服务降级]
在微服务架构中, 微服务之间通过网络进行通信, 存在互相依赖, 当其中一个服务不可用时
就有可能会造成雪崩效应, 要防止这样子的情况出现, 必须要有容错机制来保护服务

A服务(订单) -> B服务(库存) -> C服务(支付)
假设B服务出现宕机, A服务就得等待B服务
假设C服务出现宕机, A服务和B服务就得等待C服务
假设BC服务都宕机了, A服务就得等待BC服务好

重点是宕机的过程中, 假设有更多的请求发过来, 那么多条请求就会堆积在一起, 造成请求积压

服务熔断    设置服务的超时时间, 当被调用的服务经常失败到达某个阈值, 就可以开启断路保护机制
            后来的请求不再去调用这个服务, 本地直接返回默认的数据
服务降级    在运维期间, 当系统处于高峰期, 系统资源紧缺, 我们可以让非核心业务降级运行
            这里的降级指: 某些服务不处理, 或简单处理(抛异常, 返回NULL, 调用Mock数据, 调用Fallback处理逻辑)


[API网关]
在微服务架构中, API Gateway作为整体架构的重要组件
它抽象了微服务都需要的公共功能
同时提供了客户端: 负载均衡, 服务自动熔断, 灰度发布, 统一认证, 限流监控, 日志统计, 等丰富功能

client
↓
API Gateway(serviceId: login-repo)
↓
Service Discovery
↓
login-repo-A(192.168.1.4:8080)
login-repo-B(192.168.1.4:8081)
login-repo-C(192.168.1.4:8082)

请求
↓
网关(负载均衡, 服务自动熔断, 灰度发布, 统一认证, 限流监控, 日志统计, 等丰富功能)
↓
...



```

# 流程图
```
Android, Windows
↓
防火墙, WAF, CDN
↓
Nginx集群
↓
Webflux网关
SpringClound Gateway
SpringClound Gateway
SpringClound Gateway
1.动态路由 -> 指定服务, 根据状态进行负载均衡(ribbon|loadBlance) 或 熔断降级(sentinel)
2.认证授权 -> 判断是否合法
3.令牌限流 -> 限制流量
4.认证中心 -> OAuth2.0
↓
业务集群 -> openfeign或feign
数据库   -> mysql(masterSlave)
缓存     -> redis(sentinel+shard)
消息队列 -> rabbitMQ(mirror)
全文检索 -> ElasticSearch(shard)
对象储存 -> OSS
日志储存 -> ELK(Kibana, ES, LogStash)
注册中心 -> nacos
配置中心 -> nacos
服务追踪 -> sleuth, zipkin, metncs -> prometheus -> grafana, altermanager
```

# 流程图2
```
admin-vue, shop-vue, app, 小程序
↓
API网关(限流, 鉴权, 熔断降级, 过滤, 路由, 负载均衡)
↓
业务微服务集群(商品服务, 支付服务, 优惠服务, 用户服务, 仓储服务, 秒杀服务, 订单服务, 检索服务, 中央认证服务, 购物车服务, 后台管理)
第三方服务(物流, 短信, 金融, 身份认证)

[数据]
redis, shardingSphere(mysql), rabbitMQ, elastic, OOS

[服务治理]
Nacos注册中心/配置中心
Seata分布式事务
Sentinel服务容错
SpringCloudAlibaba

Feign远程调用/负载均衡
Gateway网关
sleuth服务追踪
Zipkin可视化追踪
SpringCloud

[应用监控]
Prometheus
Grafana




```







# 虚拟机(VMware, VirtualBox) && 搭建Linux
```
VMware          ...
VirtualBox      https://www.virtualbox.org/
Tips: 要开启CPU虚拟化


Vagrant https://app.vargantup.com/boxes/search
Tips: 结合VirtualBox可以快速创建一个linux虚拟机
打开Windows CMD, 运行Vagrant init centos/7, 即可初始化一个centos7系统
运行vagrant up即可启动虚拟机, 默认的账号和密码是root + vagrant
vagrant常用命令: https://www.vagrantup.com/docs/cli/init.html
    vagrant shh     自动使用vagrant用户连接虚拟机
    vagrant upload source [destination] [name|id] 上传文件
默认虚拟机的ip地址不是固定的ip
修改vagrantfile中的config.vm.network "private_network", ip: "192.168.56.10"
```

# VirtualBox: 网络地址转换-端口转发
```
windows: mysql=3333, redis=6666, 本体=2222
↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
virtualbox: mysql=3306, redis=6379, 本体=22

使用VirtualBox, 正常是访问不到虚拟机里的端口的, 需要做一个端口转发
比如在外界, 访问我的电脑端口3333, 那么就转发给虚拟机里的端口3306
麻烦的地方在于, 每当虚拟机开一个服务, 就需要在设置里进行一次端口绑定进行转发
或者可以修改Vagrantfile文件, 找到config.vm.network "private_network" ...这一行
cmd> ipconfig
查看以太网适配器VirtualBox Host-Only Network里的ipv4地址
根据地址修改为: config.vm.network "private_network", ip: "..."

但是使用VMWare就可以直接访问

弄完之后记得互相ping一下ip地址
cmd> ipconfig
这个命令可以查看虚拟机的ip和本机的ip
cmd> ping 虚拟机ip
linux> ping 本机ip

```







# Maven
```
# 下载
主页        https://maven.apache.org/index.html
下载        https://maven.apache.org/download.cgi
历史下载    https://dlcdn.apache.org/maven/

# 安装 && 环境变量
解压文件后, 设置环境变量
我这里解压安装到F:\program\apache-maven-3.8.6
于是我添加了环境变量F:\program\apache-maven-3.8.6\bin

# 查看是否配置成功
cmd> mvn -version

# 更改镜像源
[F:\program\apache-maven-3.6.3\conf]
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun.</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>

# 配置jdk1.8编译项目
[F:\program\apache-maven-3.6.3\conf]
<profile>
    <id>jdk-1.8</id>
    <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
    </activation>

    <properties>
        <maven.compliance.source>1.8</maven.compliance.source>
        <maven.compliance.target>1.8</maven.compliance.target>
        <maven.compliance.compilerVersion>1.8</maven.compliance.compilerVersion>
    </properties>
</profile>

# 配置IDEA
Settings -> Build, Execution, Deployment -> Build Tools -> Maven

修改为以下配置
name        F:\program\apache-maven-3.6.3
settgins    F:\program\apache-maven-3.6.3\conf\settings.xml
repository  F:\program\apache-maven-3.6.3\repository


Tips: 之前的项目一直都是用的IDEA提供的MAVEN(3.6.3)
    name: Bundled (Maven 3)
    settgins: C:\Users\Administrator\.m2\settings.xml
    repository: C:\Users\Administrator\.m2\repository


```

# Git
```
https://git-scm.com

右键进入git bash

# 配置用户名
gitBash> git config --global user.name "halfRain"

# 配置邮箱
gitBash> git config -- global user.email "2042136767@qq.com"

# 配置ssh免密连接(生成id_rsa文件)
gitBash> ssh-keygen -t rsa -C "2042136767@qq.com"

# 配置ssh免密连接(查看id_rsa文件)
gitBash> cat ~/.ssh/id_rsa.pub

# 配置ssh免密连接
https://github.com/settings/keys
将id_rsq文件里的内容复制到SSH keys里, 这里可以用github, 也可以用gitee

# 测试是否连接成功
gitBash> ssh -T git@github.com


# IDEA && Git
File -> New -> Project from version Control
URL: https://github.com/qs2042/RRVideo.git

Tips: commit and push提交时, 要将https改为ssh方式
https:  https://github.com/qs2042/RRVideo.git
ssh:    git@github.com:qs2042/RRVideo.git
```

# Docker介绍
```
文档地址    https://docs.docker.com/install/linux/docker-ce/centos/
镜像仓库    https://hub.docker.com/
介绍        
            Docker基于镜像, 是一种虚拟化容器技术, 可以秒级启动各种容器
            每一种容器都是一个完整的运行环境, 容器之间互相隔离
版本
            Docker Engine - Community   社区版(免费)
            Docker Enterprise           商业版(收费)
区别
            安装linux -> 部署nginx, mysql, redis...
            安装linux -> 部署docker

端口映射
            Windows
                虚拟机: Linux
                    Docker
                        容器(mysql) 3306   -p 2042:3306
                        容器(redis) 6379   -p 2043:6379
                        容器(nginx) 80     -p 4244:80
                mysql 3306
                redis 6666 
                nginx 80
            
            windows: 192.168.0.101
            虚拟机:  192.168.126.1

            192.168.126.1:2042 -> mysql
            192.168.126.1:2043 -> redis
            192.168.126.1:80   -> nginx
```

# Docker命令(初始化 && 安装, 卸载 && 启动, 停止)
```
# 切换到root账户下
linux> su (或者su root)

# 设置仓库(我们需要yum-utils的yum-config-manager.)(但是device mapper 存储驱动程序需要 device-mapper-persistent-data 和 lvm2)
linux> sudo yum install -y yum-utils device-mapper-persistent-data lvm2

# 设置稳定的仓库(官方源)(阿里云)(清华大学源)
linux> sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
linux> sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
linux> sudo yum-config-manager --add-repo https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/centos/docker-ce.repo

# 安装 Docker Engine-Community(安装最新版本的 Docker Engine-Community 和 containerd
linux> sudo yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin

# 卸载 Docker
linux> sudo yum remove docker docker-client docker-client-latest docker-common \
       docker-latest docker-latest-logrotate docker-logrotate docker-engine

# 启动docker
linux> sudo systemctl start docker

# 停止docker
linux> TODO ...
```

# Docker命令(Other)
```
# 设置docker开启自启
linux> sudo systemctl enable docker

# 查看docker版本
linux> docker -v
```

# Docker命令(容器)
```
# 查看docker的镜像
linux> docker images

# 安装一个容器
linux> docker pull [imageName]              # 默认最新版本
linux> docker pull [imageName]:[version]    # 安装指定版本

# 重启一个容器
linux> docker restart [name]

# 停止运行一个或多个容器
linux> docker stop [CONTAINER ID | NAME]...

# 卸载一个或多个容器
linux> docker rm [CONTAINER ID | NAME]...

# 启动一个容器
# -p 端口映射: 将容器中的端口映射到Linux的端口(Linux端口: 容器端口)
# -v 目录挂载: 将容器中的文件挂载到Linux里, 这样子修改容器中的文件, 就不需要一个一个进到容器中了
# -e 启动容器的时候给mysql设置一些参数
# -d [REPOSITORY]:[TAG] 以后台方式运行
# 运行命令后会得到一行不明所以的东西b034d755ad7b2eedeea22427808e75fad49d262622a1b678939c750326b8a10a, 说明就启动成功了
linux> docker run -p 3306:3306 --name mysql --privileged \
-v /home/admin/mydata/mysql/conf:/var/etc \
-v /home/admin/mydata/mysql/log:/var/log/mysql \
-v /home/admin/mydata/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql


# 查看docker运行中的容器
linux> docker ps
linux> docker ps -a
linux> docker container ls -a

# 查看容器报错日志
linux> docker logs [TagName]

# 进入容器
linux> docker exec -it [CONTAINER ID] bash
# 退出容器
linux> exit

# 设置容器自启
linux> docker update [name] --restart=always




```

# Docker(Mysql)
```
linux> docker pull mysql

linux> docker run -p 3306:3306 --name mysql --privileged \
-v /home/admin/mydata/mysql/conf:/var/etc \
-v /home/admin/mydata/mysql/log:/var/log/mysql \
-v /home/admin/mydata/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql

linux> docker exec -it [CONTAINER ID] bash
mysql> create user 'admin'@'%' identified with mysql_native_password BY 'admin';
mysql> grant all privileges on *.* to 'admin'@'%';


# 修改mysql配置
linux> cd mydata/mysql/conf
linux> vi my.cnf
linux -> vi> i
linux -> vi> 复制粘贴下面的配置
linux -> vi> :wq
[client]
default-character-set=utf8
[mysql]
default-charcter-set=utf8
[mysqld]
init_connect="SET collation_connection=utf8_unicode_ci"
init_connect="SET NAMES utf8"
character=set=server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve



```

# Docker(Redis)
```
# 拉取image
linux> docker pull redis

# 提前创建mydata/redis/conf目录
# (因为redis的etc/redis/目录下是没有redis.conf文件的, 所以就会出现挂载目录后, linux会以为redis.conf是一个文件夹, 而不是一个文件)
linux> mkdir -p /mydata/redis/conf
linux> cd mydata/redis/conf
linux> touch redis.conf
linux> docker run -p 6379:6379 --name redis \
-v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf

# 进入redis客户端进行测试
linux> docker exec -it redis redis-cli
redis-cli> set qq 2042136767
redis-cli> get qq

# 设置redis持久化(新版本默认持久化, 无需设置)
linux> cd mydata/redis/conf
linux> vi redis.conf
linux> 按下i键
linux> 输入appendonly yes
linux> 然后按下exit键
linux> 输入:wq, 写入并退出


```

# 部署后台管理(后端和前端)
```
git> git clone https://github.com/renrenio/renren-fast-vue.git
git> git clone https://gitee.com/renrenio/renren-fast
Tips: 拷贝下来后, 将文件里的.git文件都删除掉



后端: renren-fast
将renren-fast文件放入rrvideo项目中
↓
接着在聚合pom文件中加入
<modules>
    ...
    <module>renren-fast</module>
</modules>
↓
Alt+Ctrl+Shift看一下modules里的renren-fast是不是指定java1.8版本的
↓
配置据库
mysql> create database rrvideo_admin;
mysql> user rrvideo_admin;
将renren-fast/db/mysql.sql中的sql全部都执行起来
↓
修改数据源
renren-fast/src/main/resources/application.yml
可以看到profiles环境是dev, 那么转到application-dev.yml
把数据源修改为自己的




前端: renren-fast-vue
部署nodeJS && 淘宝镜像源
安装nodeJS
cmd> npm -v # 查看是否安装成功
cmd> npm config set registry http://registry.npm.taobao.org
↓
vscode打开项目 -> 新建终端 -> npm install -> npm run dev
报错的话就更换镜像源: 
    0.搜索powershell, 使用命令查看权限: get-ExecutionPolicy
        默认是Restricted, 将它改为RemoteSigned
        set-ExecutionPolicy RemoteSigned
    2.npm rebuild node-sass
    1.npm install chromedriver --chromedriver_cdnurl=http://cdn.npm.taobao.org/dist/chromedriver

```

# 部署代码生成器
```
# 拉取项目代码
git> git clone https://gitee.com/renrenio/renren-generator.git
Tips: 删除.git文件

# 项目添加进rrvideo
[RRVideo/pom.xml]
<modules>
    ...
    <module>renren-fast</module>
</modules>

# 指定项目Java版本
Alt+Ctrl+Shift看一下modules里的renren-fast是不是指定java1.8版本的

# 修改数据源
进到application.yml里
将数据源改为jdbc:mysql://192.168.126.128:3306/rrvideo_pms
账号密码: root

# 修改自动生成的配置
进到generator.proerties里
mainPath=com.qing
package=com.qing.rrvideo
moduleName=product

author=halfRain
email=2042136767@qq.com
tablePrefix=pms_

# 运行自动生成代码网站
接着运行RenrenApplication.java
打开http://localhost, 就可以访问代码生成器页面
选择全部表, 按下生成代码, 会把zip文件下载到本地

# 将自动生成的代码进行移植
将zip的文件解压并复制粘贴到rrvideo-product项目里
此时项目会爆红依赖, 有mybatis-plus这种需要添加依赖的
和PageUtils.java, Query.java这种需要路径导入的
前者添加pom.xml依赖
后者可在renren-fast项目中找到
(renren-fast/src/main/java/io/renren/common/utils)

# 生成一个微服务通用的依赖
New -> Module -> Maven
↓
[rrvideo-common/pom.xml]
↓
<description>每一个微服务公共的依赖, bean, 工具类等</description>

# 在微服务product中添加通用依赖
[rrvideo-product/pom.xml]
<dependency>
    <groupId>com.qing.rrvideo</groupId>
    <artifactId>rrvideo-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

# 添加依赖
[rrvideo-common/pom.xml]
<!-- mybatis-plus -->
<!-- lombok -->
<!-- httpcomponent -->
<!-- commons-lang -->
...


# 移植common文件(先不用这种省事的方法)
将renren-fast/src/main/java/io/renren/的common文件
移植到rrvideo-common/src/main/java/com/qing/common

# 移植common文件
查看rrvideo-product/src/main/java/com.qing.rrvideo.product文件夹中的
controller, dao, entity, service
这些文件里的.java文件, 看看缺少什么依赖, 就进行补充
如果是缺少mybatis这类依赖, 那么就在[rrvideo-common/pom.xml]中进行导入
如果是缺少renren自己写的包, 那么就到renren-fast项目中去找, 然后将包复制粘贴到rrvideo-common项目中


```

