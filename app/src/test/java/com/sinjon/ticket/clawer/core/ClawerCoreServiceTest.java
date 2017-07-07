package com.sinjon.ticket.clawer.core;

import com.sinjon.ticket.clawer.config.Config;
import com.sinjon.ticket.pojo.Good;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author SongJian
 * @Date 2017/7/7
 * @Email songjian0x00@163.com
 */
public class ClawerCoreServiceTest {


    @Test
    public void fetchHomePage() throws Exception {
        Document document = Jsoup.connect(Config.ROOT_URL).get();
        String html = document.html();
        Elements elements = document.select("div.list-good");
        for (Element element : elements) {
            String src = element.select("img[src]").attr("d-src");
            String select = element.select("h3.good-title").select("a").text();
            String price_current = element.select("div.good-price").select("span.price-current").text();
            String price_old = element.select("div.good-price").select("span.price-old").text();
            String discount = element.select("div.good-price").select("span.discount").text();
            String ticket = element.select("div.lingquan").select("a").attr("href");
            System.out.println(src);
            System.out.println(select);
            System.out.println(price_current);
            System.out.println(price_old);
            System.out.println(discount);
            System.out.println(Config.ROOT_URL+ticket);
        }

    }
    @Test
    public void fetchCatgoryGoodInfo() throws Exception {

        Document document = Jsoup.connect(Config.getCatgoryUrl(12)).get();
        Elements elements = document.select("div.list-good");
        for (Element element : elements) {
            String src = element.select("img[src]").attr("d-src");
            String select = element.select("h3.good-title").select("a").text();
            String price_current = element.select("div.good-price").select("span.price-current").text();
            String price_old = element.select("div.good-price").select("span.price-old").text();
            String discount = element.select("div.good-price").select("span.discount").text();
            String ticket = element.select("div.lingquan").select("a").attr("href");
            System.out.println(src);
            System.out.println(select);
            System.out.println(price_current);
            System.out.println(price_old);
            System.out.println(discount);
            System.out.println(Config.ROOT_URL+ticket);
        }
    }

    @Test
    public void fetchDataFromSearch() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("m", "search");
        data.put("a", "index");
        data.put("k", "洗发露");
        try {
            Document document = Jsoup.connect(Config.ROOT_URL).data(data).get();
            parserData(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String src = element.select("img[src]").attr("d-src");
            String title = element.select("h3.good-title").select("a").text();
            String price_current = element.select("div.good-price").select("span.price-current").text();
            String price_old = element.select("div.good-price").select("span.price-old").text();
            String discount = element.select("div.good-price").select("span.discount").text();
            String ticket = element.select("div.lingquan").select("a").attr("href");
            System.out.println(src);
            System.out.println(title);
            System.out.println(price_current);
            System.out.println(price_old);
            System.out.println(discount);
            System.out.println(Config.ROOT_URL+ticket);
        }
        return goodList;
    }
}