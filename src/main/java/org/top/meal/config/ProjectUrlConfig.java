package org.top.meal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "projecturl")
@Data
public class ProjectUrlConfig {

    //微信公众平台授权Url
    public String wechatMpAuthorize;


    //微信开放平台授权Url
    public String wechatOpenAuthorize;

    //项目Url
    public String sell;
}
