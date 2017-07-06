package com.sinjon.ticket.pojo;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 *
 * 商品实体类
 */
public class Good implements Serializable {

    private String imageUrl;//图片url
    private String title;//标题
    private String priceCurrent;//现价
    private String priceOld;//原价
    private String discount;//折扣
    private String ticket;//领券地址

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(String priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(String priceOld) {
        this.priceOld = priceOld;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Good{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", priceCurrent='" + priceCurrent + '\'' +
                ", priceOld='" + priceOld + '\'' +
                ", discount='" + discount + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
