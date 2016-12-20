package com.llkj.creditchecking.jpush;



import java.io.Serializable;

public class PushBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	private String ad_id;
	private String content_type;
	private String message;
	private String show_type;
	private String title;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShow_type() {
		return show_type;
	}

	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
