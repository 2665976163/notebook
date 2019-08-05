package com.znsd.filter.impl;

import com.znsd.filter.Filter;
import com.znsd.filter.Request;
import com.znsd.filter.Response;

public class HTMLFiler implements Filter {

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		
		String requestInfo = request.getInfo();
		requestInfo = requestInfo.replaceAll("<", "(").replaceAll(">", ">") + ", HTMLFilter";
		request.setInfo(requestInfo);
		
		chain.doFilter(request, response, chain);
		
		String responseInfo = response.getInfo();
		responseInfo += ", HTMLFilter";
		response.setInfo(responseInfo);
		
	}

}
