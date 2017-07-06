package com.sinjon.ticket.clawer.core;

import com.sinjon.ticket.clawer.config.Config;
import com.sinjon.ticket.pojo.Good;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 */
public class ClawerCoreService {

    public List<Good> fetchHomeGoodInfo(){
        List<Good> goodList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(Config.ROOT_URL).get();
            String html = document.html();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodList;
    }

}
