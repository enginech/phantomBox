@echo off
SetLocal EnableDelayedExpansion
(set PATH=F:\phantomjs\src\qt\qtbase\lib;!PATH!)
if defined QT_PLUGIN_PATH (
    set QT_PLUGIN_PATH=F:\phantomjs\src\qt\qtbase\plugins;!QT_PLUGIN_PATH!
) else (
    set QT_PLUGIN_PATH=F:\phantomjs\src\qt\qtbase\plugins
)
F:\phantomjs\src\qt\qtbase\bin\uic.exe %*
EndLocal
