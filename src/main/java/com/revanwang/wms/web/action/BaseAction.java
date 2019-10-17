package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

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


    /**
     * 给客户端返回信息
     * @param msg
     * @throws Exception
     */
    public void ActionContextResponseMsg(String msg) throws Exception {
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(msg);
    }

    public void ActionContextJson(Object value) throws Exception {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(value);
    }

}
