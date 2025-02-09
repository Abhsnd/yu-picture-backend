package com.yupi.yupicturebackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yupicturebackend.annotation.AuthCheck;
import com.yupi.yupicturebackend.common.BaseResponse;
import com.yupi.yupicturebackend.common.DeleteRequest;
import com.yupi.yupicturebackend.common.ResultUtils;
import com.yupi.yupicturebackend.constant.UserConstant;
import com.yupi.yupicturebackend.exception.BusinessException;
import com.yupi.yupicturebackend.exception.ErrorCode;
import com.yupi.yupicturebackend.exception.ThrowUtils;
import com.yupi.yupicturebackend.model.dto.user.*;
import com.yupi.yupicturebackend.model.entity.User;
import com.yupi.yupicturebackend.model.vo.LoginUserVO;
import com.yupi.yupicturebackend.model.vo.UserVO;
import com.yupi.yupicturebackend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /*
     * @Author winku
     * @Date 2025/2/8 14:59
     * @Description 用户注册
     * @Param [userRegisterRequest]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<java.lang.Long>
     * @Since version-1.0
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /*
     * @Author winku
     * @Date 2025/2/8 16:17
     * @Description 用户登录
     * @Param [userLoginRequest, request]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<com.yupi.yupicturebackend.model.vo.LoginUserVO>
     * @Since version-1.0
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /*
     * @Author winku
     * @Date 2025/2/8 16:33
     * @Description 获取当前登录用户
     * @Param [request]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<com.yupi.yupicturebackend.model.vo.LoginUserVO>
     * @Since version-1.0
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        // loginUser将数据库查询的所有数据都存储了（包括密码），需要脱敏后返回前端
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    /*
     * @Author winku
     * @Date 2025/2/8 16:43
     * @Description 用户注销
     * @Param [request]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<java.lang.Boolean>
     * @Since version-1.0
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /*
     * @Author winku
     * @Date 2025/2/9 1:31
     * @Description 创建用户 （仅管理员）
     * @Param [userAddRequest]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<java.lang.Long>
     * @Since version-1.0
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        ThrowUtils.throwIf(userAddRequest == null, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        // 默认密码
        final String DEFAULT_PASSWORD = "12345678";
        String encryptPassword = userService.getEncryptPassword(DEFAULT_PASSWORD);
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    /*
     * @Author winku
     * @Date 2025/2/10 1:15
     * @Description 根据 id 获取用户 （仅管理员）
     * @Param [id]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<com.yupi.yupicturebackend.model.entity.User>
     * @Since version-1.0
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(Long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }

    /*
     * @Author winku
     * @Date 2025/2/10 1:18
     * @Description 根据 id 获取包装类
     * @Param [id]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<com.yupi.yupicturebackend.model.vo.UserVO>
     * @Since version-1.0
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(Long id) {
        BaseResponse<User> response = getUserById(id);
        User user = response.getData();
        return ResultUtils.success(userService.getUserVO(user));
    }

    /*
     * @Author winku
     * @Date 2025/2/10 1:21
     * @Description 删除用户 （仅管理员）
     * @Param [deleteRequest]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<java.lang.Boolean>
     * @Since version-1.0
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /*
     * @Author winku
     * @Date 2025/2/10 1:24
     * @Description 更新用户 （仅管理员）
     * @Param [userUpdateRequest]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<java.lang.Boolean>
     * @Since version-1.0
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(result);
    }

    /*
     * @Author winku
     * @Date 2025/2/10 1:32
     * @Description 分页获取用户封装列表 （仅管理员）
     * @Param [userQueryRequest]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.yupi.yupicturebackend.model.vo.UserVO>>
     * @Since version-1.0
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
        ThrowUtils.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = userQueryRequest.getCurrent();
        long pageSize = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, pageSize), userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }
}
