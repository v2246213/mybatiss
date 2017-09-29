package com.ctmp01.web.util.response;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 4487215446444628927L;
	
	private int pageSize = 20;
	private int totalCount;
	private int totalPage;
	private int currentPage = 1;
	private List<T> content;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return pageSize * (currentPage - 1);
	}

	public int getEndIndex() {
		return pageSize * currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage > 0 ? totalPage : totalCount == 0 ? 1 : (int) Math.ceil(new Double(totalCount) / pageSize);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	
}
