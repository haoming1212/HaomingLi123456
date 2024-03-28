package org.ofo.entity;

import java.math.BigDecimal;

// 菜品
public class Item {

    // 主键
    private String id;

    // 店铺 id
    private String shopId;

    // 菜品名称
    private String name;

    // 菜品单价
    private BigDecimal price;

    // 图像
    private String img;

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public Item(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
