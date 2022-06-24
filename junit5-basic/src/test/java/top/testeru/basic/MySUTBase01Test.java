package top.testeru.basic;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
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
 */
class MySUTBase01Test {
    static final Logger logger = getLogger(lookup().lookupClass());
    @Test
    void addTest(){
        //1.创建被测系统对象 在所有操作之前创建计算器
        MySUT mySUT = new MySUT("Calculator");
        //2.每个测试方法前生成ID标识
        mySUT.initId();
        //3.每个test case业务逻辑前进行log打印 开始测试
        logger.info("Start Testing");
        //4.业务逻辑 test case 编写
        int result = mySUT.sum(1, 1);
        //5.测试后结果打印：计算结果result
        logger.info("Result is :{}",result);
        //6.对测试结果进行断言
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(2,result,"The result of adding two numbers is wrong");
        //hamcrest断言
        assertThat("The result of adding two numbers is wrong",result , is(equalTo(2)));
        //7.销毁ID
        mySUT.destroyId();
    }
}

