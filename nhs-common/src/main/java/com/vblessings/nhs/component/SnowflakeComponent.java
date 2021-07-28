package com.vblessings.nhs.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SnowflakeComponent {
    @Value("${server.dataCenterId}")
    private long dataCenterId;

    @Value("${server.workId}")
    private long workId;

    private static volatile SnowflakeIdWorker instance;

    public SnowflakeIdWorker getInstance() {
        if (instance == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (instance == null) {
                    log.info("when instance, workId = {}, dataCenterId = {}", workId, dataCenterId);
                    instance = new SnowflakeIdWorker(workId, dataCenterId);
                }
            }
        }
        return instance;
    }

    public Long nextId() {
        if(instance == null) {
            getInstance();
        }
        return instance.nextId();
    }
}
