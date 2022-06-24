package top.testeru.str;

import org.junit.jupiter.api.Test;
import top.testeru.basic.BasicTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySUTStrTest extends BasicTest {
    @Test
    void str1Test(){
        resultStr = mySUT.concatStr("hello", "JUnit5");
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals("hello JUnit5",resultStr, "The result of sub two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong","hello JUnit5" ,
                containsStringIgnoringCase("HELLO JUnit5"));
    }
    @Test
    void str2Test(){
        resultStr = mySUT.concatStr("这里是", "北京");
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals("hello JUnit5",resultStr, "The result of sub two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong","hello JUnit5" ,
                containsStringIgnoringCase("HELLO JUnit5"));
    }
    @Test
    void str3Test(){
        resultStr = mySUT.concatStr("hello", "JUnit5");
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals("hello JUnit5",resultStr, "The result of sub two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong","hello JUnit5" ,
                containsStringIgnoringCase("HELLO JUnit5"));
    }
}














