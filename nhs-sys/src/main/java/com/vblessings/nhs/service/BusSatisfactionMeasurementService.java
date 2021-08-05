package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusSatisfactionMeasurement;
import com.vblessings.nhs.result.UserInfoToken;

public interface BusSatisfactionMeasurementService {
    void add(BusSatisfactionMeasurement busSatisfactionMeasurement, UserInfoToken userInfo);

    BusSatisfactionMeasurement selectMeasurement(String phone);
}
