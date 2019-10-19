package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.utils.FileUploadUtil;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Product;
import com.revanwang.wms.query.ProductQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IBrandService;
import com.revanwang.wms.service.IProductService;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

public class ProductAction extends BaseAction {


    @Setter
    private IProductService productService;           
    @Setter
    private IBrandService brandService;
    @Getter
    private Product product = new Product();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @Setter
    private List<Long> ids;
    @Setter
    private File pic;           //上传文件
    @Setter
    private String picFileName; //上传文件名字


    @Override
    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        System.out.println("商品列表:==" + this.qo.getCurrentPage() + "___" + this.qo.getPageSize());
        try {
            //品牌
            ActionContextPut("brands", this.brandService.getList());
            QueryResultObject resultObject = this.productService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("商品编辑")
    public String input() throws Exception {
        //品牌
        ActionContextPut("brands", this.brandService.getList());
        Long productId = this.product.getId();
        if (productId != null) {
            this.product = this.productService.get(productId);
            System.out.println("ProductAction.input: " + this.product);
        }
        return INPUT;
    }


    @RequiredPermission("商品保存或更新")
    public String saveOrUpdate() throws Exception {
        System.out.println("商品保存或更新页数：" + this.qo.getPageSize());
        try {

            //更新操作
            if (this.product.getId() != null && this.pic != null && this.product.getSmallImagePath() != null) {
                FileUploadUtil.deleteFile(this.product.getImagePath());
            }

            if (this.pic != null) {
                String imgPath = FileUploadUtil.uploadFile(this.pic, this.picFileName);
                this.product.setImagePath(imgPath);
            }

            Long id = this.product.getId();
            if (id == null) {
                //新增
                this.productService.save(this.product);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.productService.update(this.product);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("商品删除")
    public String delete() {
        try {
            Long productId = this.product.getId();
            if (productId != null) {
                //删除图片
                Product pd = this.productService.get(productId);
                FileUploadUtil.deleteFile(pd.getImagePath());

                this.productService.delete(productId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("商品批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.productService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }


    /**
     * 拦截 saveOrUpdate方法
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.product.getId();
        if (empId != null) {
            //获取数据库数据
            this.product = this.productService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
