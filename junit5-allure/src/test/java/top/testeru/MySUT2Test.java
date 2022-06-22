package top.testeru;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/21 3:19 PM
 */
@DisplayName("allure-MySUT2")
class MySUT2Test {

    static MySUT sut ;
    @BeforeAll
    static void beforeAll(){
        sut = new MySUT("allure2");
    }

    @DisplayName("allure2-1+1")
    @Description("v Some detailed test description")
    @Test

    void allureTest() {
        int result = sut.sum(11,55);
        assertEquals(220,result,"1+1结果错误");

    }
    @DisplayName("allure2-2+1")
    @Test
    void allure2Test() {

        int result = sut.sum(21,11);
        assertEquals(32,result,"2 + 1结果错误");

    }

}