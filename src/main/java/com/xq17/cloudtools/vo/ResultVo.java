package com.xq17.cloudtools.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @ClassName: ResultVo
 * @Description: Vo Value Object
 * @author xq17
 * @date 2020年9月7日
 *
 */
public class ResultVo {

	private Integer code;
	private String msg;

	@JsonProperty("data")
	private Object obj;

	public ResultVo(Integer code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	public ResultVo(ExceptionMsg msg) {
		this.code = msg.getCode();
		this.msg = msg.getMsg();
		this.obj = "";
	}

	public ResultVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResultVo [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultVo other = (ResultVo) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}

}
