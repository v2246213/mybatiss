package com.ctmp01.web.util.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctmp01.web.util.StringUtil;
import com.ctmp01.web.util.StringUtils;
import com.ctmp01.web.util.list.AnalysisListUtils;

/**
 * 数据集
 * 
 * @title DataframeInDC.java
 * @Description TODO
 * @author liuxinyi
 * @date 2016年11月29日 下午12:57:47
 * @version 1.0.0
 */
public class DataframeInDC {

	private List<String> m_ColNames = new ArrayList<String>();
	private Map<String, ArrayList<Object>> m_ColValues = new HashMap<String, ArrayList<Object>>();
	private Map<Integer, String> m_IndexColNames = new HashMap<Integer, String>();
	private Map<String, Integer> m_ColNameIndexes = new HashMap<String, Integer>();
	private int m_RowsCapacity = -1;

	public DataframeInDC() {
		super();
	}

	public DataframeInDC(int nRowsCapacity) {
		super();

		m_RowsCapacity = nRowsCapacity;
	}

	public void initBlankCols() throws Exception {
		ArrayList<Object> v1 = null;
		for (int i = 0; i < m_ColNames.size(); i++) {
			// System.out.println("i:"+i);
			String colName = m_ColNames.get(i);
			ArrayList<Object> v = null;
			if (m_RowsCapacity == -1) {
				v = new ArrayList<Object>();
			} else {
				v = new ArrayList<Object>(m_RowsCapacity);
			}
			if (i == 0) {
				v1 = v;
			}

			updateColValue(colName, v);
			if (v1 == v && i > 0)
				System.out.println("v.hashCode():" + v.hashCode()
						+ ": v1==v is " + (v1 == v));
		}
	}

	public void initBlankCols(int nCapacity) throws Exception {
		ArrayList<Object> v1 = null;
		for (int i = 0; i < m_ColNames.size(); i++) {
			// System.out.println("i:"+i);
			String colName = m_ColNames.get(i);
			ArrayList<Object> v = new ArrayList<Object>(nCapacity);
			if (i == 0) {
				v1 = v;
			}

			updateColValue(colName, v);
			if (v1 == v && i > 0)
				System.out.println("v.hashCode():" + v.hashCode()
						+ ": v1==v is " + (v1 == v));
		}
	}

	public void addCol(String colName, ArrayList<Object> colValues) throws Exception {
		String sColName = colName.trim();
		if (m_ColNameIndexes.containsKey(sColName)) {
			throw new Exception("数据列[" + sColName + "]存在");
		}

		int nColIndex = m_ColNames.size();
		m_ColNames.add(colName);
		m_ColValues.put(sColName, colValues);
		m_IndexColNames.put(nColIndex, colName);
		m_ColNameIndexes.put(colName, nColIndex);
	}

	public void updateColValue(String colName, ArrayList<Object> colValue)
			throws Exception {
		String sColName = colName.trim();
		if (!m_ColNameIndexes.containsKey(sColName)) {
			throw new Exception("数据列[" + sColName + "]不存在");
		}

		m_ColValues.put(sColName, colValue);
	}

	/**
	 * 获取一个行
	 * 
	 * @param rowIndex
	 * @return
	 * @throws Exception
	 */
	public String[] getRow(int rowIndex) throws Exception {
		try {
			String[] rowValues = new String[m_ColNames.size()];
			for (int i = 0; i < this.getColCounts(); i++) {
				ArrayList<Object> v = getColValue(i);
				if (v != null) {
					if (v.size() <= rowIndex) {
						throw new Exception("列[" + getColNameAt(i) + "]长度不够["
								+ v.size() + "].");
					}
					String rowValue = v.get(rowIndex).toString();
					rowValues[i] = rowValue;
				}
			}
			return rowValues;
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * 更新指定位置的值
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @param value
	 * @throws Exception
	 */
	public void updateCellValue(int rowIndex, int colIndex, String value)
			throws Exception {
		ArrayList<Object> v = getColValue(colIndex);
		v.set(rowIndex, value);
	}

	/**
	 * 追加一个行
	 * 
	 * @param values
	 * @throws Exception
	 */
	public void addRow(String[] values) throws Exception {
		if (values.length != m_ColNames.size()) {
			throw new Exception("行值的数量和集合的列不匹配");
		}

		for (int i = 0; i < values.length; i++) {
			ArrayList<Object> v = getColValue(i);
			String value = values[i];
			if (value == null)
				value = "";
			v.add(value);
		}
	}

	/**
	 * 更新一个行的值
	 * 
	 * @param values
	 * @param rowIndex
	 * @throws Exception
	 */
	public void updateRow(String[] values, int rowIndex) throws Exception {
		if (values.length != m_ColNames.size()) {
			throw new Exception("行值的数量和集合的列不匹配");
		}

		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			if (value == null)
				value = "";

			ArrayList<Object> v = getColValue(i);
			v.set(rowIndex, value);
		}
	}

	/**
	 * 在当前的数据集中附加一个新的数据集
	 * 
	 * @param dataframe
	 * @throws Exception
	 */
	public void append(DataframeInDC dataframe) throws Exception {
		if (m_ColNames.size() != dataframe.getColNames().size()) {
			throw new Exception("两个集合的列数目不一致");
		}

		int nColCount = this.getColCounts();
		for (int i = 0; i < nColCount; i++) {
			String sColName = this.getColNameAt(i);
			ArrayList<Object> colValue = m_ColValues.get(sColName);
			ArrayList<Object> colValue1 = dataframe.getColValue(sColName);
			colValue.addAll(colValue1);
		}
	}

	public int getColCounts() {
		return m_ColNames.size();
	}

	public int getRowCounts() {
		int nRowCounts = 0;
		for (int i = 0; i < m_ColNames.size(); i++) {
			ArrayList<Object> o = getColValue(i);
			if (o == null) {
				new Exception("列[" + m_ColNames.get(i) + "]的值为null.")
						.printStackTrace();
			}
			if (o.size() > nRowCounts) {
				nRowCounts = o.size();
			}
		}

		return nRowCounts;
	}

	public DataframeInDC selectRows(int startRowIndex, int endRowIndex)
			throws Exception {
		DataframeInDC dataframe = new DataframeInDC();
		dataframe.setColNames(this.getColNames());
		dataframe.initBlankCols();

		for (int i = 0; i < this.getRowCounts(); i++) {
			if (i >= startRowIndex && i < endRowIndex) {
				String[] rowValue = this.getRow(i);
				dataframe.addRow(rowValue);
			}
		}
		return dataframe;
	}

	public DataframeInDC selectCols(String[] pColNames) throws Exception {
		DataframeInDC dataframe = new DataframeInDC();
		List<String> colNames = new ArrayList<String>();
		Map<String, ArrayList<Object>> colValues = new HashMap<String, ArrayList<Object>>();
		for (int i = 0; i < pColNames.length; i++) {
			String sColName = pColNames[i].trim();
			if (m_ColNameIndexes.containsKey(sColName)) {
				colNames.add(sColName);
				colValues.put(sColName, getColValue(sColName));
			}
		}
		dataframe.setColNames(colNames);
		dataframe.setColValues(colValues);
		return dataframe;
	}

	public DataframeInDC selectCols(int startColIndex, int endColIndex)
			throws Exception {
		DataframeInDC dataframe = new DataframeInDC();
		List<String> colNames = new ArrayList<String>();
		Map<String, ArrayList<Object>> colValues = new HashMap<String, ArrayList<Object>>();
		for (int i = startColIndex; i < this.getColCounts() && i < endColIndex; i++) {
			String sColName = m_ColNames.get(i);
			ArrayList<Object> colValue = m_ColValues.get(sColName);
			colNames.add(sColName);
			colValues.put(sColName, colValue);
		}
		dataframe.setColNames(colNames);
		dataframe.setColValues(colValues);
		return dataframe;
	}

	/**
	 * 获取值的列
	 * 
	 * @param colName
	 * @param colValue
	 * @return
	 */
	public int getRowIndexOfFirst(String colName, String colValue) {
		int nIndexOf = -1;
		int nColIndex = getColIndex(colName);
		for (int i = 0; i < getRowCounts(); i++) {
			String s0 = getColValue(i, nColIndex);
			if (s0.equalsIgnoreCase(colValue)) {
				nIndexOf = i;
				break;
			}
		}
		return nIndexOf;
	}

	/**
	 * 根据指定列的值来获取子数据集
	 * 
	 * @param pColNames
	 * @param pColValues
	 * @return
	 * @throws Exception
	 */
	public DataframeInDC selectChild(String[] pColNames, String[] pColValues)
			throws Exception {
		DataframeInDC dataframe0 = new DataframeInDC();
		dataframe0.setColNames(AnalysisListUtils.cloneList(this.getColNames()));
		dataframe0.initBlankCols();
		int rowCounts = getRowCounts();
		for (int i = 0; i < rowCounts; i++) {
			boolean bIsSelected = true; // 默认选中，如果有一个值不同，那么就不选中
			for (int j = 0; j < pColNames.length; j++) {
				String colName = pColNames[j].trim();
				int colIndex = getColIndex(colName);
				String colValue = getColValue(i, colIndex);
				if (!colValue.trim().equals(pColValues[j])) {
					bIsSelected = false;
					continue;
				}
			}

			if (bIsSelected) {
				// 将当前行选中，并put到dataframeInDC中
				String[] row = getRow(i);
				dataframe0.addRow(row);
			}
		}
		return dataframe0;
	}

	/**
	 * 根据指定列的值来获取子数据集
	 * 
	 * @param pColNames
	 * @param pColValues
	 * @return
	 * @throws Exception
	 */
	public DataframeInDC selectChild(String[] pColNames, String[] pColValues,
			String relate) throws Exception {
		DataframeInDC dataframe0 = new DataframeInDC();
		dataframe0.setColNames(AnalysisListUtils.cloneList(this.getColNames()));
		dataframe0.initBlankCols();
		int rowCounts = getRowCounts();
		for (int i = 0; i < rowCounts; i++) {
			boolean bIsSelected = true; // 默认选中，如果有一个值不同，那么就不选中
			if (relate.equalsIgnoreCase("AND")) {
				bIsSelected = true;
				for (int j = 0; j < pColNames.length; j++) {
					String colName = pColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (!colValue.trim().equals(pColValues[j])) {
						bIsSelected = false;
						continue;
					}
				}
			} else {
				bIsSelected = false;
				for (int j = 0; j < pColNames.length; j++) {
					String colName = pColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (colValue.trim().equals(pColValues[j])) {
						bIsSelected = true;
						continue;
					}
				}
			}

			if (bIsSelected) {
				// 将当前行选中，并put到dataframeInDC中
				String[] row = getRow(i);
				dataframe0.addRow(row);
			}
		}
		return dataframe0;
	}

	/**
	 * 根据指定需要排除列的值来获取子数据集
	 * 
	 * @param pExcludeColNames
	 * @param pExcludeColValues
	 * @return
	 * @throws Exception
	 */
	public DataframeInDC selectChildWithExclude(String[] pExcludeColNames,
			String[] pExcludeColValues, String relate) throws Exception {
		DataframeInDC dataframe0 = new DataframeInDC();
		dataframe0.setColNames(AnalysisListUtils.cloneList(this.getColNames()));
		dataframe0.initBlankCols();
		int rowCounts = getRowCounts();
		StringBuffer oStringBuffer = new StringBuffer();
		for (int i = 0; i < rowCounts; i++) {
			boolean bIsExclude = true; // 默认为排除，如果有一个值不相同，那么不排除
			if (relate.equalsIgnoreCase("AND")) {
				bIsExclude = true;
				for (int j = 0; j < pExcludeColNames.length; j++) {
					String colName = pExcludeColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (!colValue.trim().equals(pExcludeColValues[j])) { //
						bIsExclude = false;
					}
				}
			} else {
				// 或关系，有一个值相同，就排除
				bIsExclude = false;
				for (int j = 0; j < pExcludeColNames.length; j++) {
					String colName = pExcludeColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (colValue.trim().equals(pExcludeColValues[j])) { //
						// System.out.println(colValue+":"+pExcludeColValues[j]);
						bIsExclude = true;
						break;
					}
				}
			}

			if (!bIsExclude) { // 如果当前行不需要排除
				// 将当前行选中，并put到dataframeInDC中
				String[] row = getRow(i);
				dataframe0.addRow(row);
			} else {
				//
				int nColIndex = getColIndex("号码");
				oStringBuffer.append(getColValue(i, nColIndex)).append(",");
			}
		}
		if (oStringBuffer.length() > 0) {
			System.out.println("===移除了:" + oStringBuffer.toString());
		}
		return dataframe0;
	}

	/**
	 * 根据指定需要排除列的值来获取子数据集
	 * 
	 * @param pExcludeColNames
	 * @param pExcludeColValues
	 * @return
	 * @throws Exception
	 */
	public DataframeInDC selectChildWithExclude(String[] pExcludeColNames,
			String[] pExcludeColValues, String relate, String uniqueKey,
			Map<String, String> hmExcludeCounts) throws Exception {
		DataframeInDC dataframe0 = new DataframeInDC();
		dataframe0.setColNames(AnalysisListUtils.cloneList(this.getColNames()));
		dataframe0.initBlankCols();
		int rowCounts = getRowCounts();
		System.out.println("rowCounts:" + rowCounts);
		StringBuffer oStringBuffer = new StringBuffer();
		int nExcludeCounts = 0;
		for (int i = 0; i < rowCounts; i++) {
			boolean bIsExclude = true; // 默认为排除，如果有一个值不相同，那么不排除
			if (relate.equalsIgnoreCase("AND")) {
				bIsExclude = true;
				for (int j = 0; j < pExcludeColNames.length; j++) {
					String colName = pExcludeColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (!colValue.trim().equals(pExcludeColValues[j])) { //
						bIsExclude = false;
					}
				}
			} else {
				// 或关系，有一个值相同，就排除
				bIsExclude = false;
				for (int j = 0; j < pExcludeColNames.length; j++) {
					String colName = pExcludeColNames[j].trim();
					int colIndex = getColIndex(colName);
					String colValue = getColValue(i, colIndex);
					if (colValue.trim().equals(pExcludeColValues[j])) { //
						// System.out.println(colValue+":"+pExcludeColValues[j]);
						bIsExclude = true;
						break;
					}
				}
			}

			if (!bIsExclude) { // 如果当前行不需要排除
				// 将当前行选中，并put到dataframeInDC中
				String[] row = getRow(i);
				dataframe0.addRow(row);
				// System.out.println(i+":dataframe0.getRowCounts():"+dataframe0.getRowCounts());
			} else {
				//
				int nColIndex = getColIndex(uniqueKey);
				oStringBuffer.append(getColValue(i, nColIndex)).append(",");
				nExcludeCounts++;
			}
		}
		String sName = StringUtils.join(pExcludeColNames, ",");
		String sValue = StringUtils.join(pExcludeColValues, ",");
		hmExcludeCounts
				.put(sName + "=" + sValue, String.valueOf(nExcludeCounts) + "/"
						+ oStringBuffer.toString());
		// System.out.println("dataframe0.getRowCounts():"+dataframe0.getRowCounts());
		return dataframe0;
	}

	@Override
	@SuppressWarnings("all")
	public DataframeInDC clone() {
		try {
			DataframeInDC dataframe = new DataframeInDC();
			List<String> colNames = new ArrayList<String>();
			Map<String, ArrayList<Object>> colValues = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < this.getColCounts(); i++) {
				String sColName = m_ColNames.get(i);
				ArrayList<Object> colValue = m_ColValues.get(sColName);
				colNames.add(sColName);
				colValues.put(sColName, (ArrayList<Object>) colValue.clone());
			}
			dataframe.setColNames(colNames);
			dataframe.setColValues(colValues);
			return dataframe;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public String getColNameAt(int colIndex) {
		String sColName = m_IndexColNames.get(colIndex);
		return sColName;
	}

	public ArrayList<Object> getColValue(int colIndex) {
		String sColName = m_ColNames.get(colIndex);
		return getColValue(sColName);
	}

	public String getColValue(int rowIndex, int colIndex) {
		ArrayList<Object> o = getColValue(colIndex);
		String sColValue = "";
		if (o.size() > rowIndex) {
			Object oValue = o.get(rowIndex);
			if (oValue instanceof String) {
				sColValue = (String) oValue;
			} else {
				sColValue = o.get(rowIndex).toString();
			}
		}
		return sColValue;
	}

	public String getColValue(int rowIndex, String colName) {
		ArrayList<Object> o = getColValue(colName);
		String sColValue = "";
		if (o.size() > rowIndex) {
			Object oValue = o.get(rowIndex);
			if (oValue instanceof String) {
				sColValue = (String) oValue;
			} else {
				sColValue = o.get(rowIndex).toString();
			}
		}
		return sColValue;
	}

	public ArrayList<Object> getColValue(String colName) {
		if (colName == null) {
			System.out.println("Why?");
		}
		String sColName = colName.trim();
		return m_ColValues.get(sColName);
	}

	/**
	 * 获取列的序号
	 * 
	 * @param colName
	 * @return 列不存在返回-1
	 */
	public int getColIndex(String colName) {
		int nIndex = -1;
		try {
			nIndex = m_ColNameIndexes.get(colName);
			if (nIndex < 0)
				nIndex = -1;
		} catch (Exception ex) {
		}
		return nIndex;
	}

	public void setColNames(List<String> colNames) throws Exception {
		m_ColNames = colNames;
		for (int i = 0; i < colNames.size(); i++) {
			if (m_ColNameIndexes.containsKey(colNames.get(i))) {
				throw new Exception("数据集不允许出现重名字段[" + colNames.get(i) + "].");
			}

			m_IndexColNames.put(i, m_ColNames.get(i));
			m_ColNameIndexes.put(m_ColNames.get(i), i);
		}
	}

	public void setColValues(Map<String, ArrayList<Object>> colValues) {
		m_ColValues = colValues;
	}

	public List<String> getColNames() {
		return m_ColNames;
	}

	public Map<String, ArrayList<Object>> getColValues() {
		return m_ColValues;
	}

	public DataframeInDC removeColumns(String[] excludeColumns)
			throws Exception {
		if (excludeColumns == null || excludeColumns.length == 0) {
			return this;
		}

		DataframeInDC dataframe0 = new DataframeInDC();
		for (int i = 0; i < getColCounts(); i++) {
			String colName = getColNameAt(i);
			if (StringUtils.isInArray(colName, excludeColumns, false)) {
				continue;
			}
			dataframe0.addCol(colName, getColValue(i));
		}
		return dataframe0;
	}

	/**
	 * 对当前数据集添加另一个数据集，要求两个数据集的列名称保持一致性
	 * 
	 * @param dataframeInDC
	 * @param pUniqueColName
	 * @throws Exception
	 */
	public void merge(DataframeInDC dataframeInDC, String pUniqueColName)
			throws Exception {
		int colCounts = dataframeInDC.getColCounts();
		if (pUniqueColName == null || pUniqueColName.isEmpty()) {
			for (int i = 0; i < colCounts; i++) {
				String colname = dataframeInDC.getColNameAt(i);
				List<Object> colValue0 = getColValue(colname);
				List<Object> colValue1 = dataframeInDC.getColValue(i);
				colValue0.addAll(colValue1);
			}
		} else {
			List<Object> uniqueColValues = getColValue(pUniqueColName);
			int uniqueColIndex = dataframeInDC.getColIndex(pUniqueColName);
			for (int i = 0; i < dataframeInDC.getRowCounts(); i++) {
				String uniqueColValue = dataframeInDC.getColValue(i,
						uniqueColIndex);
				if (uniqueColValues.indexOf(uniqueColValue) != -1) {
					continue;
				}

				for (int j = 0; j < dataframeInDC.getColCounts(); j++) {
					String colName = dataframeInDC.getColNameAt(j);
					String colValue = dataframeInDC.getColValue(i, j);
					List<Object> colValues = getColValue(colName);
					colValues.add(colValue);
				}
			}
		}
	}
}
