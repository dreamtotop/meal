package org.top.meal.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.meal.dto.OrderDTO;
import org.top.meal.enums.ResultEnum;
import org.top.meal.exception.SellException;
import org.top.meal.service.OrderService;
import org.top.meal.service.PayService;
import org.top.meal.utils.JsonUtil;
import org.top.meal.utils.MathUtil;


@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME="微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum. WXPAY_MP);

        log.info("【微信支付】发起支付， request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={},",JsonUtil.toJson(payResponse));

        return payResponse;

    }

    public PayResponse notify(String notifyData){
        //1.验证签名
        //2.支付状态
        //3.支付金额
        //4.支付人(支付人 == 下单人)
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知, payResponse={}",payResponse);
        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if(orderDTO == null){
            log.error("【微信支付】 异步通知， 订单不存在,orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致（***注意调用的比较方法，还有数值的精度 比如0.1和0.10）(equals()和compareTo()方法都会出现错误)
        //if(!payResponse.getOrderAmount().equals(orderDTO.getOrderAmount()))
        //if(orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount()))!=0)
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("【微信支付】 异步通知,支付金额和订单金额不一致，orderId={},微信通知金额={},系统结算金额={},",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单的支付状态
        orderService.pay(orderDTO);
        return payResponse;


    }

    /*
     * 退款
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
        log.info("【微信退款】 refundRequest={}",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 refundResponse={}",JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
