package com.channel2.mobile.ui.helpers;

import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class RegexpKeyedMap extends HashMap {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (obj instanceof String) {
            return super.put(obj, obj2);
        }
        throw new RuntimeException("RegexpKeyedMap - only accepts Strings as keys.");
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Iterator it = keySet().iterator();
        String strCleanKey = cleanKey(obj);
        while (it.hasNext()) {
            String string = it.next().toString();
            if (Utils.regexMatch(string, strCleanKey)) {
                return super.get(string);
            }
        }
        return null;
    }

    private String cleanKey(Object obj) {
        String string = obj.toString();
        return string.charAt(0) == '^' ? string.substring(1) : string;
    }
}
