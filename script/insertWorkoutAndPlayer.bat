@echo off
setlocal enabledelayedexpansion

:: Create user
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -d "{\"username\":\"username\",\"email\":\"email@\",\"password\":\"password\"}" http://localhost:8080/user/create') do set "response1=%%i"

:: Login and extract token (this is approximate - Windows curl parsing is tricky)
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -d "{\"email\":\"email@\",\"password\":\"password\"}" http://localhost:8080/login') do set "login_response=!login_response! %%i"

:: Simple token extraction (assuming token is the last part after "token": "...")
for %%a in ("%login_response%") do (
    set "str=%%a"
    for /f "tokens=2 delims=:" %%b in ("!str:*token: =!") do (
        set "token=%%b"
        set "token=!token:"=!"
        set "token=!token:}=!"
        goto :token_extracted
    )
)
:token_extracted

:: Update user type with weight and height
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -H "Authorization: Bearer %token%" -d "{\"weight\":66,\"height\":1.69}" http://localhost:8080/user/type') do set "response2=%%i"

:: Create workout
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -H "Authorization: Bearer %token%" -d "{\"name\":\"testWorkout\",\"description\":\"testDesc\"}" http://localhost:8080/training/workout') do set "response3=%%i"

:: Create exercise
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -H "Authorization: Bearer %token%" -d "{\"nameExercise\":\"Push Up\",\"muscles\":\"Chest, Triceps\",\"repeticoes\":12,\"series\":3,\"workout_name\":\"testWorkout\"}" http://localhost:8080/exercise') do set "response4=%%i"

:: Create daily workout
for /f "delims=" %%i in ('curl -s -X POST -H "Content-Type: application/json" -H "Authorization: Bearer %token%" -d "{\"status\":\"OK\",\"workoutName\":\"testWorkout\"}" http://localhost:8080/workout/daily') do set "response5=%%i"

:: List daily workouts
for /f "delims=" %%i in ('curl -s -X GET -H "Content-Type: application/json" -H "Authorization: Bearer %token%" http://localhost:8080/workout/daily') do set "response6=%%i"

echo User creation response: %response1%
echo User type update response: %response2%
echo Workout creation response: %response3%
echo Exercise creation response: %response4%
echo Daily workout creation response: %response5%
echo Daily workout list response: %response6%

endlocal