package com.trojan.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trojan.cms.entity.Article;
import com.trojan.cms.mapper.ArticleMapper;
import com.trojan.cms.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author author
 * @since 2021-04-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
