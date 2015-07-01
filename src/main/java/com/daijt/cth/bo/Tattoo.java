package com.daijt.cth.bo;

import java.io.Serializable;

public class Tattoo implements Serializable {

	private static final long serialVersionUID = -8171619516327610054L;
	
	private String word;
	private long fontId;
	private byte[] image;
	private int imgWidth;
	private int imgHeight;
	private String imgType;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public long getFontId() {
		return fontId;
	}
	public void setFontId(long fontId) {
		this.fontId = fontId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
}
