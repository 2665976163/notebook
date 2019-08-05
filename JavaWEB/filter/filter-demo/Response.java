package com.znsd.filter;

public class Response {

	private String info = "response";

	public final String getInfo() {
		return info;
	}

	public final void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Response [info=" + info + "]";
	}

}
