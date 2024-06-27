package com.labsyncplus.labsync_investigations_microservice.utils;

import com.labsyncplus.labsync_investigations_microservice.exceptions.InvestigationNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class RequiredInvestigationFields {
    private static final Map<Long, String[]> requiredFieldsMap = new HashMap<>();
    private static final Map<String, Class<?>> fieldTypesMap = new HashMap<>();

    static {
        requiredFieldsMap.put(1L, new String[]{
                "fbsValue",
                "fbsValueFlag"
        });
        requiredFieldsMap.put(2L, new String[]{
                "totalCalcium",
                "ionizedCalcium",
                "totalCalciumFlag",
                "ionizedCalciumFlag"
        });
        requiredFieldsMap.put(3L, new String[]{
                "totalCholesterol",
                "hdlCholesterol",
                "triglycerides",
                "ldlCholesterol",
                "vldlCholesterol",
                "totalCholToHdl",
                "totalCholesterolFlag",
                "hdlCholesterolFlag",
                "triglyceridesFlag",
                "ldlCholesterolFlag",
                "vldlCholesterolFlag",
        });
        requiredFieldsMap.put(4L, new String[]{
                "colour",
                "appearance",
                "reaction",
                "albumin",
                "reducingSubs",
                "bile",
                "urobilinogen",
                "pusCells",
                "redCells",
                "epithelialCells",
                "casts",
                "crystals",
                "organisms"
        });
        requiredFieldsMap.put(5L, new String[]{
                "wbcCount",
                "neutrophils",
                "lymphocytes",
                "eosinophils",
                "monocytes",
                "basophils",
                "haemoglobin",
                "rbcCount",
                "pcv",
                "mcv",
                "mch",
                "mchc",
                "plateletCount",
                "wbcCountFlag",
                "neutrophilsFlag",
                "lymphocytesFlag",
                "eosinophilsFlag",
                "monocytesFlag",
                "basophilsFlag",
                "haemoglobinFlag",
                "rbcCountFlag",
                "pcvFlag",
                "mcvFlag",
                "mchFlag",
                "mchcFlag",
                "plateletCountFlag",
        });

        fieldTypesMap.put("fbsValue", Double.class);
        fieldTypesMap.put("fbsValueFlag", String.class);

        fieldTypesMap.put("totalCalcium", Double.class);
        fieldTypesMap.put("ionizedCalcium", Double.class);
        fieldTypesMap.put("totalCalciumFlag", String.class);
        fieldTypesMap.put("ionizedCalciumFlag", String.class);

        fieldTypesMap.put("totalCholesterol", Double.class);
        fieldTypesMap.put("hdlCholesterol", Double.class);
        fieldTypesMap.put("triglycerides", Double.class);
        fieldTypesMap.put("ldlCholesterol", Double.class);
        fieldTypesMap.put("vldlCholesterol", Double.class);
        fieldTypesMap.put("totalCholToHdl", Double.class);
        fieldTypesMap.put("totalCholesterolFlag", String.class);
        fieldTypesMap.put("hdlCholesterolFlag", String.class);
        fieldTypesMap.put("triglyceridesFlag", String.class);
        fieldTypesMap.put("ldlCholesterolFlag", String.class);
        fieldTypesMap.put("vldlCholesterolFlag", String.class);
        fieldTypesMap.put("totalCholToHdlFlag", String.class);

        fieldTypesMap.put("colour", String.class);
        fieldTypesMap.put("appearance", String.class);
        fieldTypesMap.put("reaction", String.class);
        fieldTypesMap.put("albumin", String.class);
        fieldTypesMap.put("reducingSubs", String.class);
        fieldTypesMap.put("bile", String.class);
        fieldTypesMap.put("urobilinogen", String.class);
        fieldTypesMap.put("pusCells", String.class);
        fieldTypesMap.put("redCells", String.class);
        fieldTypesMap.put("epithelialCells", String.class);
        fieldTypesMap.put("casts", String.class);
        fieldTypesMap.put("crystals", String.class);
        fieldTypesMap.put("organisms", String.class);

        fieldTypesMap.put("wbcCount", Double.class);
        fieldTypesMap.put("neutrophils", Double.class);
        fieldTypesMap.put("lymphocytes", Double.class);
        fieldTypesMap.put("eosinophils", Double.class);
        fieldTypesMap.put("monocytes", Double.class);
        fieldTypesMap.put("basophils", Double.class);
        fieldTypesMap.put("haemoglobin", Double.class);
        fieldTypesMap.put("rbcCount", Double.class);
        fieldTypesMap.put("pcv", Double.class);
        fieldTypesMap.put("mcv", Double.class);
        fieldTypesMap.put("mch", Double.class);
        fieldTypesMap.put("mchc", Double.class);
        fieldTypesMap.put("plateletCount", Double.class);
        fieldTypesMap.put("wbcCountFlag", String.class);
        fieldTypesMap.put("neutrophilsFlag", String.class);
        fieldTypesMap.put("lymphocytesFlag", String.class);
        fieldTypesMap.put("eosinophilsFlag", String.class);
        fieldTypesMap.put("monocytesFlag", String.class);
        fieldTypesMap.put("basophilsFlag", String.class);
        fieldTypesMap.put("haemoglobinFlag", String.class);
        fieldTypesMap.put("rbcCountFlag", String.class);
        fieldTypesMap.put("pcvFlag", String.class);
        fieldTypesMap.put("mcvFlag", String.class);
        fieldTypesMap.put("mchFlag", String.class);
        fieldTypesMap.put("mchcFlag", String.class);
        fieldTypesMap.put("plateletCountFlag", String.class);
    }

    public static void checkRequiredFieldAvailabilityAndType(long investigationId, Map<String, Object> investigationData) throws InvestigationNotFoundException {
        if (!requiredFieldsMap.containsKey(investigationId)) throw new InvestigationNotFoundException("Requested investigation is not defined");

        String[] requiredFields = requiredFieldsMap.get(investigationId);

        for (String fieldName: requiredFields) {
            if (!investigationData.containsKey(fieldName)) {
                throw new InvestigationNotFoundException("All required fields are not available");
            }

            Class<?> expectedType = fieldTypesMap.get(fieldName);
            Object fieldValue = investigationData.get(fieldName);

            if (expectedType != null) {
                if (expectedType == Double.class) {
                    if (!(fieldValue instanceof Double) && !(fieldValue instanceof Integer)) {
                        throw new InvestigationNotFoundException("Field " + fieldName + " is not of type " + expectedType.getSimpleName());
                    }
                } else if (!expectedType.isInstance(fieldValue)) {
                    throw new InvestigationNotFoundException("Field " + fieldName + " is not of type " + expectedType.getSimpleName());
                }
            }
        }
    }
}
