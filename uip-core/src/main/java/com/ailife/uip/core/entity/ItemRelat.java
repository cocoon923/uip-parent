package com.ailife.uip.core.entity;

/**
 * Created by chenmm6 on 2014/10/30.
 */
public class ItemRelat {

	private String seq;
	private String itemSeq;
	private String relatItemSeq;
	private String itemType;
	private String relatItemType;

	public ItemRelat() {
	}

	public ItemRelat(String seq, String itemSeq, String relatItemSeq, Class itemClazz, Class relatItemClazz) {
		this.seq = seq;
		this.itemSeq = itemSeq;
		this.relatItemSeq = relatItemSeq;
		this.itemType = itemClazz.getName();
		this.relatItemType = relatItemClazz.getName();
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getItemSeq() {
		return itemSeq;
	}

	public void setItemSeq(String itemSeq) {
		this.itemSeq = itemSeq;
	}

	public String getRelatItemSeq() {
		return relatItemSeq;
	}

	public void setRelatItemSeq(String relatItemSeq) {
		this.relatItemSeq = relatItemSeq;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getRelatItemType() {
		return relatItemType;
	}

	public void setRelatItemType(String relatItemType) {
		this.relatItemType = relatItemType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ItemRelat{");
		sb.append("seq=").append(seq);
		sb.append(", itemSeq=").append(itemSeq);
		sb.append(", relatItemSeq=").append(relatItemSeq);
		sb.append(", itemType='").append(itemType).append('\'');
		sb.append(", relatItemType='").append(relatItemType).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
