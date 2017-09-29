package com.ctmp01.web.util.excel;

import com.ctmp01.web.util.data.DataframeInDC;
import org.apache.curator.utils.CloseableUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */
public class ExcelUtils {
    public static String getExcelCellValue(XSSFCell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    // 数字类型处理
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    cellValue = bd.setScale(3, BigDecimal.ROUND_UP).toString();
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula();
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "";
                    break;
                default:
                    cellValue = "";
                    break;
            }
        }
        return cellValue.trim();
    }

    public static String getExcelCellValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    String result = "";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                                .getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else {// 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        Date date = cell.getDateCellValue();
                        result = sdf.format(date);
                    } else if (cell.getCellStyle().getDataFormat() == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        result = sdf.format(date);
                    } else {
                        double value = cell.getNumericCellValue();
                        CellStyle style = cell.getCellStyle();
                        DecimalFormat format = new DecimalFormat();
                        String temp = style.getDataFormatString();
                        // 单元格设置成常规
                        if (temp.equals("General")) {
                            format.applyPattern("#");
                        }
                        result = format.format(value);
                    }
                    return result;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula();
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "";
                    break;
                default:
                    cellValue = "";
                    break;
            }
        }
        return cellValue.trim();
    }

    public static DataframeInDC readStandardXlsxFile(String xlsxFilePath,
                                                     String sheetName) throws Exception {
        XSSFWorkbook wb = null;
        FileInputStream fIns = null;
        try {
            fIns = new FileInputStream(new File(xlsxFilePath));
            wb = new XSSFWorkbook(fIns);
            // sheetName=基因分析
            XSSFSheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new Exception("Excel缺少sheet页[" + sheetName + "]");
            }
            return getDataframeFromXSSFSheet(sheet);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(fIns);
        }
        return null;
    }

    public static DataframeInDC readStandardXlsxFile(String xlsxFilePath,
                                                     int sheetId) throws Exception {
        XSSFWorkbook wb = null;
        FileInputStream fIns = null;
        try {
            fIns = new FileInputStream(new File(xlsxFilePath));
            wb = new XSSFWorkbook(fIns);
            // sheetName=基因分析
            XSSFSheet sheet = wb.getSheetAt(sheetId);
            if (sheet == null) {
                throw new Exception("Excel缺少sheet页[" + sheetId + "]");
            }
            return getDataframeFromXSSFSheet(sheet);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(fIns);
        }
        return null;
    }

    private static DataframeInDC getDataframeFromXSSFSheet(XSSFSheet sheet)
            throws Exception {
        System.out.println("sheet.getName():" + sheet.getSheetName());
        int nLastRowNum = sheet.getLastRowNum();

        // 读取数据，结合背景颜色进行过滤
        DataframeInDC dataframe = new DataframeInDC();
        for (int i = 0; i < nLastRowNum; i++) {
            if (i == 0) {
                // 标题栏
                XSSFRow row = sheet.getRow(i);
                List<String> colNames = new ArrayList<String>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);
                    String cellValue = ExcelUtils.getExcelCellValue(cell);
                    if (cellValue == null || cellValue.trim().equals("")) {
                        cellValue = "Column" + j;
                    } else {
                        cellValue = cellValue.trim();
                    }
                    colNames.add(cellValue);
                }

                dataframe.setColNames(colNames);
                dataframe.initBlankCols();
                continue;
            }

            XSSFRow row = sheet.getRow(i);
            int nColCounts = dataframe.getColCounts();
            String[] rowValues = new String[nColCounts];
            for (int j = 0; j < nColCounts; j++) {
                if (j >= row.getLastCellNum()) {
                    rowValues[j] = "";
                } else {
                    XSSFCell cell = row.getCell(j);
                    String cellValue = ExcelUtils.getExcelCellValue(cell);
                    rowValues[j] = cellValue.trim();
                }
            }
            dataframe.addRow(rowValues);
        }

        return dataframe;
    }
}
