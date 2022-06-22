package top.testeru;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/21 3:19 PM
 */
@DisplayName("allure-MySUT")
class MySUTTest {

    static MySUT sut ;
    @BeforeAll
    static void beforeAll(){
        sut = new MySUT("params");
    }

    @DisplayName("allure-1+1")
    @Test
    void allure1Test() {

        int result = 1 + 1;
        assertEquals(2,result,"1+1结果错误");

    }
    @DisplayName("allure-2+1")
    @Test
    void allure2Test() {

        int result = 2 + 1;
        assertEquals(3,result,"2 + 1结果错误");

    }


}