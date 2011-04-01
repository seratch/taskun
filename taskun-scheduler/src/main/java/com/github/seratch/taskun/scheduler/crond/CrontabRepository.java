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

import java.util.ArrayList;
import java.util.List;

class CrontabRepository {

	private CrontabParser parser = new CrontabParser();

	private List<Crontab> crontabLines = new ArrayList<Crontab>();

	public CrontabRepository(List<Crontab> crontabLines) {
		this.crontabLines = crontabLines;
	}

	public List<Crontab> getCrontabLines() {
		return crontabLines;
	}

	public Crontab findByLine(RawCrontabLine line) {
		for (Crontab crontab : crontabLines) {
			if (crontab.rawLine.equals(line)) {
				return crontab;
			}
		}
		return null;
	}

	public void add(RawCrontabLine line) {
		// excludes commented or empty line
		if (!line.toString().startsWith("#") && !line.toString().equals("")
				&& !isAlreadyRegistered(line)) {
			crontabLines.add(parser.parseLine(line));
		}
	}

	public void remove(RawCrontabLine line) {
		if (isAlreadyRegistered(line)) {
			Crontab crontab = new CrontabRepository(crontabLines)
					.findByLine(line);
			crontabLines.remove(crontab);
		}
	}

	public void replace(RawCrontabLine before, RawCrontabLine after) {
		if (isAlreadyRegistered(before)) {
			remove(before);
		}
		add(after);
	}

	public boolean isAlreadyRegistered(RawCrontabLine line) {
		for (Crontab crontab : crontabLines) {
			if (crontab.rawLine.equals(line)) {
				return true;
			}
		}
		return false;
	}

}
