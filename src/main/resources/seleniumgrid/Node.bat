@echo [off],
java -jar selenium-server-standalone-3.141.0.jar -role node -hub http://172.20.10.5:4444/grid/register -port 5555
'