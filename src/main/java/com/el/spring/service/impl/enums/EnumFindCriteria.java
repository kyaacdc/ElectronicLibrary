package com.el.spring.service.impl.enums;

public enum EnumFindCriteria {
    TITLE(1), DESCRIPTION(2);

    private int value;

    EnumFindCriteria(int value){this.value = value;}

    public Integer getValue(){return value;}
}
