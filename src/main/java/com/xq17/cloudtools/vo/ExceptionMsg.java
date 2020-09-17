package com.xq17.cloudtools.vo;

public enum ExceptionMsg {
	SUCCESS(200, "操作成功"), FAILED(301, "操作失败"), ErrorService(500, "服务器错误");

	private Integer code;
	private String msg;

	private ExceptionMsg(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
