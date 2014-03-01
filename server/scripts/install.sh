echo -----REDEPLOYING QIBOL------
echo ----Updating repository-----
cd /home/dev/qibol/repos/server
hg update
cd /home/dev/qibol/repos/server/ServerApp/scripts/
echo ----Setting environment-----
export ANT_HOME=/usr/local/ant
echo ANT_HOME ${ANT_HOME}
export JAVA_HOME=/usr/java/jdk1.6.0_45
echo JAVA_HOME ${JAVA_HOME}
export PATH=${PATH}:${ANT_HOME}/bin
echo PATH ${PATH}

echo ----Stopping Tomcat ---
/etc/init.d/tomcat6 stop
echo -----Executing ant script-----
ant -f ../build.xml redeploy -Dbuild.filename=test_qibol_build
ant -f ../build.xml redeploy -Dbuild.filename=shared_test_qibol_build
echo ---Executing ant script END---

echo ----Starting Tomcat ---
/etc/init.d/tomcat6 start