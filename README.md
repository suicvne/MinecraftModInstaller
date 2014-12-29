MinecraftModInstaller
=====================

designed to use with eclipse

windowbuilder helps, get the integration version here: http://download.eclipse.org/windowbuilder/WB/integration/4.4/

Running the Application
=======================

i've found from my own personal experiences that after importing the project from Git/GitHub into Eclipse, run profiles will not carry over. in order to allow running of this application, do the following.

1. Click the green run button, click "Run Configurations"
2. Select "Java Application" in the left side list, then click the plus to add a new profile
3. Name it whatever you want
4. For the main class, be sure to select 'MainApplicationWindow' or 'MainApplicationWindow.java'. 'OSXAboutDialog' is strictly for testing purposes only and will probably be removed.
5. Click the arguments tab and under program arguments, add the following (no quotes, case sensitive, no spaces): "-cmdLine". The purpose of this argument is actually for release purposes to make sure the user runs the application from the command line so they can see the output.
6. Be sure to hit apply, then run if you wish.
