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
 1.入口配置在pom文件<manifest>标签中，插件会扫描这些manifest中的配置。
 2.实际的类配置可以参考META-INF/dal/jdbc/*，写完xml配置要执行mvn idea:idea进行代码生成。
 3.容器配置位于META-INF/plexus/components.xml中，
