package com.suse.salt.netapi.calls.wheel;

import com.suse.salt.netapi.calls.LocalCall;
import com.suse.salt.netapi.calls.WheelCall;

import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * salt.wheel.minions
 */
public class Minions {

    private Minions() {
    }

    private static final WheelCall<List<String>> CONNECTED =
            new WheelCall<>("minions.connected", Optional.empty(),
            new TypeToken<List<String>>(){});

    public static WheelCall<List<String>> connected() {
        return CONNECTED;
    }

    public static final LocalCall<String> install(List<String> argParam,Map<String,Object> kwParams){
        Map<String, Object> kwargs =new HashMap<>();
        kwParams.forEach((k,v)->kwargs.put(k,v));

        List<String> arg = new ArrayList<>();
        for(String argStr : argParam){
            arg.add(argStr);
        }
        return new LocalCall<String>("minions.install", Optional.of(arg),
                Optional.of(kwargs), new TypeToken<String>(){});
    }
}
