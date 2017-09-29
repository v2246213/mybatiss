package com.ctmp01.web.util.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ctmp01.web.util.StringUtil;
import com.ctmp01.web.util.StringUtils;
import com.ctmp01.web.util.list.AnalysisListUtils;
import org.apache.curator.utils.CloseableUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @title 数据框架工具.java
 * @Description TODO
 * @author liuxinyi
 * @date 2016年11月29日 下午12:56:55
 * @version 1.0.0
 */
public class DataframeInDCUtils {

	private DataframeInDC m_Dataframe = null;

	private Map<String, Map<String, Integer>> m_GroupedValue = new HashMap<String, Map<String, Integer>>();

	private Map<String, Map<String, String>> m_DigitizationInfo = new HashMap<String, Map<String, String>>();

//	private Map<String, Double> m_SumsValue = new HashMap<String, Double>();

	private int m_RowCounts = 0;

	public DataframeInDCUtils(DataframeInDC dataframe) {
		super();

		m_Dataframe = dataframe;
	}

	/**
	 * 获取某一行数据
	 * 
	 * @param dc
	 *            数据框架
	 * @param rowIndex
	 *            行索引
	 * @return
	 */
	public static Map<String, Object> getRowMap(DataframeInDC dc, int rowIndex) {
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		try {
			List<String> columnNameList = dc.getColNames();
			String[] row = dc.getRow(rowIndex);
			if (row != null && columnNameList.size() == row.length) {
				for (int i = 0; i < columnNameList.size(); i++) {
					String columnName = StringUtils
							.getStr(columnNameList.get(i));
					String value = StringUtils.getStr(row[i]);
					dataMap.put(columnName, value);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;
	}

	public int counts(String colName) {
		ArrayList<Object> o = m_Dataframe.getColValue(colName);
		if (o == null)
			return 0;

		return o.size();
	}

	public int counts(String colName, String colValue) {
		ArrayList<Object> colValues = m_Dataframe.getColValue(colName);
		if (colValues == null) {
			return 0;
		}
		int counts = 0;
		String colValue0 = colValue.trim();
		for (int i = 0; i < colValues.size(); i++) {
			String s = String.valueOf(colValues.get(i));
			if (s.equals(colValue0)) {
				counts++;
			}
		}
		return counts;
	}

	public int counts(String yColName, String yColValue, String[] xColNames,
			String[] xColValues) throws Exception {
		if (xColNames.length != xColValues.length) {
			throw new Exception("变量长度不一致");
		}

		String[] selColNames = new String[xColNames.length + 1];
		String[] selColValues = new String[xColValues.length + 1];
		System.arraycopy(xColNames, 0, selColNames, 0, xColNames.length);
		System.arraycopy(xColValues, 0, selColValues, 0, xColValues.length);
		selColNames[selColNames.length - 1] = yColName;
		selColValues[selColValues.length - 1] = yColValue;

		// 1.开始扫描行，根据行内容来判定是否符合选择
//		long l = System.currentTimeMillis();
		int counts = 0;
		if (m_RowCounts == 0) {
			m_RowCounts = m_Dataframe.getRowCounts();
		}
		int rowCounts = m_RowCounts;
		for (int rowIndex = 0; rowIndex < rowCounts; rowIndex++) {
			boolean bIsEquals = true;
			for (int nIndex = 0; nIndex < selColNames.length; nIndex++) {
				String sColName = selColNames[nIndex];
				if (sColName.equals("")) {
					continue;
				}
				String sColValue = selColValues[nIndex];
				String sColValue0 = m_Dataframe.getColValue(rowIndex, sColName);
				bIsEquals = bIsEquals && sColValue.equals(sColValue0);
			}
			if (bIsEquals) {
				counts = counts + 1;
			}
		}
		return counts;
	}

	public String[] getDistinctValues(String colName) {
		List<Object> distinctValues = new ArrayList<Object>();
		ArrayList<Object> o = m_Dataframe.getColValue(colName);
		if (o == null) {
			return new String[] {};
		}

		Map<String, String> hmValues = new HashMap<String, String>();
		for (int i = 0; i < o.size(); i++) {
			String s = String.valueOf(o.get(i));
			s = s.trim();
			if (hmValues.containsKey(s)) {
				continue;
			}
			distinctValues.add(s);
			hmValues.put(s, s);
		}
		String[] arDistinctValues = new String[distinctValues.size()];
		distinctValues.toArray(arDistinctValues);
		return arDistinctValues;
	}

	/**
	 * 获取分组后数量最多的值
	 * 
	 * @param colName
	 * @return
	 */
	public String[] getMaxGroupedNumColValue(String colName) {
		// m_GroupedValue
		Map<String, Integer> hmGroupedValue = null;
		if (m_GroupedValue.containsKey(colName)) {
			// 如果已经缓存，从缓存中获取值
			hmGroupedValue = m_GroupedValue.get(colName);
		} else {
			// 计算列的distinct value的数量
			Map<String, Integer> hmValues = new HashMap<String, Integer>();
			String[] distinctValues = getDistinctValues(colName);
			for (int i = 0; i < distinctValues.length; i++) {
				String s = distinctValues[i];
				int ncounts = counts(colName, s);
				hmValues.put(s, ncounts);
			}
			m_GroupedValue.put(colName, hmValues);
			hmGroupedValue = hmValues;
		}

		// 从这里面选择最大的
		Iterator<Map.Entry<String, Integer>> entrys = hmGroupedValue.entrySet()
				.iterator();
		int maxCounts = 0;
		String sColValue = "";
		while (entrys.hasNext()) {
			Map.Entry<String, Integer> entry = entrys.next();
			String s = entry.getKey();
			int nCounts = entry.getValue();
			if (nCounts > maxCounts) {
				maxCounts = nCounts;
				sColValue = s;
			}
		}
		return new String[] { sColValue, String.valueOf(maxCounts) };
	}

	/**
	 * 联合行
	 *
	 * <p>
	 * 格式为：Xi1=XVi1;Xi2=XVi2 其中;为colDelims
	 * </p>
	 *
	 * @param rowIndex
	 * @return
	 */
	public String combineRow(int rowIndex, char colDelims) {
		int nColCounts = m_Dataframe.getColCounts();
		StringBuffer oStringBuffer = new StringBuffer();
		for (int colIndex = 0; colIndex < nColCounts; colIndex++) {
			if (colIndex > 0) {
				oStringBuffer.append(colDelims);
			}
			String colName = m_Dataframe.getColNameAt(colIndex);
			String colValue = m_Dataframe.getColValue(rowIndex, colIndex);
			oStringBuffer.append(colName).append("=").append(colValue);
		}
		return oStringBuffer.toString();
	}

	/**
	 * 仅在选中的列中联合行
	 * 
	 * @param pColNames
	 * @param rowIndex
	 * @param colDelims
	 * @return
	 */
	public String combineRow(List<String> pColNames, int rowIndex,
			char colDelims) {
		// long lStartTime = System.currentTimeMillis();
		StringBuffer oStringBuffer = new StringBuffer(2048);
		int counts = 0;
		for (int i = 0; i < pColNames.size(); i++) {
			String colName = pColNames.get(i);
			if (colName == null || colName.trim().equals("")) {
				continue;
			}

			int colIndex = m_Dataframe.getColIndex(colName);
			if (counts > 0) {
				oStringBuffer.append(colDelims);
			}

			String colValue = m_Dataframe.getColValue(rowIndex, colIndex);
			oStringBuffer.append(colName).append("=").append(colValue);
			counts++;
		}
		// System.out.println("combine use time:"+(System.currentTimeMillis()-lStartTime));
		return oStringBuffer.toString();
	}

	/**
	 * 解除行的联合.
	 *
	 * <p>
	 * ArrayList 返回值，第一个返回的是ColNames，第二个返回的是ColValues
	 * </p>
	 * 
	 * @param sCombined
	 * @param colDelims
	 * @return
	 */
	public ArrayList<Object> uncombineRow(String sCombined, char colDelims) {
		List<String> colNames = new ArrayList<String>();
		List<String> colValues = new ArrayList<String>();
		String[] colParts = StringUtils.split(sCombined, colDelims, false);
		for (int i = 0; i < colParts.length; i++) {
			String col0 = colParts[i];
			int nIndexOf = col0.indexOf("=");
			String colName = col0.substring(0, nIndexOf);
			String colValue = col0.substring(nIndexOf + 1, col0.length());
			colNames.add(colName);
			colValues.add(colValue);
		}
		ArrayList<Object> v = new ArrayList<Object>();
		v.add(colNames);
		v.add(colValues);
		return v;
	}

	/**
	 * 将数据集合输出到Excel
	 *
	 * @param sFile
	 * @throws Exception
	 */
	public void writeIntoXslx(String sFile, String sheetName, int titleRows,
			String titleRowSep) throws Exception {
		FileOutputStream fOuts = null;
		try {
			File fFile = new File(sFile);
			if (!fFile.getParentFile().exists()) {
				fFile.getParentFile().mkdirs();
			}
			XSSFWorkbook wb = null;
			wb = new XSSFWorkbook(); // 创建Excel工作薄对象
			fOuts = new FileOutputStream(sFile);

			if (sheetName == null || sheetName.equals(""))
				sheetName = "工作表0";
			XSSFSheet sheet = wb.createSheet(sheetName);

			// 添加标题行
			if (true) {
				XSSFCellStyle cellStyle = wb.createCellStyle();
				Font ztFont = wb.createFont();
				ztFont.setFontHeightInPoints((short) 16);
				cellStyle.setFont(ztFont);
				Map<Integer, XSSFRow> rowsMap = new HashMap<Integer, XSSFRow>();
				for (int i = 0; i < m_Dataframe.getColNames().size(); i++) {
					String colName = m_Dataframe.getColNameAt(i);
					String[] colNames = new String[] { colName };
					if (titleRows > 1) {
						colNames = StringUtils.split(colName, titleRowSep);
					}
					for (int j = 0; j < titleRows; j++) {
						XSSFRow row = rowsMap.get(j);
						if (rowsMap.get(j) == null) {
							row = sheet.createRow(j);
							rowsMap.put(j, row);
						}
						XSSFCell ztCell = row.createCell(i);
						ztCell.setCellStyle(cellStyle);
						if (colNames.length > j) {
							ztCell.setCellValue(colNames[j]);
						} else {
							ztCell.setCellValue("");
						}
					}
				}
			}

			// 添加内容行
			if (true) {
				int rowcounts = m_Dataframe.getRowCounts();
				XSSFCellStyle cellStyle = wb.createCellStyle();
				Font ztFont = wb.createFont();
				ztFont.setFontHeightInPoints((short) 14);
				cellStyle.setFont(ztFont);
				for (int i = 0; i < rowcounts; i++) {
					XSSFRow row = sheet.createRow(i + titleRows); // 注意标题行，1为标题行的数量

					// 将列的值写入
					int colcounts = m_Dataframe.getColCounts();
					for (int j = 0; j < colcounts; j++) {
						String colValue = m_Dataframe.getColValue(i, j);
						XSSFCell ztCell = row.createCell(j);
						ztCell.setCellValue(colValue);
						ztCell.setCellStyle(cellStyle);
					}
				}
			}
			wb.write(fOuts);
			fOuts.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fOuts.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 将数据集写入到文件中
	 * 
	 * @param sFile
	 * @param fieldDelims
	 * @param nFileType
	 * @throws Exception
	 */
	public void writeIntoFile(String sFile, char fieldDelims, int nFileType)
			throws Exception {
		if (nFileType != 1) {
			throw new Exception("暂时不支持除tsv格式之外的数据.");
		}

		FileOutputStream fOuts = null;
		BufferedWriter writer = null;
		try {
			File parentFile = new File(sFile).getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			fOuts = new FileOutputStream(sFile);
			writer = new BufferedWriter(new OutputStreamWriter(fOuts, "UTF-8"));
			String lineSep = System.getProperty("line.separator");
			// 1.输出头信息
			List<String> colNames = m_Dataframe.getColNames();
			StringBuffer oStringBuffer = new StringBuffer();
			for (int i = 0; i < colNames.size(); i++) {
				if (i > 0)
					oStringBuffer.append(fieldDelims);
				oStringBuffer.append(colNames.get(i));
			}
			writer.write(oStringBuffer.toString() + lineSep);

			// 2.输出行数据
			int rowCounts = m_Dataframe.getRowCounts();
			int colCounts = m_Dataframe.getColCounts();
			for (int rowIndex = 0; rowIndex < rowCounts; rowIndex++) {
				oStringBuffer.delete(0, oStringBuffer.length());
				for (int colIndex = 0; colIndex < colCounts; colIndex++) {
					if (colIndex > 0) {
						oStringBuffer.append(fieldDelims);
					}
					String value = m_Dataframe.getColValue(rowIndex, colIndex);
					if (value == null) {
						value = "";
					}
					oStringBuffer.append(value);
				}
				writer.write(oStringBuffer.toString() + lineSep);
			}

			writer.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			CloseableUtils.closeQuietly(fOuts);
			CloseableUtils.closeQuietly(writer);
		}
	}

	/**
	 * 从文件中读取数据集
	 * 
	 * @param inputFile
	 * @param fieldDelims
	 * @param nFileType
	 * @return
	 * @throws Exception
	 */
	public static DataframeInDC readFromFile(String inputFile,
			char fieldDelims, int nFileType) throws Exception {
		return readFromFile(inputFile, -1, fieldDelims, nFileType);
	}

	/**
	 * 从文件中读取数据集，限定行数
	 * 
	 * @param inputFile
	 * @param lMaxLines
	 * @param fieldDelims
	 * @param nFileType
	 * @return
	 * @throws Exception
	 */
	public static DataframeInDC readFromFile(String inputFile, long lMaxLines,
			char fieldDelims, int nFileType) throws Exception {
		if (nFileType != 1) {
			throw new Exception("暂时不支持除tsv格式之外的数据.");
		}

		FileInputStream fIns = null;
		BufferedReader reader = null;
		try {
			DataframeInDC dataframe = new DataframeInDC();
			fIns = new FileInputStream(inputFile);
			reader = new BufferedReader(new InputStreamReader(fIns, "UTF-8"));
			String line;
			int lineCount = 0;
			while ((line = reader.readLine()) != null) {
				if (lMaxLines != -1 && lineCount >= lMaxLines) {
					break;
				}
				if (line.trim().length() == 0) {
					continue;
				}
				System.out.println("lineCount:" + lineCount);
				if (lineCount == 0) {
					String[] lineParts = StringUtils.split(line, fieldDelims,
							false);
					List<String> colNames = new ArrayList<String>(
							lineParts.length);
					Map<String, String> hmColNames = new HashMap<String, String>();
					for (int i = 0; i < lineParts.length; i++) {
						String colName = lineParts[i];
						if (hmColNames.containsKey(colName)) {
							colName = colName + "_#_" + i;
						}
						colNames.add(colName);
						hmColNames.put(lineParts[i], lineParts[i]);
					}
					dataframe.setColNames(colNames);
					System.out.println("colNames.size():" + colNames.size());
					dataframe.initBlankCols();
					lineCount++;
					continue;
				}

				String[] lineParts = StringUtils.split(line, fieldDelims, false);
				dataframe.addRow(lineParts);
				lineCount++;
			}

			return dataframe;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			CloseableUtils.closeQuietly(reader);
			CloseableUtils.closeQuietly(fIns);
		}
	}

	/**
	 * 对变量X列上面的值，根据Y列的值进行分组
	 * 
	 * @param yColName
	 * @param xColName
	 * @return
	 */
	public Map<String, Map<String, Integer>> groupWithY(String yColName,
			String xColName) throws Exception {
		String[] yDistinctValues = getDistinctValues(yColName);
		String[] xDistinctValues = getDistinctValues(xColName);
		Map<String, Map<String, Integer>> hmGrouped = new HashMap<String, Map<String, Integer>>();
		for (int xvIndex = 0; xvIndex < xDistinctValues.length; xvIndex++) {
			String xValue = xDistinctValues[xvIndex];
			Map<String, Integer> hm0 = new HashMap<String, Integer>();
			for (int yvIndex = 0; yvIndex < yDistinctValues.length; yvIndex++) {
				String yValue = yDistinctValues[yvIndex];
				int n = counts(yColName, yValue, new String[] { xColName },
						new String[] { xValue });
				hm0.put(yValue, n);
			}
			hmGrouped.put(xValue, hm0);
		}

		return hmGrouped;
	}

	/**
	 * 对数据集进行数字化
	 * 
	 * @param pIncludeCols
	 * @param pExcludeCols
	 * @return
	 * @throws Exception
	 */
	public DataframeInDC digitization(String[] pIncludeCols,
			String[] pExcludeCols, int nStartValue, boolean bKeepDigitColValue)
			throws Exception {
		if (m_Dataframe.getRowCounts() == 0) {
			// 返回原始的Dataframe对象
			return m_Dataframe;
		}

		Map<String, Map<String, String>> hmDigitizationInfos = new HashMap<String, Map<String, String>>();
		DataframeInDC dataframeDigited = new DataframeInDC();
		List<String> colNames = m_Dataframe.getColNames();
		dataframeDigited.setColNames(AnalysisListUtils.cloneList(colNames));
		for (int i = 0; i < colNames.size(); i++) {
			String colName = colNames.get(i);
			if (pIncludeCols != null && pIncludeCols.length > 0) {
				// colName必须在pIncludeCols中
				if (!StringUtils.isInArray(colName, pIncludeCols, false)) {
					dataframeDigited.updateColValue(colName,
							m_Dataframe.getColValue(colName));
					continue;
				}
			}

			if (pExcludeCols != null && pExcludeCols.length > 0) {
				// colName必须不在pExcludeCols中
				if (StringUtils.isInArray(colName, pExcludeCols, false)) {
					dataframeDigited.updateColValue(colName,
							m_Dataframe.getColValue(colName));
					continue;
				}
			}

			// 开始对列进行数字化，获取列数字化的信息
			Map<String, String> colDigizationInfo = getColDigitizationInfo(
					nStartValue, bKeepDigitColValue, colName);
			// 开始对列进行转换
			ArrayList<Object> vDigitedValue = new ArrayList<Object>();
			ArrayList<Object> vColValue = m_Dataframe.getColValue(colName);
			for (int j = 0; j < vColValue.size(); j++) {
				String colValue = (String) vColValue.get(j);
				colValue = colValue.trim();
				String digitedValue = colDigizationInfo.get(colValue);
				vDigitedValue.add(digitedValue);
			}

			// 保存列的数字化信息
			hmDigitizationInfos.put(colName, colDigizationInfo);
			dataframeDigited.updateColValue(colName, vDigitedValue);
		}
		m_DigitizationInfo = hmDigitizationInfos;
		return dataframeDigited;
	}

	/**
	 * 获取指定列的数字化值
	 * 
	 * @param nStartValue
	 * @param bKeepDigitColValue
	 * @param colName
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getColDigitizationInfo(int nStartValue,
			boolean bKeepDigitColValue, String colName) throws Exception {
		String[] arDistinctValues = getDistinctValues(colName);
		Map<String, String> colDigizationInfo = new HashMap<String, String>();
		for (int j = 0; j < arDistinctValues.length; j++) {
			int nDigit = nStartValue + j;
			String currValue = arDistinctValues[j];
			double d = 0;
			if (isDigit(currValue)) {
				// 本身是数字，
				if (bKeepDigitColValue) {
					d = Double.parseDouble(currValue.trim());
				} else {
					d = nDigit;
				}
			} else {
				d = nDigit;
			}
			colDigizationInfo.put(currValue, String.valueOf(d));
		}
		return colDigizationInfo;
	}

	/**
	 * 返回列值的第一个位置，没有找到，返回-1
	 * 
	 * @param colName
	 * @param sValue
	 * @param isIgnoreCase
	 * @return
	 */
	public int indexOfColValue(String colName, String sValue,
			boolean isIgnoreCase) {
		int nIndexOf = -1;
		String sValue0 = sValue.trim();
		ArrayList<Object> v0 = m_Dataframe.getColValue(colName);
		if (v0 == null)
			return -1;

		for (int i = 0; i < v0.size(); i++) {
			String sCurr = StringUtils.getStr(v0.get(i));
			if (sCurr == null) {
				continue;
			}

			sCurr = sCurr.trim();
			if (isIgnoreCase && sCurr.equalsIgnoreCase(sValue0)) {
				nIndexOf = i;
				break;
			}
			if (!isIgnoreCase && sCurr.equals(sValue0)) {
				nIndexOf = i;
				break;
			}
		}
		return nIndexOf;
	}

	/**
	 * 对数据集做矩阵转置 将行-列进行转换
	 *
	 * @param pColAsRowName
	 * @param sRowNameForColName
	 * @return
	 */
	public DataframeInDC transpose(String pColAsRowName,
			String sRowNameForColName) throws Exception {
		DataframeInDC dataframe0 = new DataframeInDC();
		// 处理转置后的数据集的ColNames
		List<String> colNames0 = new ArrayList<String>(
				m_Dataframe.getRowCounts() + 1);
		int indexColValaueAsRowName = m_Dataframe.getColIndex(pColAsRowName);
		colNames0.add(sRowNameForColName);
		for (int i = 0; i < m_Dataframe.getRowCounts(); i++) {
			// System.out.println("i:"+i);
			String colValue = m_Dataframe.getColValue(i,
					indexColValaueAsRowName);
			if (colValue == null || colValue.trim().equals("")) {
				colValue = "__Column__" + i;
			} else {
				colValue = colValue.trim();
			}
			colNames0.add(colValue);
		}

		dataframe0.setColNames(colNames0);
		System.out.println("dataframe0.setColNames finished.");

		dataframe0.initBlankCols(m_Dataframe.getColNames().size());
		System.out.println("dataframe0.initBlankCols finished.");

		// 生成数据拼接进去
//		int nRowCount = m_Dataframe.getRowCounts();
		for (int i = 0; i < m_Dataframe.getColNames().size(); i++) {
			System.out.println("i:" + i + ";"
					+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + "Mb");
			String colName = m_Dataframe.getColNameAt(i).trim();
			if (colName.equalsIgnoreCase(pColAsRowName)) {
				// 忽略掉作为新的表头的信息
				continue;
			}

			// 将列加入进去
			dataframe0.getColValue(sRowNameForColName).add(colName);

			// 将其他的列加入进去
			for (int j = 0; j < m_Dataframe.getRowCounts(); j++) {
				List<Object> newColValues = dataframe0.getColValue(j + 1);
				String colValue = m_Dataframe.getColValue(j, i);
				newColValues.add(colValue);
			}
		}
		return dataframe0;
	}

	/**
	 * 是否是数字
	 * 
	 * @param sValue
	 * @return
	 * @throws Exception
	 */
	private boolean isDigit(String sValue) throws Exception {
		String s = sValue.trim();
		boolean bIsDigit = false;
		try {
			Double.parseDouble(s);
			bIsDigit = true;
		} catch (Exception ex) {
		}
		return bIsDigit;
	}

	/**
	 * 获取最近一次数字化的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map<String, String>> getDigitizationInfo()
			throws Exception {
		if (m_DigitizationInfo.isEmpty()) {
			throw new Exception("请先调用数据集合数字化方法.");
		}

		return m_DigitizationInfo;
	}

	/**
	 * 对指定列进行离散
	 * 
	 * @param colName
	 * @param valueDispersedColName
	 * @param valueDispersed
	 * @return
	 */
	public DataframeInDC valueDispersed(String colName,
			String valueDispersedColName, IValueDispersed valueDispersed)
			throws Exception {
		List<Object> colValues = m_Dataframe.getColValue(colName);
		ArrayList<Object> dispersedColValues = new ArrayList<Object>(colValues.size());
		for (int i = 0; i < colValues.size(); i++) {
			String colValue = StringUtils.getStr(colValues.get(i));
			String sDispersedValue = valueDispersed.getDispersedValue(colValue);
			dispersedColValues.add(sDispersedValue);
		}
		if (colName.equalsIgnoreCase(valueDispersedColName)) {
			m_Dataframe.updateColValue(valueDispersedColName,
					dispersedColValues);
		} else {
			m_Dataframe.addCol(valueDispersedColName, dispersedColValues);
		}
		return m_Dataframe;
	}

	/**
	 * 联合两个数据集，大的数据集在左边
	 * 
	 * @param dataframe0
	 * @param dataframe1
	 * @param unionColumn0
	 * @param unionColumn1
	 * @return
	 * @throws Exception
	 */
	public static DataframeInDC union(DataframeInDC dataframe0,
			DataframeInDC dataframe1, String unionColumn0, String unionColumn1)
			throws Exception {
		DataframeInDC _dataframe0 = dataframe0;
		String _unionColumn0 = unionColumn0;
		DataframeInDC _dataframe1 = dataframe1;
		String _unionColumn1 = unionColumn1;
		if (dataframe0.getColCounts() < dataframe1.getColCounts()) {
			_dataframe0 = dataframe1;
			_unionColumn0 = unionColumn1;
			_dataframe1 = dataframe0;
			_unionColumn1 = unionColumn0;
		}

		// 根据左边的集合中进行联合列的顺序，为右侧构造一个全新的Dataframe
		int nUnionColIndex0 = _dataframe0.getColIndex(_unionColumn0);
		DataframeInDC newDataframe = new DataframeInDC();
		newDataframe.setColNames(_dataframe1.getColNames());
		newDataframe.initBlankCols();
		for (int i = 0; i < _dataframe0.getRowCounts(); i++) {
			String sUnionValue = _dataframe0.getColValue(i, nUnionColIndex0);
			int rowIndex = _dataframe1.getRowIndexOfFirst(_unionColumn1,
					sUnionValue);
			String[] newRowValues = null;
			if (rowIndex != -1) {
				newRowValues = _dataframe1.getRow(rowIndex);
			} else {
				newRowValues = new String[_dataframe1.getColCounts()];
				for (int j = 0; j < newRowValues.length; j++) {
					newRowValues[j] = "";
				}
			}
			newDataframe.addRow(newRowValues);
		}

		// 开始进行联合，现在newDataframe的行顺序和左侧一致了
		DataframeInDC dataframe = _dataframe0;
		for (int i = 0; i < newDataframe.getColCounts(); i++) {
			String newColName = newDataframe.getColNameAt(i);
			if (newColName.equalsIgnoreCase(_unionColumn1))
				continue;
			if (dataframe.getColIndex(newColName) != -1) {
				continue;
			}
			ArrayList<Object> colValues = newDataframe.getColValue(i);
			dataframe.addCol(newColName, colValues);
		}
		return dataframe;
	}

	/**
	 * 对行进行普通抽样。<br/>
	 * 保证抽样出的结果不重复，牺牲一些独立性
	 * 
	 * @param pRetainColNames
	 *            保留的记录条件字段名
	 * @param pRetainColValues
	 *            保留的记录条件字段值
	 * @param pSamplingColNames
	 *            抽样的记录条件字段名
	 * @param pSamplingColValues
	 *            抽样的记录条件字段值
	 * @return
	 */
	public DataframeInDC samplingRows(int samplingCount,
			String[] pRetainColNames, String[] pRetainColValues,
			String[] pSamplingColNames, String[] pSamplingColValues)
			throws Exception {
		DataframeInDC dataframe0 = m_Dataframe.selectChild(pRetainColNames,
				pRetainColValues);
		DataframeInDC dataframe1 = m_Dataframe.selectChild(pSamplingColNames,
				pSamplingColValues);
		if (samplingCount > dataframe1.getRowCounts()) {
			throw new Exception("需要抽样的记录数目太多.");
		}
		Map<Integer, Integer> sampledIndex = new HashMap<Integer, Integer>();
		int n = 0;
		int rowCount = dataframe1.getRowCounts();
		System.out.println("rowCount:" + rowCount);
		while (n < samplingCount) {
			boolean bSampled = false;
			while (!bSampled) {
				int nRandom = new Random(System.currentTimeMillis() + n * 1000)
						.nextInt() % rowCount;
				if (nRandom < 0)
					nRandom = 0 - nRandom;
				System.out.println(nRandom + ":"
						+ sampledIndex.containsKey(nRandom));
				if (!sampledIndex.containsKey(nRandom)) {
					bSampled = true;
					sampledIndex.put(nRandom, nRandom);
				}
			}
			n++;
		}
		Iterator<Integer> keyIter = sampledIndex.keySet().iterator();
		while (keyIter.hasNext()) {
			int rowId = keyIter.next();
			String[] row = dataframe1.getRow(rowId);
			dataframe0.addRow(row);
		}

		return dataframe0;
	}

	/**
	 * 根据字段名和字段值获取行索引
	 * 
	 * @param colNames
	 * @param colValues
	 * @return
	 * @throws Exception
	 */
	public int findRowIndex(String[] colNames, String[] colValues)
			throws Exception {
		int[] colNameIndexes = new int[colNames.length];
		for (int i = 0; i < colNames.length; i++) {
			int colNameIndex = m_Dataframe.getColIndex(colNames[i]);
			colNameIndexes[i] = colNameIndex;
		}

		for (int i = 0; i < m_Dataframe.getRowCounts(); i++) {
			String[] currColValues = m_Dataframe.getRow(i);
			boolean bIsEquals = true;
			for (int j = 0; j < colNames.length; j++) {
				String aColValue = colValues[j];
				String currColValue = currColValues[colNameIndexes[j]];
				if (!aColValue.equalsIgnoreCase(currColValue)) {
					bIsEquals = false;
					break;
				}
			}
			if (bIsEquals) {
				return i;
			}
		}
		return -1;
	}

/*	public static void main(String[] args) {
		try {
			DataframeInDC dc = DataframeInDCUtils
					.readFromFile(
							"E:\\temp\\Xuanwu\\pajinsen-100samples-20160111-monthtable.tsv",
							'\t', 1);
			System.out.println(dc.getColCounts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
