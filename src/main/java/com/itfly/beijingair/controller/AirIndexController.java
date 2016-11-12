package com.itfly.beijingair.controller;

import com.itfly.beijingair.entity.AirIndex;
import com.itfly.beijingair.proxy.TwitterProxy;
import com.itfly.beijingair.storage.AirIndexStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhoufeiyu on 12/11/2016.
 */
@RestController
public class AirIndexController {

    @Autowired
    private TwitterProxy twitterProxy;
    @Autowired
    private AirIndexStorage airIndexStorage;

    @RequestMapping("/airindex")
    public AirIndex get() {
        return airIndexStorage.getLatest();
    }

    @RequestMapping("/airindex/all")
    public void getAll() {
        // for test
        List<AirIndex> list = twitterProxy.getAll();
        for (AirIndex airIndex : list) {
            //airIndexStorage.insert(airIndex);
        }

    }

}
