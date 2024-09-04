package com.trojan.cms.common.param.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteArticleResp {
    
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
}
