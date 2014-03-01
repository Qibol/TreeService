echo -----REDEPLOYING QIBOL------
echo ----Setting environment-----
set ANT_HOME=D:\Projects\Qibol\apache-ant-1.9.2
echo ANT_HOME %ANT_HOME%
set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_43
echo JAVA_HOME %JAVA_HOME%
set PATH=%PATH%;%ANT_HOME%\bin
echo PATH %PATH%

echo ----Stopping Tomcat ---

echo -----Executing ant script-----
ant -f ../build.xml redeploy -Dbuild.filename=etalon_build
echo ---Executing ant script END---

echo ----Starting Tomcat ---