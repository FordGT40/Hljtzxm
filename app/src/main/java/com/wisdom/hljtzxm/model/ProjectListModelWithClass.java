package com.wisdom.hljtzxm.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm.model
 * @class describe：
 * @time 2019/1/31 14:37
 * @change
 */
public class ProjectListModelWithClass implements Serializable {

    /**
     * items : [{"catalog_name":"跨市（地）水资源配置调整的其他水事工程项目核准","catalog_code":"90C57938-25AF-4063-8FCF-DFC6C54726D8"},{"catalog_name":"农业项目核准","catalog_code":"B28D3AF9-0070-4E1D-A820-B7D65ADC23AB"},{"catalog_name":"水利工程项目核准","catalog_code":"E404365E-95AB-48E2-96C2-FD736252B75D"}]
     * class_name : 农业水利
     */

    private String class_name;
    private List<ItemsBean> items;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable{
        /**
         * catalog_name : 跨市（地）水资源配置调整的其他水事工程项目核准
         * catalog_code : 90C57938-25AF-4063-8FCF-DFC6C54726D8
         */

        private String catalog_name;
        private String catalog_code;

        public String getCatalog_name() {
            return catalog_name;
        }

        public void setCatalog_name(String catalog_name) {
            this.catalog_name = catalog_name;
        }

        public String getCatalog_code() {
            return catalog_code;
        }

        public void setCatalog_code(String catalog_code) {
            this.catalog_code = catalog_code;
        }
    }
}
