package com.rendezvous.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Conversion {

    public static LocalDateTime adjustTime(LocalDateTime localDateTime) {
        Instant instant = Instant.ofEpochSecond(localDateTime.toEpochSecond(ZoneOffset.UTC));
        ZonedDateTime atZone = instant.atZone(ZoneId.systemDefault());
        return atZone.toLocalDateTime();
    }
}
