package org.top.meal.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class SellerInfo {


    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

    private Date createTime = new Date();

    private Date updateTime = new Date();

}
