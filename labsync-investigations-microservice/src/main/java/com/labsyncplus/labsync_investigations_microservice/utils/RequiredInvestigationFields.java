package com.labsyncplus.labsync_investigations_microservice.utils;

import com.labsyncplus.labsync_investigations_microservice.exceptions.InvestigationNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class RequiredInvestigationFields {
    private static final Map<Long, String[]> requiredFieldsMap = new HashMap<>();
    private static final Map<String, Class<?>> fieldTypesMap = new HashMap<>();

    static {
        requiredFieldsMap.put(1L, new String[]{"fbsValue"});

        fieldTypesMap.put("fbsValue", Double.class);
    }

    public static void checkRequiredFieldAvailabilityAndType(long investigationId, Map<String, Object> investigationData) throws InvestigationNotFoundException {
        if (!requiredFieldsMap.containsKey(investigationId)) throw new InvestigationNotFoundException("Requested investigation is not defined");

        String[] requiredFields = requiredFieldsMap.get(investigationId);

        for (String fieldName: requiredFields) {
            if (!investigationData.containsKey(fieldName)) {
                throw new InvestigationNotFoundException("All required fields are not available");
            }

            Class<?> expectedType = fieldTypesMap.get(fieldName);
            if (expectedType != null && !expectedType.isInstance(investigationData.get(fieldName))) {
                throw new InvestigationNotFoundException("Field " + fieldName + " is not of type " + expectedType.getSimpleName());
            }
        }
    }
}
