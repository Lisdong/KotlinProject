package com.example.kt.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created By LRD
 * on 2018/8/30  notes：
 */
public class ServiceEntity implements MultiItemEntity {
	public static final int TYPE_SERVICES_MESSAGE = 1;
	public static final int TYPE_CLIENT_MESSAGE = 2;
	public static final int TYPE_CLIENT_ERROR = 3;
	private int itemType;


	public ServiceEntity(int itemType) {
		this.itemType = itemType;
	}

	@Override
	public int getItemType() {
		return itemType;
	}
	public ServiceEntity(int itemType, TurLingGson data) {
		this.itemType = itemType;
		this.data = data;
	}

	public ServiceEntity(int itemType, String inputText) {
		this.itemType = itemType;
		this.inputText = inputText;
	}

	public ServiceEntity(int itemType, AnBean data) {
		this.itemType = itemType;
		this.dataForAn = data;
	}

	private int code;
	private String text;
	private String url;
	private TurLingGson data;

	private AnBean dataForAn;
	private String inputText;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TurLingGson getData() {
		return data;
	}

	public void setData(TurLingGson data) {
		this.data = data;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public AnBean getDataForAn() {
		return dataForAn;
	}

	public void setDataForAn(AnBean dataForAn) {
		this.dataForAn = dataForAn;
	}

	/**
	 * @param data 服务器数据-图灵
	 */
	public static ServiceEntity service(TurLingGson data) {
		return new ServiceEntity(TYPE_SERVICES_MESSAGE, data);
	}
	/**
	 * @param data 服务器数据-小安
	 */
	public static ServiceEntity service(AnBean data) {
		return new ServiceEntity(TYPE_SERVICES_MESSAGE, data);
	}

	/**
	 * @param data 本地数据
	 */
	public static ServiceEntity client(String data) {
		return new ServiceEntity(TYPE_CLIENT_MESSAGE, data);
	}

	/**
	 * @param error 本地数据
	 */
	public static ServiceEntity error(String error) {
		return new ServiceEntity(TYPE_CLIENT_ERROR, error);
	}
}
