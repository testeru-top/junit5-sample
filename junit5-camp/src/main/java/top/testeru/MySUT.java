package top.testeru;

import io.qameta.allure.Step;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @program: tutorials
 * @author: testeru.top
 * @description: 我的被测系统软件
 * 被测系统数值相关计算，传递的数据类型为：整型
 * 取值范围:[-99 , 99]
 * @Version 1.0
 * @create: 2022/1/17 4:58 下午
 */
public class MySUT {
    //获得具有所需名称的记录器
    static final Logger logger = getLogger(lookup().lookupClass());

    //用例名
    String name;
    //唯一ID标识
    String id;
    private static ThreadLocal<String> threadLocalId = new ThreadLocal<String>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MySUT(String name) {
        this.name = name;
        logger.info("Open {} ", name);
    }


    public void initId(){
        id = UUID.randomUUID().toString();
        threadLocalId.set(id);
        logger.info("Generate ID：{} ", id);

    }

    public void destroyId() {
        logger.info("Release ID: {} ", id);
        threadLocalId.remove();

 /*       if (id == null) {
            throw new IllegalArgumentException(name + " failed to initialize ID");
        }
        logger.info("Release ID: {} ", id);
        id = null;*/
    }


    public void close() {
        logger.info("Close {} ", name);
    }



    //连续添加
    @Step("")
    public int sum(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u == 100)){
            logger.warn("integer is 100！");
            throw new NumberFormatException("integer is 100！");
        }else  if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            // 请输入范围内的整数
            logger.warn("Please enter an integer in the range");
            throw new IllegalArgumentException("Please enter an integer in the range！");
        }else {
            return IntStream.of(numbers).sum();
        }

    }


    //从100进行减法
    public int subtract(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("Please enter an integer in the range");
            throw new IllegalArgumentException("Please enter an integer in the range！");
        }else {
            return IntStream.of(numbers).reduce(100, (a, b) -> a-b);
        }
    }

    //2个数相减
    public int subtract(int x, int y) {
        if(x > 99 | x < -99 | y > 99 | y < -99){
            logger.warn("Please enter an integer in the range");
            throw new IllegalArgumentException("Please enter an integer in the range！");
        }else {
            return x - y;

        }
    }


    //平均值 average
    public double average(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("Please enter an integer in the range");
            throw new IllegalArgumentException("Please enter an integer in the range！");
        }else {
            return IntStream.of(numbers).average().getAsDouble();
        }
    }



    //连续拼接
    public String concatStr(String... words) {
        return String.join(" ", words);
    }

}
