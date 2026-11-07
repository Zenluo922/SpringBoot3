# 第 5 章 Spring Boot web 开发 · 学习笔记（自读版，代替网课）

> 配套讲义（所有截图都在里面）：`笔记/第5章 Spring Boot的web开发.md` —— 本笔记每节标注了讲义对应标题，看不懂的地方去讲义搜标题找截图。
> 按 README 定位：本章 🔥🔥 **只学三块**（web 通用配置 / 静态资源默认规则 / WebMvcConfigurer 手动配置思想）+ **异常处理**（黑马点评"统一异常处理"改造点直接要用的，讲义里它藏在 Thymeleaf 后面，别漏）。
> 总耗时目标：**读 1 小时 + 跟敲 1 小时**。Thymeleaf、内容协商、路径匹配整块跳过（原因见 1.8）。
>
> **本章主旋律（跟 ch4 一句话接上）：WebMvcAutoConfiguration 就是你在 ssm-annotation 手写的 SpringMvcConfig 的自动版。ch4 学的是"自动配置怎么来的"，ch5 全章就是"web 这一个自动配置到底帮我配了什么"。**

---

# Part 1 人话版：先搞懂，再挑着敲

## 1.1 本章地图（讲义：全章目录）

| 讲义大块 | 学法 | 为什么 |
|---------|------|--------|
| web自动配置（依赖传递/原理/逆推prefix） | 只读 30min | ch4 原理的 web 实例，面试素材 |
| WebMvcAutoConfiguration原理 | 只读 15min | 面试"自定义MVC配置"题的理论底 |
| 静态资源处理 + 欢迎页 | 🔫 **跟敲** | 项目里放前端资源、图片都要用 |
| web手动配置（WebMvcConfigurer） | 🔫 **跟敲** | 拦截器/异常/静态资源以后全这么配 |
| server. / multipart. 通用配置 | 🔫 **跟敲** | 改端口、头像上传（黑马点评要用） |
| 异常处理 | 🔫 **跟敲** | 前后端分离统一 JSON 错误响应，项目直接搬 |
| WebJars / 缓存 / 路径匹配 / 内容协商 | 一句话过 | README 明说跳，面试不问 |
| Thymeleaf / 国际化 / 定制容器 | 整块跳 | 你走前后端分离，讲义自己都说"用不上" |

## 1.2 web 自动配置从哪来（讲义：web自动配置的依赖是如何传递的 / 实现原理）

**依赖传递链（三跳）：**

```
spring-boot-starter-web
  └→ spring-boot-starter
       └→ spring-boot-autoconfigure
            └→ META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
               （152 个自动配置类的候选名单）
```

**加载原理链（ch4 已学，30 秒复习）：**

`@SpringBootApplication` → 里面的 `@EnableAutoConfiguration` → 它 `@Import(AutoConfigurationImportSelector.class)` → 这个 Selector 去读 **`.imports` 文件** 拿到候选名单 → 条件注解按需筛选 → 生效的注册进 IoC 容器。

⚠️ 讲义原话要记住：**任何 jar 包（包括第三方的），自动配置列表文件的路径和文件名完全相同**，都是 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`。mybatis-starter 也一样——这就是"第三方也能自动配置"的原理。

**web 相关的自动配置类有 8 个**（在 .imports 名单里都能找到），认 4 个脸熟即可：

| 自动配置类 | 干什么 |
|-----------|--------|
| `DispatcherServletAutoConfiguration` | 自动注册前端控制器（你手写 web.xml 的替代品） |
| `ServletWebServerFactoryAutoConfiguration` | 起内嵌 Tomcat（你配 IDEA Tomcat 的替代品） |
| `WebMvcAutoConfiguration` | Spring MVC 全套默认行为（**本章主角**） |
| `MultipartAutoConfiguration` | 文件上传支持 |

## 1.3 逆推法：怎么知道 yml 里能配什么（讲义：通过web自动配置类逆推web配置的prefix）

ch4 学过：自动配置类上标 `@EnableConfigurationProperties(XxxProperties.class)`，把 yml 的值绑进属性类。**看属性类的 `@ConfigurationProperties(prefix=...)` 就知道前缀**。全章最实用的结论——web 开发只有 **4 个前缀**：

| 前缀 | 属性类 | 管什么 | 常用项 |
|------|--------|--------|--------|
| `spring.mvc.` | WebMvcProperties | Spring MVC 行为 | static-path-pattern、format.date、view.prefix/suffix |
| `spring.web.` | WebProperties | 通用 web 设置 | **resources.add-mappings、resources.static-locations**、resources.cache.period |
| `spring.servlet.multipart.` | MultipartProperties | 文件上传 | **max-file-size（默认1MB⚠️）、max-request-size（默认10MB）** |
| `server.` | ServerProperties | 服务器本身 | **port**、servlet.context-path、error.include-message、tomcat.* |

以后想知道"yml 能不能配 X"：IDEA 里 Ctrl 点进任意自动配置类 → 找 `@EnableConfigurationProperties` → 点进 Properties 类看字段和默认值。**这个方法比背配置项重要。**

## 1.4 WebMvcAutoConfiguration 原理（讲义同名小节；只读，面试底牌）

**生效条件（四个注解，前三个重要）：**

```java
@ConditionalOnWebApplication(type = Type.SERVLET)   // 只在 Servlet web 环境生效
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})  // 类路径有这三个类
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)  // 容器里没有这个 bean 才生效
```

第三个条件是理解 **`@EnableWebMvc`** 的钥匙：
- `@EnableWebMvc` 会向容器注册一个 `WebMvcConfigurationSupport` → 条件不满足 → **Boot 的 MVC 默认配置全部失效**（全面接管，一般不用）。
- 而你自己写一个 `WebMvcConfigurer` 实现类 → 不触发该条件 → **默认配置照常生效，你的配置是"追加扩展"**。

> **一句话对比（面试高频）：实现 WebMvcConfigurer = 在默认配置之上扩展；加 @EnableWebMvc = 抛弃默认配置全面接管。项目里永远用前者。**

**两个内部类别搞混：**
- `WebMvcAutoConfigurationAdapter`——实现了 WebMvcConfigurer 接口，**负责给出默认配置**（静态资源映射、消息转换器等都在它的方法里）；
- `EnableWebMvcConfiguration`——继承 `DelegatingWebMvcConfiguration`，**负责收集容器里所有 WebMvcConfigurer 并执行**。

**为什么"容器里随便丢一个 WebMvcConfigurer 组件就生效"？** 看 `DelegatingWebMvcConfiguration` 源码：它有个方法 `@Autowired void setConfigurers(List<WebMvcConfigurer> configurers)`——**Spring 会把容器里所有 WebMvcConfigurer 实现类收集成 List 注入进来**，再用 `WebMvcConfigurerComposite`（组合模式，GoF 结构型）逐个回调。所以你的配置类只要被 IoC 管理（@Configuration 或 @Bean）就行。

**它还送了两个 Filter（知道名字即可）：** `HiddenHttpMethodFilter`（表单模拟 REST 的 PUT/DELETE）、`FormContentFilter`（让 PUT/DELETE 也能解析表单体）。前后端分离项目用不上。

## 1.5 静态资源默认规则 🔫（讲义：自动配置中的静态资源处理；跟敲①）

**默认规则（背下来）：** 请求先找匹配的控制器方法，**没有控制器接**，才按顺序去这 4 个类路径目录找：

```
classpath:/META-INF/resources/  →  classpath:/resources/  →  classpath:/static/  →  classpath:/public/
```

日常约定俗成只用 **`resources/static/`**（IDEA 里 `src/main/resources/static/`）。

**欢迎页：** 4 个目录任一处放 `index.html`，访问 `/` 自动返回它。多个目录都有时按数组顺序，`META-INF/resources` 优先（实测结论，讲义有截图）。

**favicon：** `favicon.ico` 丢进静态目录即可生效（测试时 Ctrl+F5 强刷，浏览器缓存图标很顽固）。

**默认规则是可以关/改的**（这就是 1.7 手动配置的动机）：
- `spring.web.resources.add-mappings=false` → 关闭全部静态资源映射
- `spring.web.resources.static-locations=classpath:/x1/,classpath:/x2/` → 换存放位置
- `spring.mvc.static-path-pattern=/xxx/**` → 换访问 URL 前缀

## 1.6 web 通用配置 + 文件上传 🔫（讲义：逆推prefix小节；跟敲②）

讲义没给上传 demo，这里补全（黑马点评头像上传的前身）。**⚠️ 最经典的坑：multipart 默认单文件上限只有 1MB，不配就传啥都报错。**

`application.yml`（顺手把端口也改了，演练 README 避坑第 2 条）：

```yaml
server:
  port: 8081        # 改完启动日志看 "Tomcat started on port 8081"

spring:
  servlet:
    multipart:
      max-file-size: 10MB       # 单文件上限，默认 1MB（不配传头像必炸）
      max-request-size: 100MB   # 单次请求总大小上限，默认 10MB
```

上传接口（新项目 sb3-09-web，只加 web 启动器 + Lombok，跟讲义建法一致）：

```java
package com.powernode.sb309web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // UUID 前缀防止重名覆盖
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dir = new File("upload");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // ⚠️ 用绝对路径，相对路径会被解析到 Tomcat 临时目录，找不到文件
        file.transferTo(new File(dir.getAbsolutePath(), fileName));
        return "上传成功：" + fileName;
    }
}
```

Postman 里选 **form-data**，key 填 `file`（类型切 File），选张图发送。验证完把 `max-file-size` 临时改回 `1MB` 再传大图 → 报 `MaxUploadSizeExceededException` → 正好给 1.8 的全局异常处理当素材。

## 1.7 手动配置：WebMvcConfigurer 🔫（讲义：SpringBoot的web手动配置；跟敲③）

**两种等价写法**（都靠"容器里存在 WebMvcConfigurer 组件"生效，原理见 1.4）：

```java
package com.powernode.sb309web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 写法一：配置类实现接口（项目里常用这种，一个类管所有 MVC 定制）
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 请求 /img/** 就去 classpath:/img/ 找
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/img/");
    }
}

/*
// 写法二：@Bean 提供匿名实现（讲义第二种）
@Configuration
public class WebConfig2 {
    @Bean
    public WebMvcConfigurer imgHandler() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/img/**")
                        .addResourceLocations("classpath:/img/");
            }
        };
    }
}
*/
```

**验证两件事：** ① `http://localhost:8081/img/x.png` 能访问（自定义规则生效）；② `static/` 里的图照样能访问（**默认配置没被干掉，是扩展**）。别忘了 `application.yml` 里 1.5 的配置先注释掉，排除干扰。

**这个类的真正意义：** `WebMvcConfigurer` 的方法就是"Spring MVC 所有可定制的点"的清单——`addInterceptors`（拦截器）、`addCorsMappings`（跨域）、`addViewControllers`（路径直跳视图）、`configureMessageConverters`（消息转换器）。**以后想改哪个行为，就重写哪个方法**，黑马点评的登录拦截器就是这个套路（讲义末尾有拦截器完整示例，敲完上面这个再回头看一眼即可）。

## 1.8 异常处理 🔫（讲义：异常处理一章；跟敲④ —— 项目要用的重点）

**优先级一句话：程序员配了 SpringMVC 方案（@ExceptionHandler），SpringBoot 默认方案就让位；没配才走 Boot 默认。**

**① 局部：@ExceptionHandler 写在控制器里** —— 只处理**本控制器**的异常。缺点：每个类都得写一遍，实际不用。

**② 全局：@ControllerAdvice + @ExceptionHandler** —— 前后端分离的标准答案：

```java
package com.powernode.sb309web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice = @ControllerAdvice + @ResponseBody，错误直接返回 JSON（讲义写的是分开两个注解，等价）
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map<String, Object> handleMaxSize(MaxUploadSizeExceededException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "文件太大了，单个文件最大 10MB");
        return result;
    }

    // 兜底：放最后，处理没人认领的异常
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "服务器开小差了：" + e.getMessage());
        return result;
    }
}
```

重启后再传大文件 → 不再是白色错误页，而是你定义的 JSON。**ch6 学完 R 对象后，把这里的 Map 换成 `R.fail(...)`，就是黑马点评的统一异常处理改造点，直接搬。**

**③ Boot 默认方案（只读，知道规则）：** 先找 `templates/error/404.html` 这类精确错误码页 → 再找 `4xx.html`/`5xx.html` 模糊页 → 再找 `error.html` → 都没有就走内置 `/error`。前后端分离用不上（前端自己渲染错误页）。⚠️ Boot 3.3.x 默认只往 Model 放 timestamp/status/error，想要 message 得加 `server.error.include-message=always`。

## 1.9 一句话跳过区（每条留一句，面试万一问到不露怯）

- **静态资源缓存**：Boot 默认响应头带 Last-Modified，资源没变返回 **304**（F12 里见过吧？就是它）；可配 `spring.web.resources.cache.period=3600` 让浏览器强缓存。
- **WebJars**：把 jQuery/vue 这类前端库打成 jar 用 Maven 管，`/webjars/**` → `META-INF/resources/webjars/`。你走 npm/前后端分离，不用。
- **路径匹配**：Boot3 默认 `PathPatternParser`（快），兼容 Ant 风格但 `**` 只能放路径末尾；要老 Ant 语法配 `spring.mvc.pathmatch.matching-strategy=ant_path_matcher`。
- **内容协商**：按请求头 `Accept` 决定返回 JSON 还是 XML（XML 要加 jackson-dataformat-xml 依赖）。前后端分离固定 JSON，用不上。底层靠 `HttpMessageConverter`，认识 `MappingJackson2HttpMessageConverter`（JSON↔对象，@RequestBody/@ResponseBody 幕后工作者）这个名字即可。
- **Thymeleaf**：服务端渲染模板引擎。讲义原话："如果你是做前后端分离的项目，这一章节的内容将用不上。"——整节跳。
- **国际化**：messages_zh_CN.properties + `#{key}`，讲义自己标"了解"。跳。
- **定制容器**：换 jetty = starter 里排除 `spring-boot-starter-tomcat` + 加 `spring-boot-starter-jetty`（原理：`ServletWebServerFactoryAutoConfiguration` 里三个容器工厂靠 `@ConditionalOnClass` 竞争，类路径有谁用谁）。`server.tomcat.max-threads/accept-count` 这类优化配置认识词即可。

---

# Part 2 骨架速查（懂了之后背这个）

## 四前缀速查

```
server.                    端口/容器/错误页      → port, servlet.context-path, error.include-*
spring.mvc.                MVC 行为             → static-path-pattern, pathmatch, format, view.*
spring.web.                通用 web/静态资源     → resources.add-mappings, resources.static-locations
spring.servlet.multipart.  文件上传             → max-file-size(1MB!), max-request-size(10MB)
```

## 静态资源默认规则（白板默写这张）

```
请求 /xx → 有控制器？控制器优先
         → 没有：依次找 META-INF/resources/ → resources/ → static/ → public/
欢迎页：任一目录下的 index.html；favicon.ico 丢静态目录
改法：yml（spring.web.resources.*）或 WebMvcConfigurer（addResourceHandlers），后者是扩展不覆盖
```

## 自定义 MVC 配置（项目模板，直接抄）

```java
@Configuration                     // 千万别加 @EnableWebMvc（会干掉全部默认配置）
public class WebConfig implements WebMvcConfigurer {
    @Override public void addResourceHandlers(ResourceHandlerRegistry r) { ... }   // 静态资源
    // 以后会用到：addInterceptors(拦截器) / addCorsMappings(跨域) / addViewControllers(视图直跳)
}
```

## 对照表：ssm-annotation 手写原型 → Boot 自动配置（面试差异化素材）

| 你在 ssm-annotation 手写的 | Boot 里对应 |
|---------------------------|------------|
| `SpringMvcConfig implements WebMvcConfigurer` | 你的 `WebConfig`（写法一模一样）+ Boot 送的默认版 `WebMvcAutoConfigurationAdapter` |
| 手动注册 DispatcherServlet | `DispatcherServletAutoConfiguration` |
| IDEA 里配 Tomcat / `WebAppInitializer` | `ServletWebServerFactoryAutoConfiguration`（内嵌） |
| Controller 抛异常 → 500 白页 | 你写 `@RestControllerAdvice` 全局接住 |

---

# Part 3 高频面试题（本章相关 5 道，README 清单第 6 题在这里）

### Q1 ⭐⭐⭐⭐ Boot 里怎么自定义 Spring MVC 配置？@EnableWebMvc 和 WebMvcConfigurer 的区别？
> 写一个类实现 `WebMvcConfigurer` 接口、标 @Configuration，重写对应方法（静态资源、拦截器、跨域…）。原理：Boot 的 `DelegatingWebMvcConfiguration` 会 @Autowired 收集容器里**所有** WebMvcConfigurer 组合执行，所以默认配置仍生效，我的配置是扩展。而 `@EnableWebMvc` 会注册 `WebMvcConfigurationSupport`，导致 `WebMvcAutoConfiguration` 的 `@ConditionalOnMissingBean` 不满足——默认配置全部失效、全面接管，一般不用。

### Q2 ⭐⭐⭐ Boot 静态资源默认放哪、访问规则是什么？
> 类路径下 4 个目录：META-INF/resources、resources、static、public（按此顺序），请求先匹配控制器、没有才走静态资源映射；index.html 自动做欢迎页。可用 `spring.web.resources.static-locations` 和 `spring.mvc.static-path-pattern` 修改。

### Q3 ⭐⭐⭐⭐ 前后端分离项目，统一异常处理怎么做？（项目题，结合黑马点评讲）
> `@RestControllerAdvice` + `@ExceptionHandler` 全局捕获，返回统一 JSON（code/msg/data 的 R 对象）；再加一个 Exception.class 兜底方法，防止堆栈信息泄露给前端。配了它之后 Boot 默认的错误页机制就让位了。

### Q4 ⭐⭐ 文件上传怎么配？默认限制多少？
> `spring.servlet.multipart.max-file-size`（默认 **1MB**）和 `max-request-size`（默认 10MB），超限抛 `MaxUploadSizeExceededException`，可在全局异常处理器里单独接住给友好提示。

### Q5 ⭐⭐⭐ Boot 内嵌 Tomcat 是怎么回事？怎么换成 jetty？
> `ServletWebServerFactoryAutoConfiguration` 按类路径条件（@ConditionalOnClass）选择容器工厂，starter-web 默认带 tomcat，所以启动类 main 一跑 `SpringApplication.run` 就起了内嵌 Tomcat，不需要外置容器和 IDEA Tomcat 配置。换 jetty：排除 `spring-boot-starter-tomcat`、引入 `spring-boot-starter-jetty`，条件注解自动切换。

---

# Part 4 跟敲总清单（新建模块 sb3-09-web，web 启动器 + Lombok，共约 50 分钟）

| # | 对应小节 | 敲什么 | 检验 |
|---|---------|--------|------|
| 1 | 1.5 | `static/` 放张图（仓库里 `SpringBoot/resources/img/dog1.jpg` 现成）→ 浏览器访问；再加 `index.html` 访问 `/` | 图片能开；`/` 出欢迎页 |
| 2 | 1.6 | yml 改 `port: 8081` + multipart 上限；写 `UploadController` → Postman form-data 传图成功 | 日志显示 8081；upload/ 目录出文件 |
| 3 | 1.6 | 把 `max-file-size` 临时改回 1MB 再传大图 | 报 MaxUploadSizeExceededException（先不处理） |
| 4 | 1.7 | 写 `WebConfig`：`/img/**` → `classpath:/img/`，放张图验证 | /img/ 能访问 **且** static/ 旧规则仍能访问 |
| 5 | 1.8 | 写 `GlobalExceptionHandler`（MaxUploadSize + Exception 兜底）→ 重复第 3 步 | 返回你定义的 JSON 而不是白页 |

**选敲（5 分钟内的加分项）：** favicon.ico 丢 static/ 强刷看图标；`spring.web.resources.cache.period=60` 后 F12 看第二次请求 304。

**只读不敲：** 1.2 / 1.3 / 1.4（原理，口述过关用）。
**整块跳过：** WebJars、缓存细节、路径匹配、内容协商、Thymeleaf、国际化、server.tomcat 优化。

## 检验标准（对照 README"重点掌握"档）

- [ ] 四个前缀各说出 1 个常用配置项和默认值（multipart 的 1MB 必须脱口而出）
- [ ] 白板讲"WebMvcConfigurer 扩展 vs @EnableWebMvc 接管"（Q1，本章最高频）
- [ ] 手写 WebConfig + 全局异常处理器，不看笔记 5 分钟内成型（黑马点评直接搬）
- [ ] README 面试题第 6 题（内嵌 Tomcat）能答
