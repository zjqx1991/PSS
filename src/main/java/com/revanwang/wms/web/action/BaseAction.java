package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {

    protected static final String LIST = "list";


    /**
     * 为Context赋值
     * @param name
     * @param value
     */
    protected void ActionContextPut(String name, Object value) {
        ActionContext.getContext().put(name, value);
    }

    /**
     * 拦截所有的方法
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        System.out.println("拦截所有的方法 prepare");
    }
}
