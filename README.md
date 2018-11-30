# cat
基于大众点评cat3.0自己做修改的版本
要想接入cat请去大众点评的开源项目：https://github.com/dianping/cat/tree/master
这里从二次开发角度简单做下说明
# 1.导包
直接clone代码导入项目依赖会报错有些包没有，因为cat项目依赖的jar包如：代码自动生成框架，数据库相关等都只存在于大众点评的私有云，在maven公共云上没有，
你有两种选择，

 1.在你的maven文件中配置大众点评的私有云地址
 
 2.大众点评cat开源项目里边有个“mvn-repo”分枝，项目中依赖的私有包都在这个分枝里，自己找到，拷贝到本地maven仓库
 
如果以上做好了，导包就不会报错了
# 2.让项目在你的IDE中跑起来
包导入以后，直接在IDE运行项目，还是跑不起来的，因为有些在xml里配置的类还没有生成，需要在项目路径下执行mvn idea:idea或mvn eclipse:eclipse，
如果命令成功结束，代码就已经生成好了，项目不会再报错，可以运行起来了，
# 3.自动生成代码
自动生成代码的功能就简单说两句，
 1.入口配置在pom文件插件codegen-maven-plugin中的配置。
 
 2.实际的类配置可以参考META-INF/dal/jdbc/*，写完xml配置要执行mvn idea:idea进行代码生成。
 
 3.容器配置位于pom文件插件plexus-maven-plugin中的配置。
 
# 4.我做的修改

 1.主要添加了一个web_server_config表，用于配置在邮件中指向的cat的服务器域名，原来的cat项目这里是写死的。
 2.修改transaction规则匹配逻辑，对name支持两种模糊匹配策略:xyf_partten_all:，:xyf_partten_either:,支持加在头和尾的*，支持以,分割不同name，具体见示例。
例子：
```allName:xyf_partten_all:my*```意思是匹配所有以my开头的name，**汇总**匹配到的name的数据与配置的规则做匹配。
比如监控项配置为count，监控条件为持续1分钟，阈值为10，则同一type下以my开头的name**执行次数之和**1分钟之内如果大于10就会触发报警；
比如监控项配置为failRatio，监控条件为持续1分钟，阈值为0.5，则同一type下以my开头的name的总**加权失败率**如果大于50%就会触发报警；
```allName:xyf_partten_either:my*,*2```意思是匹配所有以my开头的或者以2结尾的name，**取最大值**与规则匹配。
