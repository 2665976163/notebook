package com.znsd.filter;

import com.znsd.filter.impl.FilterChain;
import com.znsd.filter.impl.HTMLFiler;
import com.znsd.filter.impl.SensitiveFiler;

public class Index {

	public static void main(String[] args) {
		
		String info = "hello <scripe> Wrold 敏感内容";
		
		Request request = new Request();
		request.setInfo(info);
		Response response = new Response();
		
		FilterChain chain = new FilterChain();
		chain.add(new HTMLFiler());
		chain.add(new SensitiveFiler());
		
		chain.doFilter(request, response, chain);
		
		System.out.println(request);
		System.out.println(response);
		
	}
	
}
