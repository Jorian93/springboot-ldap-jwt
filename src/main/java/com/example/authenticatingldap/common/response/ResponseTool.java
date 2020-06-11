package com.example.authenticatingldap.common.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;



public class ResponseTool{
	
    private static void writerResponse(HttpServletResponse response,Integer status,String content){
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
                    .code(status)
                    .msg(content)
                    .build()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }