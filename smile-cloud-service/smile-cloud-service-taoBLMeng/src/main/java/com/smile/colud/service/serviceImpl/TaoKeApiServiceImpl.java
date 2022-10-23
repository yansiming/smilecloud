package com.smile.colud.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtk.main.ApiClient;
import com.smile.colud.service.TaoKeApiService;
import com.smile.colud.util.TaokeApiUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * @author yansm
 * @version 1.0
 * @date 06/23/2021 21:52
 */
@Service
public class TaoKeApiServiceImpl implements TaoKeApiService {
    @Autowired
    private TaokeApiUtil taokeApiUtil;
    @Override
    public String getTaoPassword(String taoPassword) {
        return taokeApiUtil.getTaoPassword(taoPassword);
    }
}
