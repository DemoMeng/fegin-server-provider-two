package com.mqz.fegin.server.provider.two.web;

import com.mqz.fegin.server.provider.two.common.PrefixForGateway;
import com.mqz.mars.base.constants.FeignCloudConstant;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/11/11
 */
@RestController
@RequestMapping(value = PrefixForGateway.prefix+"/gateway")
public class GetewayController {

    @Resource
    private HttpServletRequest request;


    @GetMapping(value = "/1")
    public String handle(String userName){
        //TODO 模拟具体业务层调用

        // 解析网关传递过来的参数，并且判断是否给直连服务（所有请求均要经过网关）
        String gatewayWithValue = request.getParameter(FeignCloudConstant.Gateway.KEY_GATEWAY_WITH);
        if(StringUtils.isEmpty(gatewayWithValue)){
            return "【异常】该请求未经过网关！";
        }
        String tokenValue = request.getParameter(FeignCloudConstant.Gateway.KEY_TOKEN);
        return "【feign-server 1 网关转过来的调用 ：】GATEWAY="+gatewayWithValue+"，TOKEN="+tokenValue+" 成功";
    }

    @PostMapping(value = "/2")
    public String deal1(String json){
        return "【feign-server 2 网关转过来的调用 ：】传递的JSON为："+json;
    }


}
