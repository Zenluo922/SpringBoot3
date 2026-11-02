# Spring Boot 重点学习指南

> 依据《SpringBoot 讲义》（动力节点，Spring Boot **3.3.x**，共 6 章约 7500 行，本目录 `document/`）
> 制定时间：2026-11-01 ｜ 目标：**11-1 下午 ~ 11-3，两天半学完**，11-4 Redis 速成，11-5 进黑马点评
>
> **核心策略：Boot 是一台"自动化机器"——你昨天在 `ssm-annotation` 里手写的每一行配置，都会被它自动完成。**
> 该花时间的是：**ch4 自动配置原理（Java 实习面试第一高频）、ch3 SSM 整合（黑马点评骨架的直接预演）、ch2 核心注解 + yml（项目日常）**。
> 该省时间的是：**脚手架细节、静态资源缓存、内容协商、路径匹配、WebJars**——知道有这回事即可。
>
> 环境：课程 JDK 21 + Boot 3.3.x；你本机 JDK 17 **完全够用**（Boot 3 最低要求 17），不用升级。内嵌 Tomcat，**不再需要 IDEA 配 Tomcat 运行配置**。`tools/` 里是浏览器插件（FeHelper），与课程本体无关。

---

## 〇、优先级总览

| 优先级 | 含义 | 章节清单 |
|--------|------|----------|
| 🔥🔥🔥 必须精通 | 动手写 + 白板能讲 | 2 核心注解+yml配置 / 3 SSM整合 / 4 自动配置原理 / 6 的 R对象+事务 |
| 🔥🔥 重点掌握 | 理解 + 会用 | 5 的通用配置(server.port/文件上传)+静态资源默认规则 / 1 主入口与内嵌Tomcat |
| 🔥 快速过 | 知道有这回事 | 1 脚手架三种方式(会用 IDEA 版即可) / 2 单元测试 / 6 logo+PageHelper+打war / 5 缓存+内容协商+路径匹配 |

**如果时间只剩 2 天的最短路径：** 1 → 2 → 3 → 6(R+事务) → 4 → 5(通用配置)

---

## 一、分章重点明细

### 第 1 章 First Spring Boot 🔥（1~1.5 小时，跑通即可）
- 第一个项目完整跟做一遍：**继承 spring-boot-starter-parent → 加 web starter → 主入口 @SpringBootApplication → main 直接启动内嵌 Tomcat**——体验"不配 Tomcat 不写 web.xml 不写配置类"
- **jar 包直接运行**（`java -jar`）知道即可，这是你项目期部署学生服务器的方式
- 脚手架三种（官方网页 / 阿里 / **IDEA 内置 Spring Initializr**）：只练 IDEA 版，以后建项目全靠它，其余两个快过
- ⚠️ 别在"jar 和普通 jar 区别"上耗时间——记住 Boot jar 把所有依赖打进一个包、能直接跑，面试一句话够用

### 第 2 章 Spring Boot核心机制 🔥🔥🔥（半天，项目日常全在这）
- **继承 vs 引入依赖**：parent 只做依赖版本统一管理（dependencyManagement），理解"为什么版本号都不用写"
- **starter**：一站式依赖集合，记住定位——"导 starter = 声明我要用某功能"，原理细节 ch4 讲透
- **@SpringBootApplication 三合一**：@SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan——⚠️ **主入口类必须放根包下**（默认只扫所在包及子包），放错位置组件扫不到，经典坑+经典面试题
- 外部化配置：**application.yml 语法必练**（缩进、冒号空格）；**@Value（散配置）vs @ConfigurationProperties（绑定 bean）会选**；**多环境 spring.profiles.active 切 dev/prod 必会**——黑马点评连数据库就用这套
- 单元测试 @SpringBootTest：快过，能注入 service 跑通即可

### 第 3 章 SSM整合 🔥🔥🔥（大半天，黑马点评骨架预演）
- 整合 MyBatis 三件套：**mybatis-spring-boot-starter → yml 里配 datasource + mybatis.mapper-locations → 主类 @MapperScan**——对照 `ssm-annotation` 的 DataSourceConfig/MybatisConfig 看被省掉了什么
- SpringMVC 部分不用学（自动配置了），直接写 controller + service + mapper 跑通完整 CRUD
- **Lombok**：@Data / @Builder / @Slf4j 常用三个记住；IDEA 开启注解处理（Settings → Enable annotation processing），否则编译爆红
- 逆向生成（free mybatis tools 插件）：了解即可——黑马点评用 **MyBatis-Plus**，逆向思路知道就行，别花时间练插件
- ✅ 检验：新建模块，yml 连库 + Mapper CRUD + controller 返回 JSON，30 分钟内从零跑通

### 第 4 章 Spring Boot自动配置 🔥🔥🔥（半天，面试第一高频，精读）
- **两大核心 = starter + 自动配置**，本章是全课程的面试价值担当
- 记住这条链：**starter 引入自动配置 jar → @EnableAutoConfiguration 加载 152 个自动配置类 → 条件注解按需筛选 → 属性类 @ConfigurationProperties 绑定 yml → 组件进 IoC 容器**
- 条件注解认得常见几个：@ConditionalOnClass / @ConditionalOnMissingBean / @ConditionalOnProperty
- **一句话总结（讲义原话，面试直接用）：导入启动器，修改配置文件，就可以完成对应功能的开发**
- 对照学习：ssm-annotation 里 `WebAppInitializer`↔内嵌容器自动注册 DispatcherServlet、`SpringMvcConfig`↔WebMvcAutoConfiguration、`DataSourceConfig`↔datasource 自动配置——**手写的原型找到，自动配置就秒懂**

### 第 5 章 Spring Boot的web开发 🔥🔥（半天，只学三块）
- **web 通用配置认前缀**：`server.`（端口等）/ `spring.servlet.multipart.`（文件上传——头像上传要用）/ `spring.web.`
- **静态资源默认规则**知道即可：classpath:/static 等目录下的文件直接按路径访问；欢迎页 index.html
- 手动配置方式理解思想：容器里有 `WebMvcConfigurer` 组件就生效（拦截器、异常处理以后都这么配）
- ⏭ 内容协商、路径匹配（AntPathMatcher/PathPatternParser）、静态资源缓存：**全跳**，面试不问项目用不上

### 第 6 章 SpringBoot实用技术整合 🔥🔥（1 小时，两小两大）
- **R 对象统一响应封装（必会）**：code/msg/data 三段式泛型——黑马点评"统一响应"改造点就是它，跟 @ControllerAdvice 全局异常配对使用
- **事务管理（必会）**：Boot 里 @Transactional 直接用，零配置——对照 ssm-annotation 里手写的 DataSourceTransactionManager，体会"自动配置"
- logo 开关：30 秒过；PageHelper：MyBatis ch17 已学过，直接用
- 打 war 包：知道三步（改 packaging → 排除内嵌 tomcat → 继承 SpringBootServletInitializer）即可，**你项目部署走 jar**

---

## 二、面试高频题自测清单

学完后逐条自测，答不上来回看对应章节：

| # | 面试题 | 出处 | 掌握标准 |
|---|--------|------|----------|
| 1 | **Spring Boot 自动配置原理？** | 4 | 能讲链条：starter→@EnableAutoConfiguration→条件注解按需筛选→属性类绑定 yml；这是重灾区 |
| 2 | 什么是 starter？解决什么问题？ | 2/4 | 一站式依赖集合 + 版本托管；"导 starter + 改配置 = 完成功能" |
| 3 | @SpringBootApplication 包含哪些注解？默认扫描范围？ | 2 | 三合一答出 + 主类放根包的原因 |
| 4 | @Value 和 @ConfigurationProperties 怎么选？ | 2 | 散配置 vs 批量绑定 bean（+ relaxed binding） |
| 5 | yml 和 properties 区别？多环境怎么切换？ | 2 | 层次化/简洁性；spring.profiles.active 两种方式 |
| 6 | Boot 内嵌 Tomcat 是怎么回事？ | 1/5 | main → SpringApplication.run → 自动配置起内嵌容器；不部署外置容器 |
| 7 | Boot 里整合 MyBatis 要哪些配置？ | 3 | starter + datasource yml + @MapperScan + mapper-locations，实操题 |
| 8 | jar 和 war 的区别？Boot 为什么推荐 jar？ | 1/6 | 自包含可执行 vs 外置容器；内嵌容器让 jar 直接跑 |
| 9 | 前后端分离怎么统一响应格式？ | 6 | R 对象 + @ControllerAdvice + 全局异常，结合黑马点评改造点讲 |
| 10 | Spring / Spring MVC / Spring Boot 三者关系？ | 4/总结 | 基座 / Web 层框架 / 自动配置脚手架；接 SpringMVC 清单第 10 题 |

---

## 三、时间规划（11-1 下午 ~ 11-3）

| 天 | 内容 | 产出检验 |
|----|------|----------|
| 11-1 下午 | 1 跑通第一个项目（1h）→ 2 前半：核心注解 + starter | IDEA 脚手架 3 分钟建项目，main 一键跑通；@SpringBootApplication 三合一能口述 |
| 11-2 | 2 后半：yml/@Value/@ConfigurationProperties/多环境 → **3 SSM 整合全流程**（对照 ssm-annotation）→ 6 的 R 对象 + 事务 | yml 连库 CRUD 跑通；@ConfigurationProperties 绑定 bean；R 对象 + 全局异常成型 |
| 11-3 | **4 自动配置原理精读**（半天，白板能画）→ 5 只学通用配置/文件上传/静态资源规则 → 自测清单过关 | 自动配置原理白板讲 1 分钟；server.port/文件上传配置会改；面试题 10 题全过 |

> 每章在 IDEA 新建独立模块跟做（课程命名 sb3-0X-xxx，沿用即可）。学完 11-4 直接进 Redis 速成——Boot 整合 Redis 也是自动配置，正好用 ch4 的视角看它。
> **算法打卡照常每天 1 题，项目期不断。**

---

## 四、学习检验标准（学完 Spring Boot 你应该能做到）

- [ ] IDEA 脚手架从零建 Boot web 项目 → main 一键跑通，全程不碰 Tomcat 配置
- [ ] 手写：yml 配数据源 + @MapperScan + mapper-locations，MyBatis CRUD 跑通
- [ ] 手写：@ConfigurationProperties(prefix) 绑定自定义配置类 + 多环境 dev/prod 切换
- [ ] 手写：R<T> 统一响应对象 + @RestControllerAdvice 全局异常组合成型（黑马点评直接搬）
- [ ] 白板讲自动配置原理（starter → @EnableAutoConfiguration → 条件注解 → 属性绑定）
- [ ] 能说清 ssm-annotation 每个手写配置类被 Boot 的哪个自动配置替代了

---

## 五、避坑提醒

1. **JDK 用你本机 17 就行**——课程 21，Boot 3 最低要求 17，不需要升级；IDEA 里 Project/Module SDK 选 17
2. **内嵌 Tomcat 后不再配 IDEA Tomcat 运行配置**——main 方法直接跑；报端口占用（8080）先杀旧进程（ssm-annotation 的 Tomcat 窗口记得关）
3. **主入口类放根包**（如 com.powernode.xxx）——默认包扫描只扫所在包及子包，放深了组件扫不到还报莫名 NPE
4. **Lombok 编译爆红**先查 IDEA 的 Enable annotation processing 有没有开
5. **版本跟课程走 3.3.x**（parent 3.3.3/3.3.4 都行），别追最新版——黑马点评复刻资料对版本敏感
6. **Boot 依赖量大**，确认 IDEA 的 Maven home 指向你本地 `D:\Develop\Maven` 且配了阿里云镜像，不然下载等到怀疑人生
7. **打 war 一节快速过**——你项目部署走 jar + Docker（外置 Tomcat 才需要 war）
8. 学 ch4/ch5 时**开着 ssm-annotation 对照**：每个自动配置找它的手写原型，这是理解自动配置最快的路径，也是面试对比讲差异化（"我知道它帮我自动做了什么"）的素材
