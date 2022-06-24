package top.testeru.num;

import org.junit.jupiter.api.Test;
import top.testeru.basic.BasicTest;
import top.testeru.tag.selftag.IntTagTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySUTSubTest extends BasicTest {

    @IntTagTest
    void subTest(){
        result = mySUT.subtract(20,10,30);
        //JUnit5断言 expected：期望值,  actual：业务代码实际结果,  message：断言失败的时候显示说明的语句
        assertEquals(40,result, "The result of sub two numbers is wrong");
        //hamcrest断言
        assertThat( "The result of sub two numbers is wrong",result , is(equalTo(40)));
        assertThat( "The result of sub two numbers is wrong",result , is(lessThanOrEqualTo(100)));
    }
}
