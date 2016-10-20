package com.zs.test.rbac.maven;

/**
 * maven学习笔记:
 * 1 下载安装maven,配置环境变量。
 * 2 改变maven默认的本地仓库。
 * 3 maven中骨架概念：maven中固有的固定的组织架构的格式。
 * 4 maven中scope的取值：
 * 		compile:编译，测试，打包命令都会用到。
 * 		provided:编译，测试用到，打包不用。(解决依赖中jar包冲突的问题)
 * 		runtime:编译，测试无效，运行其有效。
 * 		test:测试用，编译和打包不用。
 * 4 maven依赖：1 依赖之间具有传递性。
 * 		依赖传递特点：complied范围的jar包回传递，test域的jar包不传递。
 * 		依赖传递规则：依赖级别相同：在pom.xml文件中先声明那个jar包，则引用那个jar包。
 * 				  依赖级别不同：引用级别较短的jar包。
 * 5 maven聚合：为了更好的解耦，通常将一个项目按照用途或模块分成不同的工程。当编译这个项目时候，需要一个一个的install各个工程，较麻烦，所以maven 提供了聚合功能，
 * 			      将所有的工程聚合在一个管理工程中(pom)，运行这一个管理工程就可以编译所有的模块。
 * 				<modle></modle>
 * 6 maven继承：在团队开发中，同一个jar包，不同的人可能引用不同的版本，这个项目管理带来不便。为此，maven提供了继承功能，定义一个parent的管理maven工程，其他子工程都
 * 			      继承这个父工程。在父工程中引入所有需要的jar包，并放在<dependencyManager>中，这样父工程对jar包的管理就是定义引用，子工程需要那个就在自己的工程中定义那个。
 * 			      这样子工程在pom.xml文件中引用jar包，只需要定义groupID,artid，不需要定义版本号。
 *            还可以将一些常量定义在父工程中，这样就不需要在每个子工程中定义此变量。
 *            子工程中通过<parent>标签进行继承父类。
 * 			
 *
 */
public class MavenJava {

}
