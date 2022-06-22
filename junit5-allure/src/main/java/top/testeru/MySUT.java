package top.testeru;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @program: junit5_samples
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/6/20 1:59 PM
 */
public class MySUT implements Closeable {

    static final Logger log = getLogger(lookup().lookupClass());

    String name;
    String id;

    public MySUT(String name) {
        this.name = name;
        log.info("{} created", name);
    }

    public void initId() {
        id = UUID.randomUUID().toString();
        log.info("Id created: {}", id);
    }

    public void releaseId() {
        if (id == null) {
            throw new IllegalArgumentException(name + " not initilized");
        }
        log.info("Id released: {}", id);
        id = null;
    }

    @Attachment(value = "screentshot", type = "image/png")
    public byte[] saveScreenshot(String path) {
        File file = new File(path);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] image = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", bos);
            image = bos.toByteArray();
        } catch (Exception e) { }

        // if decoding is not necessary just return image
        return image != null ? Base64.getMimeDecoder().decode(image) : null;
    }
    //连续添加
    @Step("Num: {numbers}.")
    public int sum(int... numbers) {
//        saveScreenshot("")
        if(Arrays.stream(numbers).anyMatch(u -> u == 100)){
            log.warn("integer is 100！");
            throw new NumberFormatException("integer is 100！");
        }else if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            throw new IllegalArgumentException("请输入范围内的整数！");

        }else {
            return IntStream.of(numbers).sum();
        }

    }


    //从100进行减法
    public int subtract(int... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            throw new IllegalArgumentException("请输入范围内的整数！");
        }else {
            return IntStream.of(numbers).reduce(100, (a, b) -> a-b);
        }
    }

    public int subtract(int x,int y) {
        if(x>99 | x<-99 | y>99 | y<-99){
            log.warn("请输入范围内的整数");
            return 0;
        }else {
            return x-y;

        }
    }


    //平均值 average
    public double average(int... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            log.warn("请输入范围内的整数");
            return 0;
        }else {
            return IntStream.of(numbers).average().getAsDouble();
        }
    }

    public String concatenate(String... words) {
        return String.join(" ", words);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void close() {
        log.info("{} closed", name);
    }

}
