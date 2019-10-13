package com.revanwang.template;

import com.revanwang.wms.domain.BaseDomain;
import lombok.Getter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * 表示数据模型对象，封装了模板中的占位符的数据
 */
@Getter
public class ClassInfo {
    private String  basePKG;        //基础包名
    private String  className;      //domain类的简单类名
    private String  lowerClassName; //domain类的简单类名 （小写）
    private List<String> properts = new ArrayList<>();  //对象属性

    public ClassInfo(Class beanClass) throws Exception {
        this.className = beanClass.getSimpleName();
        String pkgName = beanClass.getPackage().getName();
        this.basePKG = pkgName.substring(0, pkgName.lastIndexOf("."));

        StringBuilder sb = new StringBuilder(20);
        sb.append(this.className.substring(0, 1).toLowerCase());
        sb.append(this.className.substring(1));
        this.lowerClassName = sb.toString();

        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, BaseDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            this.properts.add(pd.getName());
        }

        System.out.println("ClassInfo.ClassInfo:==" + pds.length);
    }
}
