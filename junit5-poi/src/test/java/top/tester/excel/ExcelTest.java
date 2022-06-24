package top.tester.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname: excel
 * @Description: excel文件解析
 * @Date: 2022/6/6 16:00
 * @Created by top.testeru
 */
public class ExcelTest {
    @Test
    void test() throws IOException, InvalidFormatException {

        //方式一：流的形式打开文件
        //使用 InputStream 比使用 File 需要更多内存，因此 File 可用
//        FileInputStream file = new FileInputStream(new File("src/test/resources/orderLines.xlsx"));
//        Workbook workbook = new XSSFWorkbook(file);
        //方式二：直接使用file
        Workbook workbook = new XSSFWorkbook(new File("src/test/resources/orderLines.xlsx"));
        //读取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //第一个sheet中有多少行
//        int rows = sheet.getPhysicalNumberOfRows();
        //map<行号,list是行内容>
        Map<Integer, List<Object>> data = new HashMap<>();

        int i = 0;
        for (Row row : sheet) {
            //{0=[]}
            data.put(i, new ArrayList<Object>());
            System.out.println("data："+ data);
            for (Cell cell : row) {
                List<Object> objects = data.get(Integer.valueOf(i));
                switch (cell.getCellType()) {
                    //单元格类型枚举值为STRING时，将使用Cell接口的getRichStringCellValue()方法读取内容：
                    //文本格式的内容读取
                    case STRING:
                        objects.add(cell.getRichStringCellValue().getString());
                        break;
                    // 数字、日期
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            //日期型以年-月-日格式存储
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            objects.add(fmt.format(cell.getDateCellValue()) + "");
                        } else {
                            objects.add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        objects.add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        objects.add(cell.getCellFormula() + "");
                        break;
                    default: data.get(Integer.valueOf(i)).add(" ");
                }
            }
            i++;
        }
        System.out.println(data);
        //关闭流
        workbook.close();
    }
}
