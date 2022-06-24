package top.testeru.selftag;

import org.junit.jupiter.api.*;
import top.testeru.basic.BasicTest;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: tutorials
 * @author: testeru.top
 * @description: 常用注解测试：
 * 自定义方法上元注释
 *      @IntTagTest 替换 @Test+@Tag("integer")
 * 自定义类上/方法上元注释
 *      @MyClassTag 替换 @Tag("mytag")
 * @Version 1.0
 * @create: 2022/1/17 5:01 下午
 */
@DisplayNameGeneration(SelfTagAnnotationTest.ReplaceCamelCase.class)
//@MyClassTag
public class SelfTagAnnotationTest extends BasicTest {

    @DisplayName("加法")
//    @Test
//    @Tag("integer")
    @IntTagTest
    void addTest1(){
        logger.info("Begin Add Test");
        //加法运算
        int sum = mySUT.sum(5, 8);
        logger.info("Operation result：{}",sum);
        // expected:期望值,  actual:运算的实际值
        assertEquals(13,sum);
    }

    @Test
    @Tag("integer")
    void subtractTest1(){
        logger.info("Begin Subtract Test");
        //减法运算
        int subtract = mySUT.subtract(5, 8);
        logger.info("Operation result：{}",subtract);
        // expected:期望值,  actual:运算的实际值
        assertEquals(-3,subtract);
    }

    @DisplayName("字符串拼接🐶")
    @Test
    @Disabled
    @Tag("str")
    void csTest1(){
        logger.info("Begin ConcatStr Test");
        //字符串拼接
        String concatStr = mySUT.concatStr("Hello","Junit5");
        logger.info("Operation result：{}",concatStr);
        // expected:期望值,  actual:运算的实际值
        assertEquals("Hello Junit5",concatStr);
    }

    @Test
    @Tag("integer")
    void subtractTest2(){
        logger.info("Begin Continuous Subtract Test");
        //100连续减
        int subtract = mySUT.subtract(50,30,10,60);
        logger.info("Operation result：{}",subtract);
        // expected:期望值,  actual:运算的实际值
        assertEquals(-50,subtract);
    }

    @Test
    @Disabled("平均值有bug")
    @Tags({
            @Tag("decimal"),
            @Tag("dev")
    })
    void avTest1(){
        logger.info("Begin Average Test");
        //平均值
        double average = mySUT.average(55,44,86,72,64);
        logger.info("Operation result：{}",average);
        // expected:期望值,  actual:运算的实际值
        assertEquals(64.2,average);
    }

    @Test
    @Tag("decimal")
    void avTest2(){
        logger.info("Begin Average Test");
        //平均值
        double average = mySUT.average(95,44,86,72,64);
        logger.info("Operation result：{}",average);
        // expected:期望值,  actual:运算的实际值
        assertEquals(72.2,average);
    }
    //标准显示名称生成行为 Standard默认配置
    static class ReplaceCamelCase extends DisplayNameGenerator.Standard {
        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return replaceCamelCase(super.generateDisplayNameForClass(testClass))+ "...";
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass));
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            //+ DisplayNameGenerator.parameterTypesAsString(testMethod) 方法的括号
            return this.replaceCamelCase(testClass.getSimpleName()+ "；方法名：" + testMethod.getName()+ ".");
        }

        String replaceCamelCase(String camelCase) {
            StringBuilder result = new StringBuilder();
            result.append(camelCase.charAt(0));
            for (int i=1; i<camelCase.length(); i++) {
                if (Character.isUpperCase(camelCase.charAt(i))) {
                    result.append(' ');
                    result.append(Character.toLowerCase(camelCase.charAt(i)));
                } else {
                    result.append(camelCase.charAt(i));
                }
            }
            return result.toString().replace("test","Test");
        }
    }

}