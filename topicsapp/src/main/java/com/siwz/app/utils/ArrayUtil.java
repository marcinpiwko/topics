package com.siwz.app.utils;

import java.util.List;

public class ArrayUtil {

    private ArrayUtil() {

    }

    public static boolean isEmpty(List<?> list) {
        return (list == null || list.isEmpty());
    }
}
