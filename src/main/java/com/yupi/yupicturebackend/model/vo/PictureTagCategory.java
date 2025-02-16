package com.yupi.yupicturebackend.model.vo;

import lombok.Data;

import java.util.List;

/*
 * @Author winku
 * @Date 2025/2/14 21:44
 * @Description 图片标签分类列表视图
 * @Since version-1.0
 */

@Data
public class PictureTagCategory {

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 分类列表
     */
    private List<String> categoryList;
}
