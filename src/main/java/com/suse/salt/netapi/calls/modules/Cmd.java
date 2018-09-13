package com.suse.salt.netapi.calls.modules;

import com.suse.salt.netapi.calls.LocalCall;
import com.suse.salt.netapi.results.CmdExecCodeAll;

import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * salt.modules.cmdmod
 */
public class Cmd {

    private Cmd() { }

    public static LocalCall<CmdExecCodeAll> execCodeAll(String lang, String code) {
        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        return new LocalCall<>("cmd.exec_code_all", Optional.of(Arrays.asList(lang, code)),
                Optional.of(args), new TypeToken<CmdExecCodeAll>(){});
    }

    public static LocalCall<String> run(String cmd) {
        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        args.put("cmd", cmd);
        return new LocalCall<>("cmd.run", Optional.empty(), Optional.of(args),
                new TypeToken<String>(){});
    }

    public static LocalCall<String> execCode(String lang, String code) {
        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        return new LocalCall<>("cmd.exec_code", Optional.of(Arrays.asList(lang, code)),
                Optional.of(args), new TypeToken<String>(){});
    }

    public static LocalCall<Boolean> hasExec(String cmd) {
        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        args.put("cmd", cmd);
        return new LocalCall<>("cmd.has_exec", Optional.empty(), Optional.of(args),
                new TypeToken<Boolean>(){});
    }

    public static LocalCall<CmdExecCodeAll> script(String cmd, List<String> args) {
        LinkedHashMap<String, Object> kwargs = new LinkedHashMap<>();
        if(args==null) args = new ArrayList<>();
        args.add(cmd);
        return new LocalCall("cmd.script", Optional.ofNullable(args), Optional.of(kwargs), new TypeToken<CmdExecCodeAll>() {
        });
    }
}
