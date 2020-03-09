/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.display.darkmode;

import android.content.Context;
import android.app.UiModeManager;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;

public class DarkModeCustomEndTimePreferenceController extends BasePreferenceController {

    private UiModeManager mUiModeManager;
    private DarkModeTimeFormatter mTimeFormatter;

    public DarkModeCustomEndTimePreferenceController(Context context, String key) {
        super(context, key);

        mUiModeManager = context.getSystemService(UiModeManager.class);
        mTimeFormatter = new DarkModeTimeFormatter(context);
    }

    @Override
    public int getAvailabilityStatus() {
        return BasePreferenceController.AVAILABLE;
    }

    @Override
    public final void updateState(Preference preference) {
        preference
                .setVisible(mUiModeManager.getNightModeAutoMode()
                        == UiModeManager.MODE_NIGHT_CUSTOM);
        preference.setSummary(mTimeFormatter.getFormattedTimeString(
                mUiModeManager.getNightModeCustomEndTime()));
    }
}
