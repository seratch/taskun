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

import java.util.HashMap;
import java.util.Map;

public class SchedulerConfig {

	public boolean enableInvokingScheduler = true;

	public boolean enableLoggingForEachCrondInvocation = false;

	public Map<String, String> namedServers = new HashMap<String, String>();

	public void putNamedServer(String name, String hostname) {
		namedServers.put(name, hostname);
	}

	public void removeNamedServer(String name) {
		namedServers.remove(name);
	}

	public String getNamedServerHostname(String name) {
		return namedServers.get(name);
	}

}
