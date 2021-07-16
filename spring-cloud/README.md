# shin-cloud study
学习 spring cloud & spring cloud alibaba 微服务常用组件
###目前已实现  
> nocas 多环境切换, 配置中心, 服务注册消费
    
##Nacos 集成文档说明
> 本项目基于 spring boot 2.5.1、spring cloud 2020.0.3、 nacos 2021.1 版本，各位实际集成时请注意版本对应关系。
###其他 boot 版本参照官方版本说明
[nacos 官网github wiki版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

1. 下载 nacos 并启动
    [官方下载地址](https://github.com/alibaba/nacos/releases)
    * 环境配置
        * 64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。
        * 64 bit JDK 1.8+ 
        * Maven 3.2.x+
    * 注意事项
        * nacos 默认为集群模式，需要配置数据库 可以更改为 单机模式
2. 引入版本spring boot、cloud、nacos 依赖包  
    * 注意事项
        * spring cloud 2020 之前的版本使用默认的 ribbon 负载均衡组件, 之后的版本需要单独引入 loadbalancer 组件
        * bootstrap 组件为加载 bootstrap.yml/properties 文件支持. 必须引入
        * nacos配置需要放在bootstrap 配置中, 否则无法生效. 
        * 配置文件加载顺序 bootstrap > application
```    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    <dependencyManagement>
       <dependencies>
           <!--      Spring Cloud 依赖项管理      -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-dependencies</artifactId>
               <version>2020.0.3</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
           <!--      Spring Cloud Alibaba 依赖项管理      -->
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-alibaba-dependencies</artifactId>
               <version>2021.1</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
    </dependencyManagement>

    <dependencies>
        <!--   配置中心依赖     -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <!--  注册服务依赖  -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
<!--  负载均衡组件在 cloud 2020 以后的版本才需要单独加载  -->
        <!--  负载均衡组件  -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>
        <!-- 支持 bootstrap 配置文件 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
    </dependencies>

```