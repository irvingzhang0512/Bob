package com.emmairving.bob.server.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by irving on 17/2/4.
 */
public class ConstantValueForServer {
    public static Map<String, String> RAW_DATA_UNIT_MAP = new HashMap<String, String>();
    static {
        RAW_DATA_UNIT_MAP.put("Voltage", "V");
        RAW_DATA_UNIT_MAP.put("Current", "A");
        RAW_DATA_UNIT_MAP.put("ActivePower", "V");
        RAW_DATA_UNIT_MAP.put("ReactivePower", "V");
        RAW_DATA_UNIT_MAP.put("ApparentPower", "V");
        RAW_DATA_UNIT_MAP.put("PowerFactor", "");
        RAW_DATA_UNIT_MAP.put("ElectricEnergy", "KWh");
        RAW_DATA_UNIT_MAP = Collections.unmodifiableMap(RAW_DATA_UNIT_MAP);
    }


    public static Map<String, Integer> RAW_DATA_INDEX_MAP = new HashMap<String, Integer>();
    static {
        RAW_DATA_INDEX_MAP.put("Voltage", 0);
        RAW_DATA_INDEX_MAP.put("Current", 1);
        RAW_DATA_INDEX_MAP.put("ActivePower", 2);
        RAW_DATA_INDEX_MAP.put("ReactivePower", 3);
        RAW_DATA_INDEX_MAP.put("ApparentPower", 4);
        RAW_DATA_INDEX_MAP.put("PowerFactor", 5);
        RAW_DATA_INDEX_MAP.put("ElectricEnergy", 6);
        RAW_DATA_INDEX_MAP = Collections.unmodifiableMap(RAW_DATA_INDEX_MAP);
    }
}
