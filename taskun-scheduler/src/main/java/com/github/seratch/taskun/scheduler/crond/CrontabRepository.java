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

    private List<Crontab> crontabLines = new ArrayList<Crontab>();

    public CrontabRepository(List<Crontab> crontabLines) {
        this.crontabLines = crontabLines;
    }

    public List<Crontab> getCrontabLines() {
        return crontabLines;
    }

    public Crontab find(Crontab crontab) {
        for (Crontab each : crontabLines) {
            if (each.rawLine.equals(crontab.rawLine)) {
                return crontab;
            }
        }
        return null;
    }

    public void add(Crontab crontab) {
        // excludes commented or empty line
        RawCrontabLine line = crontab.rawLine;
        if (!line.toString().startsWith("#") && !line.toString().equals("")
                && !isAlreadyRegistered(crontab)) {
            crontabLines.add(crontab);
        }
    }

    public void remove(Crontab crontab) {
        RawCrontabLine line = crontab.rawLine;
        if (isAlreadyRegistered(crontab)) {
            Crontab found = new CrontabRepository(crontabLines).find(crontab);
            crontabLines.remove(found);
        }
    }

    public void replace(Crontab before, Crontab after) {
        if (isAlreadyRegistered(before)) {
            remove(before);
        }
        add(after);
    }

    public boolean isAlreadyRegistered(Crontab crontab) {
        for (Crontab each : crontabLines) {
            if (each.rawLine.equals(crontab.rawLine)) {
                return true;
            }
        }
        return false;
    }

}
