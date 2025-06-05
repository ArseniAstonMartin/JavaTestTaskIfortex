package com.example.java_ifortex_test_task.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DeviceType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public DeviceType convertToEntityAttribute(Integer dbData) {
        return dbData != null ? DeviceType.fromCode(dbData) : null;
    }
}