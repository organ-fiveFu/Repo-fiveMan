package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.business.BusSatisfactionMeasurement;
import com.nurse.healthy.result.UserInfoToken;

public interface BusSatisfactionMeasurementService {
    BusSatisfactionMeasurement add(BusSatisfactionMeasurement busSatisfactionMeasurement, UserInfoToken userInfo);
}
