package seven.team.entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private int goodsId;   //商品的id号
    private String goodsName;
    private double price; //商品价格
    private String userId;
    private String nickName;
    private int quantity;
    private String picAddress1;
    private String picAddress2;
    private String picAddress3;
    private String content; //商品简介
    private String describe; //商品描述
    private String type;  //商品的种类

    public void setGoodsId(int goodsId){this.goodsId = goodsId; }

    public int getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() { return goodsName; }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {  this.quantity = quantity; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicAddress1() { return picAddress1; }

    public void setPicAddress1(String picAddress1) { this.picAddress1 = picAddress1; }

    public String getPicAddress2() { return picAddress2; }

    public void setPicAddress2(String picAddress2) { this.picAddress2 = picAddress2; }

    public String getPicAddress3() { return picAddress3; }

    public void setPicAddress3(String picAddress3) { this.picAddress3 = picAddress3; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }
}


