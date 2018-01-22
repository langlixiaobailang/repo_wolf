package com.cmos.web.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    
    private  static Log log = LogFactory.getLog(ExcelUtil.class);
    
    private ExcelUtil(){}
    /**
     *  ExcelUtil.export(request,response,"工作量明细数据导出结果-" + DateFormatUtils.format(new Date(), "yyyyMMdd"),titles, data);
     * 导出Excel数据，单个sheet
     * @param response
     * @param excelName    excel名称
     * @param titles       excel标题
     * @param dataList          数据集合
     */
    public static void export(HttpServletRequest request,HttpServletResponse response,
                              String excelName, Object[] titles, List<Object[]> dataList){
        BufferedInputStream bis = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            exportExcel(excelName, titles, dataList, os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = excelName + ".xls";
            String agent = (String)request.getHeader("USER-AGENT");
            //针对IE或者以IE为内核的浏览器：
            if (agent.contains("MSIE")||agent.contains("Trident")) {
                String  enableFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            } else {
                //非IE浏览器的处理：
                String  enableFileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            }
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                out.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.debug(e);
        }finally {
            try {
                if(null != bis){
                    bis.close();
                }
            } catch (IOException e) {
                log.debug(e);
            }
        }
    }
    /**
     * 解决excel导出IE乱码问题
     * @param s
     * @return
     */
    private static String toUtf8String(String s){ 
        StringBuilder sb = new StringBuilder(); 
        for (int i=0;i<s.length();i++){ 
            char c = s.charAt(i); 
            if (c >= 0 && c <= 255){sb.append(c);} 
            else{ 
                byte[] b; 
                try { b = Character.toString(c).getBytes("utf-8");} 
                catch (Exception ex) { 
                    System.out.println(ex); 
                    b = new byte[0]; 
                } 
                for (int j = 0; j < b.length; j++) { 
                    int k = b[j]; 
                    if (k < 0) k += 256; 
                    sb.append("%" + Integer.toHexString(k).toUpperCase()); 
                } 
            } 
        } 
        return sb.toString(); 
    }
    /**
     * 具体Excel数据操作，单个sheet
     * @param excelName
     * @param titles
     * @param dataList
     * @param os
     */
    private static void exportExcel(String excelName, Object[] titles, List<Object[]> dataList, OutputStream os){
        try {
            // 声明一个工作薄  
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格  
            HSSFSheet sheet = workbook.createSheet(excelName);
            // 设置表格默认列宽度为15个字节  
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式  
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式  
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体  
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式  
            style.setFont(font);
            // 生成并设置另一个样式  
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setFillForegroundColor(HSSFColor.WHITE.index);
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体  
            HSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式  
            style2.setFont(font2);
            // 声明一个画图的顶级管理器  
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            // 定义注释的大小和位置,详见文档  
            HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
            // 设置注释内容  
            //comment.setString(new HSSFRichTextString("请勿改变已有格式内容！"));  
            // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
            comment.setAuthor("liuyangyang");
            //excel的表头
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle contextstyle =workbook.createCellStyle();
            HSSFCell cell;
            HSSFRichTextString text;
            //循环所有的表头
            for (short i = 0; i < titles.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                text = new HSSFRichTextString(titles[i] == null ? "" : titles[i].toString());
                cell.setCellValue(text);
            }
            for (int r = 0; r < dataList.size(); r++) {
                row = sheet.createRow(r + 1);
                Object array[] = dataList.get(r);
                for (int i = 0; i < array.length; i++) {
                    cell = row.createCell(i);
                    if (array[i] != null && array[i] != "") {
                        //判断data是否为数值型
                        boolean isNum = array[i].toString().matches("^(-?\\d+)(\\.\\d+)?$");
                        //判断data是否为整数（小数部分是否为0）
                        boolean  isInteger = array[i].toString().matches("^[-\\+]?[\\d]*$");
                        //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                        if (isNum || isInteger) {
                            // 设置单元格格式
                            cell.setCellStyle(contextstyle);
                            // 设置单元格内容为double类型
                            cell.setCellValue(Double.parseDouble(array[i].toString()));
                        } else {
                            cell.setCellStyle(contextstyle);
                            // 设置单元格内容为字符型
                            cell.setCellValue(array[i].toString());
                        }
                    }
                }
            }
            workbook.write(os);
        } catch (Exception e) {
            log.debug(e);
        }  
    }  
    /*
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     */
    public static List<String[][]> parseExcelData(String fileName, InputStream inputStream, int ignoreRows){
        List<String[][]> resultList = new ArrayList<String[][]>();
        try {
            Workbook wb = getWorkbook(inputStream);
            Cell cell;
            int rowSize = 0;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                List<String[]> result = new ArrayList<String[]>();
                Sheet sheet = wb.getSheetAt(sheetIndex);
                // 第一行为标题，不取
                for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    String[] values = new String[rowSize-1];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            // 注意：一定要设成这个，否则可能会出现乱码
                            //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                        } else {
                                            value = "";
                                        }
                                    } else {
                                        DecimalFormat df = new DecimalFormat("#.#########");//转换成整型
                                        value = df.format(cell.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    // 导入时如果为公式生成的数据则无值
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    value = "";
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                    break;
                                default:
                                    value = "";
                            }
                        }
                        values[columnIndex] = value.replaceAll("^[ |　](.*)[ |　]$","$1");
                        hasValue = true;
                    }
                    if (hasValue) {
                        result.add(values);
                    }
                }
                if(!result.isEmpty()){
                    String[][] returnArray = new String[result.size()][rowSize-1];
                    for (int i = 0; i < returnArray.length; i++) {
                        returnArray[i] = (String[]) result.get(i);
                    }
                    resultList.add(returnArray);
                }
                rowSize = 0;
            }
            return resultList;
        } catch (Exception e) {
            log.debug(e);
        }
        return null;
    }

    /**
     * 根据类型判断当
     * @param inputStreams
     * @return
     */
    public static Workbook getWorkbook(InputStream inputStreams){
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            Workbook workbook;
            InputStream inputStream;
            if(!inputStreams.markSupported()) {
                inputStream = new PushbackInputStream(inputStreams, 8);
            }else {
                inputStream = inputStreams;
            }
            if(POIFSFileSystem.hasPOIFSHeader(inputStream)) {//2007以上版本
                workbook = new HSSFWorkbook(inputStream);
            }else {
                workbook = new HSSFWorkbook(inputStream);
            }
            return  workbook;
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

