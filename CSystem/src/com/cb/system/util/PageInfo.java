package com.cb.system.util;

/**
 * 翻页类
 * @author chen
 *
 */
public class PageInfo {

	/**
	 * 当前页
	 */
	private int currentPageNo = 1;
	
	/**
	 * 每页显示数目
	 */
	private int sizePerPage = 10;
	
	/**
	 * 总页数
	 */
	private int totalPages = 1;

	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if (currentPageNo > 0)
			this.currentPageNo = currentPageNo;
		else
			this.currentPageNo = 1;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages(int totalPages) {
		if (totalPages > 0)
			this.totalPages = totalPages;
		else
			this.totalPages = 1;
	}

	public int getSizePerPage() {
		return this.sizePerPage;
	}

	public void setSizePerPage(int sizePerPage) {
		if (sizePerPage >= 0)
			this.sizePerPage = sizePerPage;
	}


	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("PagedInfo [currentPageNo=");
		sb.append(this.currentPageNo);
		sb.append(", sizePerPage=");
		sb.append(this.sizePerPage);
		sb.append(", totalPages=");
		sb.append(this.totalPages);
		sb.append("]");
		return sb.toString();
	}
}
