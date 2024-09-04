package com.trojan.cms.common.param.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteArticleReq {
    
    @ApiModelProperty(value = "文章ID")
    private Long id;
    
    @ApiModelProperty(value = "文章分类ID")
    private Long cateId;
    
    @ApiModelProperty(value = "推荐状态，1为推荐，0为不推荐")
    private Integer recommend;
    
    @ApiModelProperty(value = "文章标题")
    private String title;
    
    @ApiModelProperty(value = "发布时间")
    private String time;
    
    @ApiModelProperty(value = "作者")
    private String author;
    
    @ApiModelProperty(value = "阅读量")
    private Integer views;
    
    @ApiModelProperty(value = "文章描述")
    private String description;
    
    @ApiModelProperty(value = "文章详情")
    private String detail;
    
    @ApiModelProperty(value = "文章头图")
    private String header;
    
    @ApiModelProperty(value = "附件文件")
    private String files;
    
    @ApiModelProperty(value = "文章状态，1为正常，0为草稿，-1为删除")
    private Integer status;
}
