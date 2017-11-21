/** 广州哇宝信息技术有限公司 */
package com.wabao.json;

/**
 * 通用Ajax通讯结构体 (本结构体只适用于当浏览器的Ajax请求已经到达服务器端)
 * 
 * @since 2017年11月17日 上午11:43:08
 * @author Administrator
 */
public class AjaxJson {
	private int status;
	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
