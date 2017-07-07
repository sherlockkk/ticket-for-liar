package com.sinjon.ticket.clawer.config;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 */
public class Config {
    public static final String ROOT_URL = "http://www.shentehui.com";

    public static String getCatgoryUrl(int id) {
        String ID = String.valueOf(id);
        String catgoryUrl = String.format("%s/index/cate/cid/%s.html", ROOT_URL,ID);
        return catgoryUrl;
    }
}
