package com.znsd.filter.impl;

import java.util.ArrayList;
import java.util.List;

import com.znsd.filter.Filter;
import com.znsd.filter.Request;
import com.znsd.filter.Response;

public class FilterChain implements Filter {

	private List<Filter> list;
	private Integer index;
	
	public FilterChain() {
		this.list = new ArrayList<>();
		this.index = 0;
	}
	
	public FilterChain add (Filter filter) {
		list.add(filter);
		return this;
	}

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		if (index++ == list.size()) return;
		
		list.get(index - 1).doFilter(request, response, chain);
	}
	
}
