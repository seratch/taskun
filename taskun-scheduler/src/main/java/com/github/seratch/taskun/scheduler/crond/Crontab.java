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
package com.github.seratch.taskun.scheduler.crond;

import java.util.List;

public class Crontab {

	public RawCrontabLine rawLine;

	public CrontabElement minuteElement;

	public List<Integer> minuteFixedInvocations;

	public CrontabElement hourElement;

	public List<Integer> hourFixedInvocations;

	public CrontabElement dayElement;

	public List<Integer> dayFixedInvocations;

	public CrontabElement monthElement;

	public List<Integer> monthFixedInvocations;

	public CrontabElement dayOfWeekElement;

	public List<Integer> dayOfWeekFixedInvocations;

	public CrontabCommandClassNameElement commandClassName;

	public List<String> namedServers;

	public boolean isIntervalInvocation;

	public long intervalSeconds;

	public long initialIntervalSeconds;

	public long multiplicity;

	public long nextInvocationTime;

	public boolean isServerToInvoke(String serverName) {
		if (namedServers == null || namedServers.size() == 0) {
			return true;
		}
		for (String nameServer : namedServers) {
			if (serverName.equals(nameServer)) {
				return true;
			}
		}
		return false;
	}

}
