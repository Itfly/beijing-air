package com.itfly.beijingair.controller;

import com.itfly.beijingair.entity.AirIndex;
import com.itfly.beijingair.proxy.TwitterProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoufeiyu on 12/11/2016.
 */
@RestController
public class AirIndexController {

    @Autowired
    private TwitterProxy twitterProxy;

    @RequestMapping("/airindex")
    public AirIndex get() {
        return twitterProxy.getLatestAirIndex();
    }


}
