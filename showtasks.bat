call runcrud
if "%ERRORLEVEL%" == "0" goto runie
echo.
echo runcrud has errors – breaking work
goto fail

:runie
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo runie has errors – breaking work
goto fail

:openpage
chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo runie has errors – breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.