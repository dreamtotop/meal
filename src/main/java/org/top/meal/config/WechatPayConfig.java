package org.top.meal.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(payConfig());
        return bestPayService;
    }

    @Bean
    public WxPayConfig payConfig(){
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wechatAccountConfig.getMpAppId());
        payConfig.setAppSecret(wechatAccountConfig.getMpAppSecret());
        payConfig.setMchId(wechatAccountConfig.getMchId());
        payConfig.setMchKey(wechatAccountConfig.getMchKey());
        payConfig.setKeyPath(wechatAccountConfig.getKeyPath());
        payConfig.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return payConfig;

    }
}
