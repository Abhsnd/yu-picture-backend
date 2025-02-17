package com.yupi.yupicturebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/*
 * @Author winku
 * @Date 2025/2/17 0:50
 * @Description 用户权限枚举类
 * @Since version-1.0
 */

@Getter
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /*
     * @Author winku
     * @Date 2025/2/8 0:46
     * @Description 根据 value 获取枚举
     * @Param [value 枚举值的value]
     * @Return com.yupi.yupicturebackend.model.enums.UserRoleEnum
     * @Since version-1.0
     */
    public static UserRoleEnum getUserRoleEnum(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
