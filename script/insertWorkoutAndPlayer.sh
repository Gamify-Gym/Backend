#!/bin/bash

# Create user
response1=$(curl -s -X POST -H 'Content-Type: application/json' -d '{"username":"username","email":"email@","password":"password"}' http://localhost:6000/user/create)

# Login and extract token
login_response=$(curl -s -X POST -H 'Content-Type: application/json' -d '{"email":"email@","password":"password"}' http://localhost:6000/login)
token=$(echo $login_response | grep -o '"token":"[^"]*' | cut -d'"' -f4)

# Update user type with weight and height
response2=$(curl -s -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -d '{"weight":66,"height":1.69}' http://localhost:6000/user/type)

# Create workout
response3=$(curl -s -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -d '{"name":"testWorkout","description":"testDesc"}' http://localhost:6000/training/workout)

# Create exercise
response4=$(curl -s -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -d '{"nameExercise":"Push Up","muscles":"Chest, Triceps","repeticoes":12,"series":3,"workout_name":"testWorkout"}' http://localhost:6000/exercise)

# Create daily workout
response5=$(curl -s -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -d '{"status":"OK","workoutName":"testWorkout"}' http://localhost:6000/workout/daily)

# List daily workouts
response6=$(curl -s -X GET -H 'Content-Type: application/json' -H "Authorization: Bearer $token" http://localhost:6000/workout/daily)

echo "User creation response: $response1"
echo "User type update response: $response2"
echo "Workout creation response: $response3"
echo "Exercise creation response: $response4"
echo "Daily workout creation response: $response5"
echo "Daily workout list response: $response6"