package com.atsushi.kitazawa;

import java.util.List;

public class Dest {
    private String str;
    private int i;
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
