package com.trojan.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 文章
 * </p>
 * @author author
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article extends Model<Article> {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private Long siteId;
    
    private Long cateId;
    
    private Integer status;
    
    private Integer recommend;
    
    private String title;
    
    private String time;
    
    private String author;
    
    private Integer views;
    
    private String description;
    
    private String detail;
    
    private String header;
    
    private String files;
    
    private Timestamp createTime;
    
    private Timestamp updateTime;
    
    
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    
}
