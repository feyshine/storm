package com.cn.hnust.pojo;

public class Image {
    private Long imgId;

    private String imgName;

    private String imgSize;

    private String imgType;

    private String imgPath;

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize == null ? null : imgSize.trim();
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}