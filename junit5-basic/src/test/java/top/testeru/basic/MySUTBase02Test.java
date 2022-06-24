package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * 1.创建计算器对象
 * 2.生成测试用例ID
 * 3.测试方法之前打印：开始进行加法测试操作
 * 4.编写测试业务逻辑
 * 5.测试后打印：计算结果result
 * 6.对测试结果进行断言
 * 7.销毁ID
 * 改造，添加beforeall afterall beforeEach afterEach
 */
class MySUTBase02Test {
    static final Logger logger = getLogger(lookup().lookupClass());
    static MySUT mySUT;
    int result;
    //static修饰 返回值为void @BeforeAll注解只在最初的时候运行一次 static静态方法加载的优先级高于非静态方法
    //作用：测试用例中，数据的初始化、环境的安装准备在这里（被测包apk的安装、网站对应cookie、session的获取、app启动参数...）
    @BeforeAll
    static void beforeAll(){
        //1.创建被测系统对象 在所有操作之前创建计算器
        mySUT = new MySUT("Calculator");
    }
    //返回值为void @BeforeEach注解在每一个 @Test修饰的测试方法前运行一次。有多少个@Test运行多少次
    //作用：测试用例中，初始化测试方法所需要使用的某些属性（app、web进入首页/固定页面；log日志文件的删除；增加部门前删除已有的部门名）
    @BeforeEach
    void beforeEach(){
        //2.每个测试方法前生成ID标识
        mySUT.initId();
        //3.每个test case业务逻辑前进行log打印 开始测试
        logger.info("Start Testing");
    }
    @Test
    void add1Test(){
        //4.业务逻辑 test case 编写
        result = mySUT.sum(1, 1);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(2,result,"The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",result , is(equalTo(2)));
    }

    @Test
    void add2Test(){
        //4.业务逻辑 test case 编写
        result = mySUT.sum(1, 1,9);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(11,result,"The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",result , is(equalTo(11)));
    }
    @Test
    void add3Test(){
        //4.业务逻辑 test case 编写
        result = mySUT.sum(100, 0);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(100,result,"The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",result , is(equalTo(100)));
    }

    //返回值为void 与@BeforeEach注解对应，在每一个 @Test修饰的测试方法后运行一次。有多少个@Test运行多少次，断言无论是否失败都运行
    //作用：测试用例中，销毁测试方法所需要使用的某些属性（app、web返回首页/固定页面；log日志文件的删除；增加部门后删除已有的部门名）
    @AfterEach
    void afterEach(){
        //5.测试后结果打印：计算结果result
        logger.info("Result is :{}",result);
    }
    //static修饰 返回值为void @与BeforeAll注解对应，只在最后的时候运行一次 static静态方法加载的优先级高于非静态方法
    //作用：测试用例中，apk的卸载，app退出在这里
    @AfterAll
    static void afterAll(){
        //7.销毁ID
        mySUT.destroyId();
    }
}

