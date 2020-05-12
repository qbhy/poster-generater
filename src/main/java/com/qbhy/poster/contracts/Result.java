package com.qbhy.poster.contracts;


public interface Result extends Jsonable {

    static final String SUCCESSFUL = "success";

    public boolean isSuccessful();
}
