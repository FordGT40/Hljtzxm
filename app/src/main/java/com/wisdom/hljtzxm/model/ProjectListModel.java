package com.wisdom.hljtzxm.model;

import java.io.Serializable;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm.model
 * @class describe：
 * @time 2019/1/31 13:59
 * @change
 */
public class ProjectListModel implements Serializable {

    /**
     * catalog_code : 18F5210D-8E72-403E-BE61-5172DDF1F4A9
     * catalog_name : 政府投资非跨大江大河、跨市（地）的市（地）本级独立公路桥梁、隧道项目
     */

    private String catalog_code;
    private String catalog_name;

    public String getCatalog_code() {
        return catalog_code;
    }

    public void setCatalog_code(String catalog_code) {
        this.catalog_code = catalog_code;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }
}
