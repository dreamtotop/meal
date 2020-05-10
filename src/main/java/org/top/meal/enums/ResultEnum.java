package org.top.meal.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    ORDER_PAY_UPDATE_ERROR(18,"订单支付状态更新失败"),

    CART_EMPTY(19,"购物车为空"),

    ORDER_OWNER_ERROR(20,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(21,"微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(22,"微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(23,"订单取消成功"),

    ORDER_FINISH_SUCCESS(24,"订单完结成功"),

    PRODUCT_STATUS_ERROR(25,"商品状态不正确"),

    PRODUCT_STATUS_SUCCESS(26,"商品状态修改成功"),

    LOGIN_FAIL(27,"登录失败，登录信息不正确"),

    LOGOUT_SUCCESS(28,"登出成功")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
