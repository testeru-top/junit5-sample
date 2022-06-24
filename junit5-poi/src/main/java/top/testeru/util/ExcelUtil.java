package top.testeru.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname: ExcelUtil
 * @Description: excel文件工具类
 * @Date: 2022/6/6 16:40
 * @Created by top.testeru
 */
public class ExcelUtil {
    static int cellcount;
    static List<HashMap<String, Object>> resultDataList;
    /**
     * 1、文件判断是否存在
     *      如果文件存在则进行下一步
     * 2、获取excel文件类型
     *      不同类型用的解析对象不同
     * 3、打开文件，流的形式
     * 4、获取工作薄对象Workbook
     *      根据不同类型的文件后缀获取
     * 5、
     */
    public static Object readExcel(String pathname){

        FileInputStream fileStream = null;
        try {
            //1、查看文件是否存在
            File excelFile = new File(pathname);
            if (!excelFile.exists()) {
                System.out.println("指定的Excel文件不存在！");
                return null;
            }

            //2、获取Excel后缀名，excel文件类型
            String excelType = pathname.substring(pathname.lastIndexOf(".") + 1, pathname.length());

            //3、流的形式打开文件
            fileStream = new FileInputStream(excelFile);
            //4、根据文件后缀名类型获取对应的工作簿对象
            Workbook workbook = getWorkbook(fileStream, excelType);

            // 读取excel中的数据
            //只读取sheet1
            resultDataList = parseExcel(workbook);
            //读取所有sheet
            //        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {}
            int numberOfSheets = workbook.getNumberOfSheets();
            //关闭流
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultDataList;

    }


    private static List<HashMap<String, Object>> parseExcel(Workbook workbook){
        return parseExcel(workbook,0);
    }
    private static List<HashMap<String, Object>> parseExcel(Workbook workbook, int sheetNum){
        List<HashMap<String,Object>> resultDataList = new ArrayList<>();
        // 解析sheet
        Sheet sheet = workbook.getSheetAt(sheetNum);
        // 校验sheet是否合法
        if (sheet == null) {
            return null;
        }
        // 获取第一行数据
        int firstRowNum = sheet.getFirstRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        if (null == firstRow) {
            System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
        }


        // 解析每一行的数据，构造数据对象
        int rowStart = firstRowNum + 1;
        int rowEnd = sheet.getPhysicalNumberOfRows();
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);

            if (null == row) {
                continue;
            }

            HashMap<String,Object> resultData = convertRowToData(row,firstRow);
            if (resultData.isEmpty()) {
                System.out.println("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                continue;
            }
            resultDataList.add(resultData);
        }
        System.out.println(resultDataList);
        return resultDataList;

    }

    /**
     * 提取每一行中的数据，构造成为一个结果数据对象
     *
     * @param <>
     * @param row
     * @param firstRow
     * @return
     */
    private static HashMap<String,Object> convertRowToData(Row row, Row firstRow) {

        //获取当前sheet页中第一行的最大单元格数
        cellcount = firstRow.getPhysicalNumberOfCells();
        HashMap<String,Object> map = new HashMap<>();

        Cell cell;


        for (int i = 0; i < cellcount; i++) {
            //key
            String firstOne = convertCellToObject(firstRow.getCell(i)).toString();
            cell = row.getCell(i);
            //value
            Object location = convertCellToObject(cell);
            map.put(firstOne,location);

        }
        return map;
    }

    /**
     *
     * @param fileStream 文件输入流
     * @param excelType  excel文件后缀名 xlsx和xls
     * @return  Workbook包含文件数据的工作簿对象
     * @throws IOException
     */
    private static Workbook getWorkbook(FileInputStream fileStream, String excelType) throws IOException {
        Workbook workbook = null;
        if(excelType.equalsIgnoreCase("xls")){
            workbook = new HSSFWorkbook(fileStream);
        }else if(excelType.equalsIgnoreCase("xlsx")){
            workbook = new XSSFWorkbook(fileStream);
        }
        return workbook;

    }

    /**
     * 将单元格内容转换为对应类型object
     * @param cell
     */
    static Object convertCellToObject(Cell cell){
        if(cell==null){
            return null;
        }
        Object returnValue = null;
        switch (cell.getCellType()) {
            //单元格类型枚举值为STRING时，将使用Cell接口的getRichStringCellValue()方法读取内容：
            //文本格式的内容读取
            case STRING:  //文本
                returnValue = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:  // 数字、日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期型以年-月-日格式存储
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    returnValue = fmt.format(cell.getDateCellValue());
                } else {
                    returnValue = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN: // 布尔
                returnValue = cell.getBooleanCellValue();
                break;
            case FORMULA: // 公式
                returnValue = cell.getCellFormula();
                break;
            case BLANK:     // 空值
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }
}
