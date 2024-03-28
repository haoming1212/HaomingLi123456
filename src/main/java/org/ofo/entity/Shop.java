package org.ofo.entity;

// 店铺
public class Shop {

    // 数据库主键
    private String id;

    // 用户 id
    private String userId;

    // 店铺名称
    private String name;

    // 店铺管理者
    private String manager;

    // 联系方式
    private String phone;

    // 地址
    private String address;

    public Shop(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
