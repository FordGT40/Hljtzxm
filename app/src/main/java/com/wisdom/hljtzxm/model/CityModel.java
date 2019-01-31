package com.wisdom.hljtzxm.model;

import java.io.Serializable;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm.model
 * @class describe：
 * @time 2019/1/31 11:23
 * @change
 */
public class CityModel implements Serializable {

    /**
     * area_name : 哈尔滨市
     * parent_code : 230000
     * area_code : 230100
     * area_level : 2
     * area_full_name : 黑龙江省哈尔滨市
     */

    private String area_name;
    private String parent_code;
    private String area_code;
    private int area_level;
    private String area_full_name;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public int getArea_level() {
        return area_level;
    }

    public void setArea_level(int area_level) {
        this.area_level = area_level;
    }

    public String getArea_full_name() {
        return area_full_name;
    }

    public void setArea_full_name(String area_full_name) {
        this.area_full_name = area_full_name;
    }
}
