![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804471502-31c64115-c49a-4d76-92e0-90e9ae543b32.png)

# 认识Spring Boot
我们来看看官方是如何介绍的：

[https://docs.spring.io/spring-boot/index.html](https://docs.spring.io/spring-boot/index.html)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728875326069-8146a95b-569b-4338-8d28-696fcb647ec8.png)

翻译：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728893860338-bce86e2c-719d-49fe-9932-419fe1e50fb1.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804492457-0c89e835-316b-4ea3-8bc1-4e0245c50c3d.png)



Spring Boot倡导`约定优于配置`，将`简化开发`发挥到极致。使用Spring Boot框架可以快速构建Spring应用，再也不需要`大量的繁琐的`的各种配置。Spring Boot框架设计的目标是：程序员关注业务逻辑就行了，环境方面的事儿交给Spring Boot就行。



Spring Boot特性：

1. 快速创建独立的Spring应用程序。（Spring支持的SpringBoot都支持，也就是说SpringBoot全方位支持IoC，AOP等）
2. 嵌入式的Tomcat、Jetty、Undertow容器。（web服务器本身就是几个jar包，Spring Boot框架自动嵌入了。）
3. 需要什么功能时只需要引入对应的starter启动器即可。（启动器可以自动管理这个功能相关的依赖，自动管理依赖版本的控制）
4. 尽最大努力，最大可能的自动配置Spring应用和第三方库。（例如：如果要进行事务的控制，不用做任何事务相关的配置，只需要在service类上添加@Transactional注解即可。）
5. 没有代码生成，没有XML配置。（Spring Boot的应用程序在启动后不会动态地创建新的Java类，所有逻辑都是在编译期就已经确定好的）
6. 提供了生产监控的支持，例如健康检查，度量信息，跟踪信息，审计信息等。也支持集成外部监控系统。



Spring Boot的开箱即用和约定优于配置：

+ 开箱即用：Spring Boot框架设计得非常便捷，开发者能够在几乎不需要任何复杂的配置的情况下，快速搭建并运行一个功能完备的Spring应用。
+ 约定优于配置：“约定优于配置”（Convention Over Configuration, CoC）是一种软件设计哲学，核心思想是通过提供一组合理的默认行为来减少配置的数量，从而简化开发流程。例如：Spring Boot默认约定了使用某个事务管理器，在事务方面不需要做任何配置，只需要在对应代码的位置上使用`@Transactional`注解即可。



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804513628-aa8a4632-8dbd-48e7-b861-1031f6eeabeb.png)

# 知识储备
学习本套课程的前提是你已经学过了：

+ MyBatis
+ Spring
+ SpringMVC

# 主要版本说明
Spring Boot版本：3.3.5

IDEA版本：2024.2.4

JDK版本：21（Spring Boot3要求JDK的最低版本是JDK17）



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804520945-b2ffed91-1386-4044-8ecb-72c9550b6156.png)

# First Spring Boot
需求：在浏览器上输入请求路径 http://localhost:8080/hello，在浏览器上显示 HelloWorld!

使用**<font style="color:#DF2A3F;">Spring Boot3 开发web应用</font>**，实现步骤如下：

## 第一步：创建一个空的工程，并设置JDK版本21（Spring Boot 3要求JDK最低版本是17）
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726108555353-e1941b34-dc63-4767-954c-4f6e5e3a9aa8.png)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726108673944-7c20a530-a244-4258-a98a-d8ef9c6d04da.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804526644-4c51d00e-391f-4631-bb20-6614b0353249.png)

## 第二步：设置maven
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726109769284-7a5cae72-e931-4fd3-bf1c-c99a37cb4023.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804531985-b030f4e8-5c56-40ea-9e70-2b915e768336.png)

## 第三步：创建一个Maven模块 sb3-01-first-web
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726110490826-f7c563eb-4aff-488e-83dc-8daf82a700e9.png)

## 第四步：打开Spring Boot 3官方文档，按照文档一步一步进行
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726111379585-fd90758d-f6bd-4a71-9ba9-b1549dd18c84.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804539094-1ef98c7f-787e-45bd-8ec3-24b10f0b3ba6.png)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726111411632-0a5cfc79-f222-4395-ae19-35b8d3868f3d.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804546516-062f1c9d-5083-45bb-b53f-a5a2976997e3.png)

## 第五步：要使用Spring Boot 3，需要继承这个开源项目。从官方指导文档中复制以下内容：
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726111736067-221c85d6-e3ce-4d41-9b20-2893705b11de.png)

```xml
<!--继承Spring Boot 3.3.3开源项目-->
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.3.3</version>
</parent>
```

我们开发的每一个项目其实可以看做是 Spring Boot 项目下的子项目。



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804551532-f4cf08f2-8e89-48ab-a28f-5c55182291db.png)

## 第六步：添加Spring Boot的web starter
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726112199621-1d90cda6-1bb5-4c66-ac9f-b0f2bedb87b3.png)



在parent下立即添加如下配置，让Spring Boot项目具备开发web应用的依赖：

```xml
<dependencies>
    <!--引入Spring Boot web启动器依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```



关联的依赖也被引入进来，如下：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726112541453-f0298719-9217-4792-9af5-bb39d6582560.png)

可以看到spring mvc被引入了，tomcat服务器也被引入了。

## 第七步：编写Spring Boot主入口程序
```java
package com.powernode.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804571434-d020e651-43df-4cd7-ae0a-f938ecb04237.png)

## 第八步：编写controller
```java
package com.powernode.springboot3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MyController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }
    
}

```

## 第九步：运行main方法就是启动web容器
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726197869192-497ee49e-fffb-4201-9c20-6caa97823963.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804582667-76ae128e-3a65-4ad3-a995-c2a180eb3cdc.png)

## 第十步：打开浏览器访问
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726113139402-b64d1c16-9055-495c-aa3d-1ef13baf9816.png)

# 便捷的部署方式
## 打jar包运行
Spring Boot提供了打包插件，可以将Spring Boot项目打包为**<font style="color:#DF2A3F;">可执行 jar 包</font>**。Web服务器（Tomcat）也会连同一块打入jar包中。只要电脑上安装了Java的运行环境（JDK），就可以启动Spring Boot项目。

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199029529-6ffc59e1-3d92-4d4e-ab44-31f24cba9585.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804587620-a1e6e4b5-644d-46d1-a293-f40cea183cc4.png)



根据官方文档指导，使用打包功能需要引入以下的插件：

```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```



执行打包命令，生成可执行jar包：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199412578-3892d163-9341-401d-83b5-a4412a1f7bdf.png)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199440187-2e04d480-92c7-44e2-afb4-17c495fdc415.png)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199497215-d3080065-f6dc-4c1d-b37c-4af2393faf58.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804598719-51a94622-a709-41ff-88b9-f0332c6c1435.png)



单独的将这个 jar 包可以拷贝到任何位置运行，通过`java -jar sb3-01-first-web-1.0-SNAPSHOT.jar`命令来启动 Spring Boot 项目：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199690515-f22a7212-d34a-4644-a6cd-141a58ed490c.png)



打开浏览器访问：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1726199728479-c1365c32-65d1-428f-b4fa-3d10f20df2e0.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804602673-3f02c5a8-cc61-4e7c-83a7-4715a5b15800.png)



另外，Spring Boot框架为我们提供了非常灵活的配置，在可执行jar包的同级目录下新建配置文件：application.properties，并配置以下信息：

```properties
server.port=8888
```

重新启动服务器，然后使用新的端口号访问：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1727503630959-3bcc152d-e57d-4723-8630-bc576582e215.png)

## SpringBoot的jar包和普通jar包的区别
Spring Boot 打包成的 JAR 文件与传统的 Java 应用程序中的 JAR 文件相比确实有一些显著的区别，主要体现在`依赖管理`和`可执行性`上。

**依赖管理**：

+ Spring Boot 的 JAR 包通常包含了应用程序运行所需的所有依赖项，也就是说它是一个“fat jar”（胖 JAR 包），这种打包方式使得应用可以独立运行，而不需要外部的类路径或应用服务器上的其他依赖。
+ 普通的 JAR 文件一般只包含一个类库的功能，并且需要依赖于特定的类路径来找到其他的类库或者框架，这些依赖项通常在部署环境中已经存在，比如在一个应用服务器中。

**可执行性**：

+ Spring Boot 的 JAR 文件可以通过直接执行这个 JAR 文件来启动应用程序，也就是说它是一个可执行的 JAR 文件。通过 `java -jar your-application.jar` 命令就可以直接运行应用程序。
+ 而普通的 JAR 文件通常是不可直接执行的，需要通过指定主类（main class）的方式或者其他方式来启动一个应用程序，例如使用 `-cp` 或 `-classpath` 加上类路径以及主类名来执行。

Spring Boot 的这些特性使得部署和运行变得更加简单和方便，特别是在微服务架构中，每个服务都可以被打包成独立的 JAR 文件并部署到任何支持 Java 的地方。



SpringBoot的可执行jar包目录结构：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1729577060207-7c7bbf86-12ee-4ea4-9f0a-fb2d44d5774c.png)

普通jar包的目录结构：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1729576629470-76daa653-7d27-4e33-a1c1-05191c529e6a.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804610429-e111b1bf-c6bd-4c88-a089-3117a184807c.png)

# Spring Boot脚手架
## 什么是脚手架
### 建筑工程中的脚手架
在建筑工程领域，“脚手架”指的是临时性的结构，用于支撑建筑物以及建筑材料，同时为建筑工人提供工作平台。这种脚手架通常是由钢管、扣件、木板和其他配件组成的，可以根据施工需要搭建不同高度和形状的结构。

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728552516494-a4239fdd-4ac0-4dbc-b55c-94eb742a950a.png)

### 软件开发中的脚手架
在软件开发领域，“脚手架”指的是用于快速创建项目基本结构的工具或模板。它帮助开发者初始化项目，设置必要的目录结构、文件模板以及依赖项。 

### Spring Boot脚手架
Spring Boot 脚手架（Scaffold）可以帮助开发者快速搭建一个Spring Boot项目结构，让开发者只专注于业务逻辑的开发，而不是在项目的初始阶段花费大量时间来配置环境或者解决依赖关系。



Spring Boot 脚手架工具存在多种形式，以下是一些常见的 Spring Boot 脚手架工具和方法：

+ **Spring Initializr：**

这是 Spring 官方提供的工具，可以在 [https://start.spring.io](https://start.spring.io) 上找到。它允许开发者选择所需的依赖、Java 版本、构建工具（Maven 或 Gradle）以及其他配置选项来生成一个新的 Spring Boot 项目。

+ **IntelliJ IDEA 内置支持：**

IntelliJ IDEA 集成了 Spring Initializr 的功能，可以在 IDE 内直接创建 Spring Boot 项目。

+ **Start Alibaba Cloud：**

阿里云提供的 Start Alibaba Cloud 增强版工具，除了基本的 Spring Boot 模块外，还集成了阿里云服务和中间件的支持。

+ **JHipster：**

JHipster 是一个流行的脚手架工具，用于生成完整的 Spring Boot 应用程序，包括前端（Angular, React 或 Vue.js）和后端。它还包括用户管理和认证等功能。

+ **Yeoman Generators：**

Yeoman 是一个通用的脚手架工具，它有一个庞大的插件生态系统，其中包括用于生成 Spring Boot 项目的插件。

+ **Bootify：**

Bootify 是另一个用于生成 Spring Boot 应用程序的脚手架工具，提供了一些预定义的应用模板。

+ **Spring Boot CLI：**

Spring Boot CLI 是一个命令行工具，允许用户通过命令行来编写和运行 Spring Boot 应用。

+ **Visual Studio Code 插件：**

Visual Studio Code 社区提供了多个插件，如 Spring Boot Extension Pack，可以帮助开发者生成 Spring Boot 项目的基本结构。

+ **GitHub Gist 和 Bitbucket Templates：**

在 GitHub 和 Bitbucket 上，有很多开发者分享了用于生成 Spring Boot 项目的脚本或模板。

+ **自定义脚手架：**

很多开发者也会根据自己的需求定制自己的脚手架工具，比如使用 Bash 脚本、Gradle 或 Maven 插件等。

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804619800-7a4f8744-5419-474d-a139-2a423b8e40ba.png)

## 使用官方提供的
### 使用官方脚手架生成Spring Boot项目
Spring Initializr：[https://start.spring.io](https://start.spring.io)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728609240280-87eccabc-ab8d-48a4-b9d2-a31dcdad927a.png)



点击“GENERATE”后，生成zip压缩包：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728609413133-c4914cdd-0c38-4c71-ab86-7ba13e3c4dd8.png)

将其解压后的目录结构是一个标准的maven 工程：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728609603462-7f1dd559-7b10-4200-9503-08d76df40ebe.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804625878-bab9de35-8ada-449b-946f-2276fd3da8de.png)

### 将项目放到IDEA当中
接下来将其导入到IDEA当中：直接将解压后的`sb3-02-use-spring-initializr`拷贝到我们新建的空工程`SpringBoot3`下，如图：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728636406330-1f5945e3-d3d7-4c2c-b9e8-dea3be89ccbf.png)

打开IDEA工具，你会看到如下图：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728637409850-6f7b13f1-b98d-4b11-9f17-ba5fac3112f3.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804629435-074a9f0f-b4cb-4d72-8b14-f7e65cafbb17.png)

注意：如果`pom.xml`文件的图标颜色不是蓝色，而是橘色，需要在`pom.xml`文件上右键，选择：add as maven project。这样`pom.xml`文件的图标就会变为蓝色了。

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728643539990-d4e9061c-1e9e-4b3c-a21a-b2604f165f5b.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804633058-3206faea-cbfd-42a7-a25a-514983c15a27.png)

### 脚手架生成的pom.xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
  <!--继承Spring Boot父工程-->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
  <!--自己项目的坐标-->
	<groupId>com.powernode</groupId>
	<artifactId>sb3-02-use-spring-initializr</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>sb3-02-use-spring-initializr</name>
	<description>使用spring官方提供的脚手架构建springboot项目</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
    <!--web起步依赖-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
    <!--单元测试起步依赖-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

  <!--打包插件-->
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
  
</project>
```

可以看到脚手架生成的`pom.xml`文件的内容和我们手动创建Spring Boot项目的`pom.xml`文件是一样的。



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804642699-99557f0b-7c8c-4333-bea2-dba7ec000724.png)

### 脚手架生成的Spring Boot项目的结构
![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728640089701-39f8e3a9-5308-422a-aaf8-af029c6e5dda.png)

请仔细阅读上图来学习Spring Boot项目结构。



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804647206-afaf01f7-c77d-4a7b-aad8-05792838c65d.png)

### 编写controller并测试
新建controller包，并新建HelloController类，如下图：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728643959787-f1691efd-0459-4949-b3f7-79cdc3dd6b6d.png)

**<font style="color:#DF2A3F;">重点：默认情况下，SpringBoot项目只扫描主入口程序所在目录以及子目录，因此创建的Controller类要求放在主入口程序的同级目录下或子目录下。其他位置默认情况下扫描不到。</font>**

启动应用并访问：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728644098279-12a20878-eb1f-4203-b051-d01b95f79c21.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804650811-00d36c96-98a7-4093-b4d4-13f689db90cc.png)

## 使用阿里提供的
阿里巴巴提供的 Spring Boot 项目脚手架服务称为 DragonBoot（也被称为 Alibaba Cloud Spring Boot Initializr）。DragonBoot 基于 Spring Initializr，并在此基础上增加了更多的定制选项，特别是针对阿里巴巴云服务和中间件的支持。脚手架地址：[https://start.aliyun.com/](https://start.aliyun.com/)

当下（2024年）阿里云提供的脚手架使用的版本较低，国内有一些公司在用。如果要求版本较高的，则阿里云脚手架不适用。

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728640969491-cea21c57-bd9a-4695-b215-3c25ec44b19c.png)

阿里提供的脚手架和spring官方脚手架基本上是相同的。不再赘述。点击`获取代码`也会生成zip压缩包：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728641066809-6d6f0a30-c2a2-4dad-83a9-628d8962c42e.png)

解压：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728643375176-6bad6e0e-c9cf-4439-850c-c1de7ee26e2c.png)

和之前操作一样，将其放到IDEA开发环境中：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728643741051-faa4db06-4936-40ef-a0d5-4551ced4c82f.png)



启动应用并访问：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728643772647-264fb406-ae63-44dd-a2eb-24023de8c221.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804656857-0a80c0e6-7c8b-477d-abf7-0c7f9a9e4d11.png)

## 使用IDEA工具的脚手架插件
 IDEA工具自带了Spring Boot脚手架的插件，使用它会更加的方便，让我们来操作一下：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728716111385-646e158d-1e92-422a-b6e3-258d04e36f5e.png)

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728716166927-1dd5e821-aab7-4255-88cf-595f0ce8a1fb.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1730804670317-aa29720e-81cc-48e2-8e20-9129c5f9997b.png)



![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728716199947-7e2949fd-921f-4ee3-9a40-fe47af20199b.png)

编写控制器，启动服务器测试：

![](https://cdn.nlark.com/yuque/0/2024/png/21376908/1728716401724-8808d893-408c-4b1e-9e14-70d96ff461fe.png) 

