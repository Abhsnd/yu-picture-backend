package com.yupi.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yupicturebackend.model.dto.picture.PictureQueryRequest;
import com.yupi.yupicturebackend.model.dto.picture.PictureReviewRequest;
import com.yupi.yupicturebackend.model.dto.picture.PictureUploadByBatchRequest;
import com.yupi.yupicturebackend.model.dto.picture.PictureUploadRequest;
import com.yupi.yupicturebackend.model.entity.Picture;
import com.yupi.yupicturebackend.model.entity.User;
import com.yupi.yupicturebackend.model.vo.PictureVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author LENOVO
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-14 00:27:45
*/
public interface PictureService extends IService<Picture> {

    // 上传图片
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    // 获取查询条件
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    // 获取单个图片封装
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    // 分页获取图片封装
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    // 图片校验
    void validPicture(Picture picture);

    // 图片审核
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    // 补充审核参数
    void fillReviewParams(Picture picture, User loginUser);

    // 批量抓取和创建图片
    Integer uploadPictureByBatch(PictureUploadByBatchRequest pictureUploadByBatchRequest, User loginUser);
}
