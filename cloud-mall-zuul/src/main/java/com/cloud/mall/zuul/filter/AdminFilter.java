package com.cloud.mall.zuul.filter;

import com.cloud.mall.common.common.Constant;
import com.cloud.mall.user.model.pojo.User;
import com.cloud.mall.zuul.feign.UserFeignClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminFilter extends ZuulFilter {
    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        if (requestURI.contains("adminLogin")) {
            return false;
        }
        if (requestURI.contains("admin")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = currentContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        User currentUser = (User)session.getAttribute(Constant.MALL_USER);
        if (currentUser == null) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("{\n" +
                    "    \"status\": 10007,\n" +
                    "    \"msg\": \"用户未登录\",\n" +
                    "    \"data\": null\n" +
                    "}");
            currentContext.setResponseStatusCode(200);
            return null;
        }

        Boolean adminRole = userFeignClient.checkAdminRole(currentUser);
        if (!adminRole) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("{\n" +
                    "    \"status\": 10009,\n" +
                    "    \"msg\": \"无管理员权限\",\n" +
                    "    \"data\": null\n" +
                    "}");
            currentContext.setResponseStatusCode(200);
        }
        return null;
    }
}
