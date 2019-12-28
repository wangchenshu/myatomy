package com.myatomy.myatomy.controller;


import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.myatomy.myatomy.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@LineMessageHandler
public class LineBotProductsController {

    @Autowired
    private ProductsService productsService;

    @EventMapping
    public List<Object> handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String eventStr = event.getMessage().getText();
        AtomicReference<String> retStr = new AtomicReference<>("只要輸入 艾多美新品名稱");
        List<Object> list = new ArrayList<>();
        list.add(
                new TemplateMessage(retStr.get(), new ButtonsTemplate(
                        URI.create("https://firebasestorage.googleapis.com/v0/b/atomy-bot.appspot.com/o/%E8%89%BE%E5%A4%9A%E7%BE%8E%20%E8%89%BE%E4%B8%8D%E9%87%8B%E6%89%8B%E7%B6%93%E5%85%B8%E7%B3%BB%E5%88%97.jpg?alt=media&token=ebbb8e46-6d86-4229-9e29-67b4858ea0d1"),
                        "艾多美新品 Bot 1.0 Spring",
                        "艾多美新品上市",
                        Arrays.asList(
                                new MessageAction("艾不釋手經典系列", "艾多美 艾不釋手經典系列"),
                                new MessageAction("托特包", "艾多美 托特包"),
                                new MessageAction("物理性防曬膏", "艾多美 物理性防曬膏"),
                                new MessageAction("新春紅包袋", "艾多美 新春紅包袋")
                        )))
        );

        List<String> stringList = new ArrayList<>();

        productsService.findByNameContaining(eventStr)
                .forEach(products -> {
                    stringList.add(
                            "名稱: " + products.getName() + "\n" +
                                    "價格: " + products.getPrice() + "\n" +
                                    "PV: " + products.getPoint()
                    );
                });
//        productsService.findByNameContaining(eventStr).ifPresent(products -> {
//            retStr.set(
//                    "名稱: " + products.getName() + "\n" +
//                    "價格: " + products.getPrice() + "\n" +
//                    "PV: " + products.getPoint()
//            );
//        });

        int strLen = stringList.size();
        if (strLen > 4) {
            strLen = 4;
        }
        stringList.subList(0, strLen).forEach(item -> {
            list.add(new TextMessage(item));
        });

        return list;
    }
}
