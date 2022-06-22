package top.testeru.asserts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.testeru.MySUT;



import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description: 多断言处理
 * 使用Junit5提供的Java 8 lambdas的assertAll断言方法，增加了脚本的容错性
 * 通过assertThat引用三方Matcher库，大大增加了断言的灵活性
 * @Version 1.0
 * @create: 2022/6/20 2:14 PM
 */
public class GroupedAssertionsTest {
    static MySUT mySUT;
    @BeforeAll
    static void beforeAll(){
        mySUT = new MySUT("assertAll");
    }
    @Test
    void groupedWithOutMessageAssertions() {

        int result = mySUT.sum(1, 1);
        //断言失败没有提示消息
        assertAll(
                    //junit5 断言
                    () -> assertEquals(2,result,"1+1结果错误"),
                    //hamcrest断言
                    () -> assertThat("1+1结果错误",result,is(equalTo(2))),
                    //assertj断言
                    () -> assertThat(result).isGreaterThan(1).isLessThan(3)
                 );



    }
    @Test
    void groupedWithMessageAssertions() {
        int result = mySUT.sum(1, 2, 1);
        //断言失败有提示消息
        assertAll("多个断言失败",
                //junit5 断言
                () -> assertSame(4,result,"1+1结果错误"),
                //hamcrest断言
                () -> assertThat("1+1结果错误",result,
                        both(greaterThan(2)).and(lessThan(5))),
                //assertj断言
                () -> assertThat(result).isGreaterThan(1).isLessThan(5)
        );
    }
}
