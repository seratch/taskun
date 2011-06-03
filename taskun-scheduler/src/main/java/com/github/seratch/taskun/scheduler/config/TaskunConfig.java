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
package com.github.seratch.taskun.scheduler.config;

import com.github.seratch.taskun.logging.TaskunLog;
import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl;

import java.util.HashMap;
import java.util.Map;

public class TaskunConfig {

    public boolean enableInvokingScheduler = true;

    public boolean enableLoggingForEachCrondInvocation = false;

    public Map<String, String> namedServers = new HashMap<String, String>();

    public Class<? extends TaskunLog> logImplClass = TaskunLogUtilLoggerImpl.class;

    public void setEnableInvokingScheduler(boolean enableInvokingScheduler) {
         this.enableInvokingScheduler = enableInvokingScheduler;
    }

    public void setEnableLoggingForEachCrondInvocation(boolean enableLoggingForEachCrondInvocation) {
         this.enableLoggingForEachCrondInvocation = enableLoggingForEachCrondInvocation;
    }

    public void setNamedServers(Map<String, String> namedServers) {
        this.namedServers = namedServers;
    }

    public void putNamedServer(String name, String hostname) {
        namedServers.put(name, hostname);
    }

    public void removeNamedServer(String name) {
        namedServers.remove(name);
    }

    public String getNamedServerHostname(String name) {
        return namedServers.get(name);
    }

    public <T extends TaskunLog> void setLogImplClass(Class<T> logImplClass) {
        this.logImplClass = logImplClass;
    }

    public Class<? extends TaskunLog> getLogImplClass() {
        return this.logImplClass;
    }


}
