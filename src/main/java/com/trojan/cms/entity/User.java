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
 * 用户表
 * </p>
 *
 * @author author
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private String avatarUrl;

    /**
     * 0平台1管理员2编辑3用户
     */
    private Integer role;

    private Long siteId;

    private Integer status;

    private String email;

    private String phone;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
