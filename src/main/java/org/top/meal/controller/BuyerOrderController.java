package org.top.meal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.top.meal.converter.OrderForm2OrderDTO;
import org.top.meal.dto.OrderDTO;
import org.top.meal.enums.ResultEnum;
import org.top.meal.exception.SellException;
import org.top.meal.form.OrderForm;
import org.top.meal.service.BuyerService;
import org.top.meal.service.OrderService;
import org.top.meal.utils.ResultVOUtil;
import org.top.meal.vo.ResultVO;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确, orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO create = orderService.create(orderDTO);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("orderId",create.getOrderId());

        return ResultVOUtil.success(resultMap);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> findAll(@RequestParam("openid") String openid,
                                            @RequestParam(value="page",defaultValue = "0") int page,
                                            @RequestParam(value="size",defaultValue = "10") int size){

        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest request= PageRequest.of(page,size,sort);
        Page<OrderDTO> orderDTOPage = orderService.findAll(openid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> findDetail(@RequestParam("openid") String openid,
                                         @RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单详情】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO orderCancel(@RequestParam("openid") String openid,
                                          @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
