package com.yupi.yupicturebackend.model.dto.file;

import lombok.Data;

/*
 * @Author winku
 * @Date 2025/2/14 14:46
 * @Description 上传图片的结果
 * @Since version-1.0
 */

@Data
public class UploadPictureResult {
    /**
     * 图片 url
     */
    private String url;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片体积
     */
    private Long picSize;

    /**
     * 图片宽度
     */
    private Integer picWidth;

    /**
     * 图片高度
     */
    private Integer picHeight;

    /**
     * 图片宽高比例
     */
    private Double picScale;

    /**
     * 图片格式
     */
    private String picFormat;
}
