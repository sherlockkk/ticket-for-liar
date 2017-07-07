package com.sinjon.ticket.clawer.core;

import com.sinjon.ticket.clawer.config.Config;
import com.sinjon.ticket.pojo.Good;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 */
public class ClawerCoreService {


    /**
     * 获取首页商品信息
     * @return
     */
    public List<Good> fetchHomeGoodInfo(){
        List<Good> goodList = null;
        try {
            Document document = Jsoup.connect(Config.ROOT_URL).get();
            goodList = parserData(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodList;
    }

    /**
     * 按分类信息获取商品页面
     * @param catgory
     * @return
     */
    public List<Good> fetchCatgoryGoodInfo(int catgory){
        List<Good> goodList = null;
        try {
            Document document = Jsoup.connect(Config.getCatgoryUrl(catgory)).get();
            goodList = parserData(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodList;
    }

    /**
     * 从搜索接口获取数据
     * @param keyWord 搜索关键字
     * @return
     */
    public List<Good> fetchDataFromSearch(String keyWord) {
        List<Good> goodList = null;
        Map<String, String> data = new HashMap<>();
        data.put("m", "search");
        data.put("a", "index");
        data.put("k", keyWord);
        try {
            Document document = Jsoup.connect(Config.ROOT_URL).data(data).get();
            goodList = parserData(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodList;
    }


    /**
     * 在转化后的Document对象中解析出我们需要的数据
     * @param document
     * @return
     */
    private static List<Good> parserData(Document document){
        List<Good> goodList = new ArrayList<>();
        Elements elements = document.select("div.list-good");
        for (Element element : elements) {
            Good good = new Good();
            String src = element.select("img[src]").attr("d-src");
            String title = element.select("h3.good-title").select("a").text();
            String price_current = element.select("div.good-price").select("span.price-current").text();
            String price_old = element.select("div.good-price").select("span.price-old").text();
            String discount = element.select("div.good-price").select("span.discount").text();
            String ticket = element.select("div.lingquan").select("a").attr("href");
            good.setImageUrl(src);
            good.setTitle(title);
            good.setPriceCurrent(price_current);
            good.setPriceOld(price_old);
            good.setDiscount(discount);
            good.setTicket(Config.ROOT_URL+ticket);
            goodList.add(good);
        }
        return goodList;
    }

}
