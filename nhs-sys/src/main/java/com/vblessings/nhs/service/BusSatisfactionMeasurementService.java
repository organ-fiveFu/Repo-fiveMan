package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.business.BusSatisfactionMeasurement;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;

public interface BusSatisfactionMeasurementService {
    void add(BusSatisfactionMeasurement busSatisfactionMeasurement);

  BusSatisfactionMeasurement selectMeasurement(String phone);
}
