package nak.nsf.pager;

import java.util.List;

import nak.nsf.pager.Pager;


public class PageResult<T> {

	private List<T> list;
	private Pager pager;
		
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
