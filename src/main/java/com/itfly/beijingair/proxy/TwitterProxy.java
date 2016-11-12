package com.itfly.beijingair.proxy;

import com.itfly.beijingair.entity.AirIndex;
import com.itfly.beijingair.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhoufeiyu on 12/11/2016.
 */
@Service
public class TwitterProxy {
    private static final Logger LOG = Logger.getLogger(TwitterProxy.class);

    private static final String BeijingAir = "BeijingAir";
    private static final String PMHourly = "PM2.5";
    private static final String PMDaily = "PM2.5 24hr avg";  // ignore this one
    private static final String Semicolon = ";";

    private static final Twitter twitter = TwitterFactory.getSingleton();

    public AirIndex getLatestAirIndex() {
        try {
            List<Status> statuses = twitter.getUserTimeline(BeijingAir);
            for (Status status : statuses) {
                AirIndex airIndex = buildAirIndex(status);
                if (airIndex != null) {
                    return airIndex;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return null;
    }

    public List<AirIndex> getAll() {
        int pagenum = 1;
        List<Status> statuses = new ArrayList<>();

        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pagenum++, 100);
                statuses.addAll(twitter.getUserTimeline(BeijingAir, page));
                if (statuses.size() == size) {
                    break;
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }

        return statuses.stream()
                .map(this::buildAirIndex)
                .filter(index -> index != null)
                .collect(Collectors.toList());
    }

    private AirIndex buildAirIndex(Status status) {
        String text = status.getText();
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        String[] parts = Arrays.stream(text.split(Semicolon))
                .map(String::trim)
                .toArray(String[]::new);

        if (parts.length != 5) {
            LOG.warn("tweet should have five parts, tweet: " + text);
            return null;
        }

        if (!PMHourly.equals(parts[1])) {
            if (!PMDaily.equals(parts[1])) {
                LOG.error("tweet has another type, tweet: " + text);
            }
            return null;
        }

        return buildAirIndex(parts);

    }

    // TODO : check data validation
    private AirIndex buildAirIndex(String[] parts) {
        AirIndex airIndex = new AirIndex();

        airIndex.setDateTime(DateTimeUtils.fromStr(parts[0]));
        airIndex.setConcentration(Double.parseDouble(parts[2]));
        airIndex.setAqi(Integer.parseInt(parts[3]));
        airIndex.setDefinition(parts[4]);

        return airIndex;
    }

}
