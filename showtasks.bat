call runcrud.bat
timeout 25 /nobreak
if "%ERRORLEVEL%" == "0" goto startMozilla
echo.
echo RUNCRUD has errors - breaking work
goto fail

:startMozilla
cd C:\Program Files\Mozilla Firefox
start firefox.exe
cd C:\Users\Jan\Documents\Development\Projects\REST-API\tasks
if "%ERRORLEVEL%" == "0"  goto startGetTasks
echo Cannot start browser
goto fail

:startGetTasks
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot startGetTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.