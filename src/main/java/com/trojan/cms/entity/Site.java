package com.trojan.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 站点设置
 * </p>
 *
 * @author author
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("site")
public class Site extends Model<Site> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String nickName;

    private String address;

    private String lat;

    private String lng;

    private String logo;

    private String phone;

    private String email;

    private String beian;

    private String description;

    private String detail;

    /**
     * 域名
     */
    private String domain;

    private Integer status;

    /**
     * []
     */
    private String recommendArticle;

    private String banner;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
