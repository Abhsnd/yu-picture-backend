package com.yupi.yupicturebackend.common;

import lombok.Data;

import java.io.Serializable;

/*
 * @Author winku
 * @Date 2025/2/2 1:45
 * @Description 删除请求包装类
 * @Since version-1.0
 */

@Data
public class DeleteRequest implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;
}
