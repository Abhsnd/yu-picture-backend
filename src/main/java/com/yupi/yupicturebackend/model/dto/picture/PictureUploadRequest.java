package com.yupi.yupicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片上传请求
 */

@Data
public class PictureUploadRequest implements Serializable {

    /**
     * 图片id （由于修改）
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
