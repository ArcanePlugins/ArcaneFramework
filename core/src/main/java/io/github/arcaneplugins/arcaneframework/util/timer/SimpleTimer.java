/*
This file was retrived from the ArcaneFramework source code. Learn more
about ArcaneFramework at <https://github.com/lokka30/ArcaneFramework/>.

Copyright © 2022 lokka30 and contributors

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.arcaneplugins.arcaneframework.util.timer;

import java.text.DecimalFormat;

@SuppressWarnings("unused")
public final class SimpleTimer {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    private long startTime = System.currentTimeMillis();

    public long getDurationMillis() {
        return System.currentTimeMillis() - getStartTime();
    }

    public float getDurationSeconds() {
        return getDurationMillis() / 1000f;
    }

    public String getFormattedDurationSeconds() {
        return DECIMAL_FORMAT.format(getDurationSeconds());
    }

    public long getStartTime() {
        return startTime;
    }

    public void restart() {
        this.startTime = System.currentTimeMillis();
    }
}
