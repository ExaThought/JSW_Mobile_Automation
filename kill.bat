@echo off
setlocal enabledelayedexpansion

:: Check if a port number is provided as an argument
if "%~1"=="" (
    echo Usage: %~nx0 ^<port_number^>
    exit /b 1
)

set PORT=%~1
set "PID="

:: Find the PID associated with the specified port
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :%PORT%') do (
    set "PID=%%a"
    echo Process ID using port %PORT%: !PID!
    :: Terminate the process
    taskkill /PID !PID! /F
)

:: Check if a PID was found and terminated
if not defined PID (
    echo No process is using port %PORT%.
)

endlocal