package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.Thread.sleep;
import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodHandles.throwException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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
class MySUTBase03Test extends BasicTest{

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
        //抛出异常断言
        //Class<T> expectedType：抛出的异常类型，必须是.class结尾 T是泛型
        //Executable executable：业务逻辑流
        //String message：异常失败的显示原因
        //如果没有抛出异常，或者抛出了不同类型的异常，则此方法将失败
        //抛出指定类型异常(抛出的异常是指定异常的子类型即可)
        //Exception exception = assertThrows(RuntimeException.class, () -> mySUT.sum(9, 100));
        //抛出指定类型异常(抛出的异常和指定异常的class类型必须完全相同)
        Exception exception = assertThrowsExactly(NumberFormatException.class, () -> mySUT.sum(9, 100));

        assertTrue(exception.getMessage().contains("integer is 100"));
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",exception.getMessage() , is(containsString("integer is 100")));
    }

    @BeforeAll
    static void ba(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("MySUTBase03Test beforeAll");
    }

    @BeforeEach
    void be(){

        logger.info("MySUTBase03Test beforeEach");
    }

    @AfterEach
    void ae(){
        logger.info("MySUTBase03Test afterEach");
    }

    @AfterAll
    static void aa(){
        logger.info("MySUTBase03Test afterAll");
    }

}

