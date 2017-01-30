package com.el.spring.service.impl.enums;

public enum EnumFindCriteria {
    TITLE(1), DESCR(2);

    private int value;

    EnumFindCriteria(int value){this.value = value;}

    public int getValue(){return value;}
}
