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
 * 分类
 * </p>
 *
 * @author author
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cate")
public class Cate extends Model<Cate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long siteId;

    private String name;

    private Long pid;

    private Integer recommend;

    private Integer sort;

    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
