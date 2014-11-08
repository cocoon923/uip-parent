package com.ailife.uip.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 9/28/2014.
 */
public class Inter extends JsonBean<Inter> implements Serializable {

	private String seq;
	private String interName;
	private String interDesc;
	private String interCode;
	private String implClass;
	private String invokeMethod;
	private Integer sort;
	private String remark;
	private List<Param> params = new ArrayList<Param>();
	private List<ItemRelat> itemRelats = new ArrayList<ItemRelat>();

	public Inter() {
	}

	public Inter(String seq, String interName, String interDesc, String interCode, String implClass, String invokeMethod, Integer sort, String remark) {
		this.seq = seq;
		this.interName = interName;
		this.interDesc = interDesc;
		this.interCode = interCode;
		this.implClass = implClass;
		this.invokeMethod = invokeMethod;
		this.sort = sort;
		this.remark = remark;
	}

	@Override
	protected Inter getT() {
		return this;
	}

	public static Inter toBean(String json) {
		return JsonBean.toBean(json, Inter.class);
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public String getInterDesc() {
		return interDesc;
	}

	public void setInterDesc(String interDesc) {
		this.interDesc = interDesc;
	}

	public String getInterCode() {
		return interCode;
	}

	public void setInterCode(String interCode) {
		this.interCode = interCode;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public String getInvokeMethod() {
		return invokeMethod;
	}

	public void setInvokeMethod(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Param> getParams() {
		return params;
	}

	public void addParams(List<Param> params) {
		this.params.addAll(params);
	}

	public List<ItemRelat> getItemRelats() {
		return itemRelats;
	}

	public void addItemRelats(List<ItemRelat> itemRelats) {
		this.itemRelats.addAll(itemRelats);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Inter)) return false;

		Inter inter = (Inter) o;

		if (!seq.equals(inter.seq)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return seq.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Inter{");
		sb.append("seq=").append(seq);
		sb.append(", interName='").append(interName).append('\'');
		sb.append(", interDesc='").append(interDesc).append('\'');
		sb.append(", interCode='").append(interCode).append('\'');
		sb.append(", implClass='").append(implClass).append('\'');
		sb.append(", invokeMethod='").append(invokeMethod).append('\'');
		sb.append(", sort=").append(sort);
		sb.append(", remark='").append(remark).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
