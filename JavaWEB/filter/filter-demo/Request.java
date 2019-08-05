package com.znsd.filter;

public class Request {

	private String info = "request";

	public final String getInfo() {
		return info;
	}

	public final void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Request [info=" + info + "]";
	}

}
