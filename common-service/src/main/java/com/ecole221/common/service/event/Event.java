package com.ecole221.common.service.event;

import java.util.Date;
import java.util.UUID;

public interface Event {
    UUID getEventId();
    Date getEventDate();
}
