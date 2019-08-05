package com.znsd.filter.impl;

import com.znsd.filter.Filter;
import com.znsd.filter.Request;
import com.znsd.filter.Response;

public class SensitiveFiler implements Filter {

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		
		String requestInfo = request.getInfo();
		requestInfo = requestInfo.replaceAll("敏感", "") + ", SensitiveFiler";
		request.setInfo(requestInfo);
		
		chain.doFilter(request, response, chain);
		
		String responseInfo = response.getInfo();
		responseInfo += ", SensitiveFiler";
		response.setInfo(responseInfo);
		
	}

}
