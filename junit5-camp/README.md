# Basic
## 拆分用例类
- 1.`BaseTest` 类只有 `BeforeAll`,`BeforeEach`,`AfterEach`,`AfterAll`
  - 执行顺序：
    - `BeforeAll`
      - `BeforeEach` --> `Test 1` --> `AfterEach`
      - `BeforeEach` --> `Test 2` --> `AfterEach`
      - ...
    - `AfterAll`
- 2.加法、减法、字符串拼接为各自测试类
  - 加法：`MySUTAddTest`
  - 减法：`MySUTSubTest`
  - 字符拼接：`MySUTStrTest`
- 3.最终打印所有运算结果`result`：`TestInfo`{beforeEach/AfterEach都可以获取到}
  - 算数运算的打印`int`
  - 字符的打印`str`
>使用TestInfo获取到当前执行的测试方法名，判断方法名以什么字段开头
> 如果是str，则打印字符结果；其他的则打印int
## 编写抛异常用例
- 4.使用`JUnit5`自带异常断言
  - 断言返回值为：异常的`class`类型，不是异常的描述
  - `assertThrows`：抛出的异常类为指定异常与其子类都可。
  - `assertThrowsExactly`：只能是完全一样的异常类。
## 自定义显示名
- 5.`DisplayName`
  - 是**方法**「`ElementType.TYPE`」和**类**「`ElementType.METHOD`」上的注解；
  - 对应`value`为显示的名称
    - 可以是空格、特殊符号、表情符号
## 测试方法排序
- 6.使用`order`注解对测试方法排序：`MySUTAddTest`
  - `MethodOrderer`选择排序规则
  - 2种配置方式
    - `JUnit5`配置文件：`junit-platform.properties` 
    - 测试类上直接添加注解：`@TestMethodOrder`
    - 优先级：配置文件声明 **<** 类上声明排序规则
## 测试方法参数化
- 7.使用参数化进行多参数运算：`ParameterizedTest`+`*Source`「缺一不可」
  - `ParameterizedTest`：声明参数化方式执行测试用例
    - 不要在`@ParameterizedTest`注解的方法上添加`Test`注解「参数化注解就已经包含了test」
    - `ARGUMENTS_WITH_NAMES_PLACEHOLDER`
      - `key=value` 的map结构；`key`为方法参数名，`value`为参数值。eg:a=11,b==11,re=22
    - `ARGUMENTS_PLACEHOLDER`
      - `list`结构；直接为`value`的`list`集合。eg:11,11,22
  - `MethodSource`：提供参数源的注解
    - 参数方法与测试方法同名，则直接默认
    - 参数方法与测试方法名不同，则需要在括号内指定参数方法名
## suite套件管理测试集
- 8.使用`suite`套件运行测试集
  - `SelectClasses`
    - 选择多个测试类为测试集
  - `SuiteDisplayName`
    - 自定义对应测试集的显示名


# Advanced
- 对应脚本优化
## 参数化进阶
- 1.测试系统所需参数为多个「2个、3个、4个...」，合并到一个测试方法中
  - 使用`List`集合包含多个参数：`MySUTAddProTest#addPro1Test`{无异常}、`MySUTAddProTest#addPro2Test`{抛异常}
- 2.多个参数转为实体类提供给测试方法：`MySUTAddProTest#addPro3Test`
  - stream流提供的为多个参数
  - 测试方法的形参为实体类对象
  - `@AggregateWith`注解指定自定义实现的 `ArgumentsAggregator`
- 3.自定义注解优化2：`ArgToSumData`
  - `@AggregateWith`注解➕实现类，合并为一个自定义注解
## 多断言
```xml
<properties>
    <assertj.version>3.23.1</assertj.version>
    <hamcret.version>2.2</hamcret.version>
</properties>
<dependencies>
  <dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest-core</artifactId>
    <version>${hamcret.version}</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>${assertj.version}</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```
- 4.使用`Junit5`提供的`Java 8 lambdas`的`assertAll`断言方法，增加了脚本的容错性
- 通过`assertThat`引用第三方`Hamcrest`的`Matcher`库，大大增加了断言的灵活性
- `assertAll`：`MySUTAddProTest#addPro4Test`
  - junit5 断言：测试框架自带断言
  - hamcrest 断言：多语言适用
  - assertj 断言：只适用Java语言
## 数据驱动
```xml
<properties>
  <jackson.version>2.13.3</jackson.version>
</properties>
<dependencies>
  <!--yaml文件解析-->
  <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml -->
  <dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>vhvh
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>${jackson.version}</version>
  </dependency>
  <!-- Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling -->
  <!-- 正常解析datatime变量 -->
  <dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>${jackson.version}</version>
  </dependency>
</dependencies>
```
- 5.`yaml` 文件解析：`MySUTAddYamlTest`
- 6.正常用例与异常测试合并：
  - `MySUTAddYamlTest`
## 动态生成测试用例
