package com.suse.salt.netapi.calls.modules;

import com.suse.salt.netapi.calls.LocalCall;
import com.suse.salt.netapi.parser.JsonParser;
import com.suse.salt.netapi.results.StateApplyResult;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * salt.modules.state
 */
public class State {

    /**
     * Result type for state.apply
     */
    public static class ApplyResult extends StateApplyResult<JsonElement> {

        public <R> R getChanges(Class<R> dataType) {
            return JsonParser.GSON.fromJson(changes, dataType);
        }

        public <R> R getChanges(TypeToken<R> dataType) {
            return JsonParser.GSON.fromJson(changes, dataType.getType());
        }
    }

    private State() { }

    public static LocalCall<Map<String, ApplyResult>> apply(List<String> mods) {
        return apply(mods, Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static LocalCall<Map<String, ApplyResult>> apply(String... mods) {
        return apply(Arrays.asList(mods), Optional.empty(), Optional.empty(),
                Optional.empty());
    }

    public static LocalCall<Map<String, ApplyResult>> apply(List<String> mods,
            Optional<Map<String, Object>> pillar, Optional<Boolean> queue,
            Optional<Boolean> test) {
        Map<String, Object> kwargs = new LinkedHashMap<>();
        kwargs.put("mods", mods);
        pillar.ifPresent(p -> kwargs.put("pillar", p));
        queue.ifPresent(q -> kwargs.put("queue", q));
        test.ifPresent(t -> kwargs.put("test", t));
        return new LocalCall<>("state.apply", Optional.empty(), Optional.of(kwargs),
                new TypeToken<Map<String, ApplyResult>>(){});
    }

    public static LocalCall<Object> showHighstate() {
        return new LocalCall<>("state.show_highstate", Optional.empty(), Optional.empty(),
                new TypeToken<Object>(){});
    }
    public static LocalCall<Object> sls(List<String> argParam,Map<String,Object> kwParams) {
        Map<String, Object> kwargs =new HashMap<>();
        kwParams.forEach((k,v)->kwargs.put(k,v));

        List<String> arg = new ArrayList<>();
        for(String argStr : argParam){
            arg.add(argStr);
        }

        return new LocalCall<>("state.sls", Optional.ofNullable(argParam), Optional.ofNullable(kwParams),
                new TypeToken<Object>(){});
    }

}
