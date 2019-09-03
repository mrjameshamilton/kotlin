/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.konan

import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.util.Version
import com.intellij.util.EnvironmentUtil
import com.intellij.util.SystemProperties
import java.io.File

object AndroidToolkit {
    val home: File? = EnvironmentUtil.getValue("ANDROID_SDK_ROOT")?.let { File(it) }
        ?: File(SystemProperties.getUserHome(), "Library/Android/sdk")
    val adb: File? = home?.let { File(File(it, "platform-tools"), "adb".exe) }
    val emulator: File? = home?.let { File(File(it, "tools"), "emulator".exe) }
    val buildTools: File? = home?.let { home ->
        File(home, "build-tools").listFiles()?.maxBy {
            Version.parseVersion(it.name) ?: Version(0, 0, 0)
        }
    }
    val aapt: File? = buildTools?.let { File(it, "aapt".exe) }

    val avdData: File = File(File(SystemProperties.getUserHome(), ".android"), "avd")

    private val String.exe
        get() =
            if (SystemInfo.isWindows) "$this.exe"
            else this
}