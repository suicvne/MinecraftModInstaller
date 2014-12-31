#!/bin/sh

DESKTOPLAUNCHERURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/scripts/linux/Minecraft%20Mod%20Installer.desktop"
LAUNCHSCRIPTURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/scripts/linux/launch.sh"
LATESTJARURL="https://github.com/Luigifan/MinecraftModInstaller/blob/master/scripts/Minecraft%20Mod%20Installer.app/Contents/MacOS/mcmodinstaller.jar?raw=true"
ICONURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/src/res/icon.png"

#1. Check if the dir ~/.mcmodinstaller exists
#2. Make if necessary
#3. wget the 3 necessary files
#4. Copy the .desktop to ~/.local/share/applications/

echo "Making necessary directories.."

if [ ! -d "~/.mcmodinstaller" ]; then
	mkdir "~/.mcmodinstaller"
fi

echo "Checking for Java.."
#check if java even exists
if type -p java; then

elif [[ -n "$JAVA_HOME"]] && [[ -x "$JAVA_HOME/bin/java" ]]; then

else
	echo "no java"
	exit
fi

echo "Checking Java version.."
#check version
if [["$_java"]]; then
	version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
	echo version "$version"
	if [[ "$version" > "1.6"]]; then
		echo "good"
	else
		echo "you need at least Java 1.7 to run this program!"
		exit
	fi
fi

echo "Retrieving files.."
#download
wget -O "~/.mcmodinstaller/mcmodinstaller.jar" $LATESTJARURL
wget -O "~/.local/share/applications/Minecraft Mod Installer.desktop" $DESKTOPLAUNCHERURL
wget -O "~/.mcmodinstaller/icon.png" $ICONURL
wget -O "~/.mcmodinstaller/launch.sh" $LAUNCHSCRIPTURL

