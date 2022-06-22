package top.testeru;

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
@DisplayName("allure3-MySUT")
class MySUT3Test {

    static MySUT sut ;
    @BeforeAll
    static void beforeAll(){
        sut = new MySUT("allure3");
    }

    @DisplayName("allure3-1+1")
    @Test
    void allureTest() {

        int result = 11 + 11;
        assertEquals(21,result,"1+1结果错误");

    }
    @DisplayName("allure3-2+1")
    @Test
    void allure2Test() {

        int result = 21 + 11;
        assertEquals(32,result,"2 + 1结果错误");

    }

}