#!/bin/bash

DESKTOPLAUNCHERURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/scripts/linux/Minecraft%20Mod%20Installer.desktop"
LAUNCHSCRIPTURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/scripts/linux/launch.sh"
LATESTJARURL="https://github.com/Luigifan/MinecraftModInstaller/blob/master/scripts/Minecraft%20Mod%20Installer.app/Contents/MacOS/mcmodinstaller.jar?raw=true"
ICONURL="https://raw.githubusercontent.com/Luigifan/MinecraftModInstaller/master/src/res/icon.png"


#1. Check if the dir ~/.mcmodinstaller exists
#2. Make if necessary
#3. wget the 3 necessary files
#4. Copy the .desktop to ~/.local/share/applications/

echo "Making necessary directories.."

if [ ! -d "$HOME/.mcmodinstaller" ]; then
	mkdir "$HOME/.mcmodinstaller"
fi

echo "Checking for Java.."
#check if java even exists
echo "Retrieving files.."
#download
wget -q -O "$HOME/.mcmodinstaller/mcmodinstaller.jar" $LATESTJARURL
wget -q -O "$HOME/.local/share/applications/Minecraft Mod Installer.desktop" $DESKTOPLAUNCHERURL
wget -q -O "$HOME/.mcmodinstaller/icon.png" $ICONURL
wget -q -O "$HOME/.mcmodinstaller/launch.sh" $LAUNCHSCRIPTURL

echo "Writing .desktop.."
#
echo -e "#!/usr/bin/env xdg-open\n[Desktop Entry]\nVersion=1.0\nType=Application\nTerminal=false\nName=Minecraft Mod Installer\nComment=\"A simple Java based mod installer for Minecraft\"\nExec=java -jar $HOME/.mcmodinstaller/mcmodinstaller.jar\nIcon=$HOME/.mcmodinstaller/icon.png" >> "$HOME/.local/share/applications/mcmodinstaller.desktop"
#
echo "Setting permissions.."
chmod +x "$HOME/.mcmodinstaller/launch.sh"
chmod +x "$HOME/.local/share/applications/mcmodinstaller.desktop"
#
echo "All files seem to be installed correctly!"
echo "Press enter to exit.."
read -n1

