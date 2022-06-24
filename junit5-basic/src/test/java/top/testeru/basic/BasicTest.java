package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import java.lang.reflect.Method;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BasicTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static MySUT mySUT;
    public int result;
    public String resultStr;
    //static修饰 返回值为void @BeforeAll注解只在最初的时候运行一次 static静态方法加载的优先级高于非静态方法
    //作用：测试用例中，数据的初始化、环境的安装准备在这里（被测包apk的安装、网站对应cookie、session的获取、app启动参数...）
    @BeforeAll
    public static void beforeAll(){
        //1.创建被测系统对象 在所有操作之前创建计算器
        mySUT = new MySUT("Calculator");
    }
    //返回值为void @BeforeEach注解在每一个 @Test修饰的测试方法前运行一次。有多少个@Test运行多少次
    //作用：测试用例中，初始化测试方法所需要使用的某些属性（app、web进入首页/固定页面；log日志文件的删除；增加部门前删除已有的部门名）
    @BeforeEach
    public void beforeEach(){
        //2.每个测试方法前生成ID标识
        mySUT.initId();
        //3.每个test case业务逻辑前进行log打印 开始测试
        logger.info("Start Testing");
    }

    //返回值为void 与@BeforeEach注解对应，在每一个 @Test修饰的测试方法后运行一次。有多少个@Test运行多少次，断言无论是否失败都运行
    //作用：测试用例中，销毁测试方法所需要使用的某些属性（app、web返回首页/固定页面；log日志文件的删除；增加部门后删除已有的部门名）
    @AfterEach
    public void afterEach(TestInfo testInfo){
        String str1 = testInfo.getTestMethod().map(Method::getName)
                .filter(str -> str.startsWith("str"))
                .ofNullable(resultStr).orElse(String.valueOf(result));
        //5.测试后结果打印：计算结果result
        logger.info("Result is :{}",str1);
    }
    //static修饰 返回值为void @与BeforeAll注解对应，只在最后的时候运行一次 static静态方法加载的优先级高于非静态方法
    //作用：测试用例中，apk的卸载，app退出在这里
    @AfterAll
    public static void afterAll(){
        //7.销毁ID
        mySUT.destroyId();
    }
}
