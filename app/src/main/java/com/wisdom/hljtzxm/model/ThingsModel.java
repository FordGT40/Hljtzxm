package com.wisdom.hljtzxm.model;

import java.io.Serializable;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm.model
 * @class describe：
 * @time 2019/1/31 15:45
 * @change
 */
public class ThingsModel implements Serializable {

    /**
     * ITEM_NAME : 固定资产投资项目节能评估审查（按国家和省有关规定办理）（按国家和省有关规定办理）
     * tzxm_depdisname : 发改委部门
     */

    private String ITEM_NAME;
    private String tzxm_depdisname;

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getTzxm_depdisname() {
        return tzxm_depdisname;
    }

    public void setTzxm_depdisname(String tzxm_depdisname) {
        this.tzxm_depdisname = tzxm_depdisname;
    }
}
