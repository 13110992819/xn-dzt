部署步骤：
1，eclipse导出war包

2. 本地war包上传至服务器
   scp -P 22 xn-dzt.war root@121.43.101.148:/mnt
   cdkj123456Q


scp -P 22 xn-dzt.war root@47.96.161.183:/mnt/www/dzt/package/v2.0.2/2nd
   cdkjqwertyQ01
3. 备份原先配置(如果第一次部署，跳过此步骤)
  ssh root@121.43.101.148 -p 22
  cdkj123456Q

  cd /mnt/www/dzt/tomcat_dzt_biz/webapps
  cp ./xn-dzt/WEB-INF/classes/application.properties .
  cp ./xn-dzt/WEB-INF/classes/config.properties .
  rm -rf xn-dzt*
  mv /mnt/xn-dzt.war .
   
4. 已备份配置文件放回原处,重启tomcat
  mv -f application.properties ./xn-dzt/WEB-INF/classes/
  mv -f config.properties ./xn-dzt/WEB-INF/classes/
  
  ../bin/shutdown.sh
  ../bin/startup.sh
  
6. 验证程序
  http://121.43.101.148:5604/xn-dzt/api

