package com.znsd.filter;

import com.znsd.filter.impl.FilterChain;

public interface Filter {

	public void doFilter(Request request, Response response, FilterChain chain);
	
}
