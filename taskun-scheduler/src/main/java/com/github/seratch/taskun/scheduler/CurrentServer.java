/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.util.StringUtil;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

public final class CurrentServer {

    public static boolean isWorkingOnNamedServerHost(SchedulerConfig config, String name) {
        String expected = config.getNamedServerHostname(name);
        if (StringUtil.isEmpty(expected)) {
            return false;
        }
        return getHostname().matches(expected);
    }

    public static String getServerName(SchedulerConfig config) {
        if (config != null) {
            Map<String, String> servers = config.namedServers;
            Set<String> names = servers.keySet();
            for (String name : names) {
                if (getHostname().matches(servers.get(name))) {
                    return name;
                }
            }
        }
        return getHostname();
    }

    public static String getHostname() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (Exception e) {
            return "localhost";
        }
    }

}
