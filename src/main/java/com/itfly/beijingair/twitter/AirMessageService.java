package com.itfly.beijingair.twitter;

import org.apache.commons.lang3.StringUtils;
import twitter4j.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fezho on 11/12/2016.
 */
public class AirMessageService {

    private static final Logger LOG = Logger.getLogger(AirMessageService.class);

    private static final String BeijingAir = "BeijingAir";
    private static final String PMHourly = "PM2.5";
    private static final String PMDaily = "PM2.5 24hr avg";
    private static final String Semicolon = ";";

    public Message getLatestMessage() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getUserTimeline(BeijingAir);
        for (Status status : statuses) {
            String text = status.getText();
            if (StringUtils.isEmpty(text)) {
                continue;
            }

            String[] parts = Arrays.stream(text.split(Semicolon))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length != 5) {
                LOG.warn("tweet should be have five parts, tweet: " + text);
                continue;
            }

            if (!PMHourly.equals(parts[1])) {
                if (!PMDaily.equals(parts[1])) {
                    LOG.error("tweet has another type, tweet: " + text);
                }
                continue;
            }

            return createMessage(parts);
        }

        return null;
    }

    private Message createMessage(String[] parts) {
        Message message = new Message();

        message.setDateTime(DateTimeUtils.fromStr(parts[0]));
        message.setConcentration(Double.parseDouble(parts[2]));
        message.setAqi(Integer.parseInt(parts[3]));
        message.setDefinition(parts[4]);

        return message;
    }

    public static void main(String[] args) {
        try {

            Message message = new AirMessageService().getLatestMessage();
            message = new AirMessageService().getLatestMessage();

        } catch (TwitterException exception) {

        }

    }
}
