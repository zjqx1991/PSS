package com.revanwang.template;

import com.revanwang.utils.XmlUtil;
import com.revanwang.wms.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

/**
 * 代码生成器
 */
public class CodeGenerator {

    private static Configuration config;

    static {
        try {
            //1 创建配置对象
            config = new Configuration(Configuration.getVersion());
            config.setDefaultEncoding("UTF-8");
            //2 设置模板文件加载的目录（根目录）
            config.setDirectoryForTemplateLoading(new File("templates"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

//        generator(StockInBill.class);

    }


    private static void  generator(Class clazz) throws Exception {
        //1、生成Pension的DAO
        generatorDAO(clazz);

        //2、生成Persion的DAO的impl
        generatorDAOImpl(clazz);


        //3、生成Persion的查询对象
        generatorQueryObject(clazz);

        //4、生成Persion的Service
        generatorService(clazz);

        //5、生成Persion的Service的impl
        generatorServiceImpl(clazz);

        //6、生成Persion的Action
        generatorAction(clazz);

        //7、生成Persion的list.jsp
        generatorListJsp(clazz);

        //8、生成Persion的input.jsp
        generatorInputJsp(clazz);

        //9、生成Persion的hbm
        generatorHbm(clazz);

        //10、生成Persion在applicationContext-dao.xml添加
        generatorXML("Dao.xml", new ClassInfo(clazz), "src/main/resources/applicationContext-dao.xml");

        //11、生成Persion在applicationContext-service.xml添加
        generatorXML("Service.xml", new ClassInfo(clazz), "src/main/resources/applicationContext-service.xml");

        //12、生成Persion在applicationContext-action.xml添加
        generatorXML("Action.xml", new ClassInfo(clazz), "src/main/resources/applicationContext-action.xml");

    }


    /**
     * 生成 Hbm
     *
     * @param beanClass Class
     */
    private static void generatorContext(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/resources/{0}/domain/{1}.hbm.xml";
        //为{0}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJSP("hbm.xml", info, filePath);
    }


    /**
     * 生成 Hbm
     *
     * @param beanClass Class
     */
    private static void generatorHbm(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/resources/{0}/domain/{1}.hbm.xml";
        //为{0}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJSP("hbm.xml", info, filePath);
    }

    /**
     * 生成input.jsp
     *
     * @param beanClass Class
     */
    private static void generatorInputJsp(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/webapp/WEB-INF/views/{0}/input.jsp";
        //为{0}设值
        String filePath = MessageFormat.format(path, info.getLowerClassName());
        //生成代码
        generatorJSP("input.jsp", info, filePath);
    }

    /**
     * 生成list.jsp
     *
     * @param beanClass Class
     */
    private static void generatorListJsp(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/webapp/WEB-INF/views/{0}/list.jsp";
        //为{0}设值
        String filePath = MessageFormat.format(path, info.getLowerClassName());
        //生成代码
        generatorJSP("list.jsp", info, filePath);
    }


    /**
     * 生成Action
     *
     * @param beanClass Class
     */
    private static void generatorAction(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/web/action/{1}Action.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("Action.java", info, filePath);
    }

    /**
     * 生成Service的实现
     *
     * @param beanClass Class
     */
    private static void generatorServiceImpl(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/service/impl/{1}ServiceImpl.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("ServiceImpl.java", info, filePath);
    }


    /**
     * 生成Service
     *
     * @param beanClass Class
     */
    private static void generatorService(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/service/I{1}Service.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("IService.java", info, filePath);
    }


    /**
     * 生成DAO
     *
     * @param beanClass Class
     */
    private static void generatorDAO(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/dao/I{1}DAO.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("IDAO.java", info, filePath);
    }


    /**
     * 生成 DAOImpl
     *
     * @param beanClass Class类名
     * @throws Exception
     */
    private static void generatorDAOImpl(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/dao/impl/{1}DAOImpl.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("DAOImpl.java", info, filePath);
    }

    /**
     * 生成queryObject
     *
     * @param beanClass Class
     */
    private static void generatorQueryObject(Class beanClass) throws Exception {
        ClassInfo info = new ClassInfo(beanClass);
        /**
         * 输出目录地址
         * {0}:基础包名，需要将. 该成 /
         * {1}:domain类的简单类名
         */
        String path = "src/main/java/{0}/query/{1}QueryObject.java";
        //为{0}/{1}设值
        String filePath = MessageFormat.format(path, info.getBasePKG().replace(".", "/"), info.getClassName());
        //生成代码
        generatorJava("QueryObject.java", info, filePath);
    }

    /**
     * 生成Java代码
     *
     * @param templateType 模板类型[DAO、Service、Action]
     * @param info         封装bean的信息
     * @param filePath     输出路径
     * @throws IOException
     */
    private static void generatorJava(String templateType, ClassInfo info, String filePath) throws Exception {
        //选择使用构造器来传递获取参数封装数据对象
        Template template = config.getTemplate(templateType);
        template.process(info, new FileWriter(filePath));
    }


    /**
     * 生成JSP
     *
     * @param templateType 模板类型[DAO、Service、Action]
     * @param info         封装bean的信息
     * @param filePath     输出路径
     * @throws IOException
     */
    private static void generatorJSP(String templateType, ClassInfo info, String filePath) throws Exception {
        //选择使用构造器来传递获取参数封装数据对象
        Template template = config.getTemplate(templateType);

        File file = new File(filePath);
        //判断当前文件的父目录是否存在，如果不存在，则生成一个
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        template.process(info, new FileWriter(file));
    }

    /**
     * 生成XML
     *
     * @param templateType 模板类型[DAO、Service、Action]
     * @param info         封装bean的信息
     * @param filePath     输出路径
     * @throws IOException
     */
    private static void generatorXML(String templateType, ClassInfo info, String filePath) throws Exception {
        //选择使用构造器来传递获取参数封装数据对象
        Template template = config.getTemplate(templateType);

        StringWriter out = new StringWriter();
        template.process(info, out);

        String appendXML = out.toString();
        XmlUtil.appendXML(new File(filePath), appendXML);
    }

}
