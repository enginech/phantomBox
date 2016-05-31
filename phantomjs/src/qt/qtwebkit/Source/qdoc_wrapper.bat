@echo off
SetLocal EnableDelayedExpansion
(set QT_VERSION=5.6.1)
(set QT_VER=5.6)
(set QT_VERSION_TAG=561)
(set QT_INSTALL_DOCS=F:/phantomjs/src/qt/qtbase/doc)
F:\phantomjs\src\qt\qtbase\bin\qdoc.exe %*
EndLocal
