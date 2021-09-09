package com.atsushi.kitazawa;

import java.util.List;

public class Dest1 {
    private String strParent;
    private String str;
    private int iParent;
    private int i;
    private Long lParent;
    private Long l;
    private List<String> list;

    public String getStr() {
	return str;
    }

    public void setStr(String str) {
	this.str = str;
    }

    public int getI() {
	return i;
    }

    public void setI(int i) {
	this.i = i;
    }

    public Long getL() {
	return l;
    }

    public void setL(Long l) {
	this.l = l;
    }

    public String getStrParent() {
	return strParent;
    }

    public void setStrParent(String s) {
	this.strParent = s;
    }

    public int getIParent() {
	return iParent;
    }

    public void setIParent(int i) {
	this.iParent = i;
    }

    public long getLParent() {
	return lParent;
    }

    public void setLParent(long l) {
	this.lParent = l;
    }

    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Class:" + this.getClass().getSimpleName());
	sb.append("-");
	sb.append("str:" + str);
	sb.append("-");
	sb.append("i:" + i);
	sb.append("-");
	sb.append("l:" + l);
	sb.append("-");
	return sb.toString();
    }
}
