# Celebrity Guess Game

This is a simple Android-based guessing game where users try to identify celebrities based on their photos. The game randomly selects a celebrity image and challenges the player to enter the correct name.

## 🎯 Features

- Random celebrity image shown on each attempt
- Input field to type the guess
- Score tracking based on correct answers
- Local JSON file used to store celebrity names and image URLs
- Images loaded dynamically using Picasso library

## 📂 Project Structure

- `MainActivity.java`: Handles UI and core game logic
- `celebrities.json`: Stores the list of celebrity names and image URLs
- `ScoreTextView`, `ImageView`, `EditText`, and `Button`: Components used in the layout

## 🧰 Technologies Used

- Java
- Android SDK
- Android Studio
- Picasso (image loading)
- GSON (JSON parsing)

## ▶️ How to Run

1. Clone or download this repository
2. Open the project in Android Studio
3. Add your own celebrity images and data into `celebrities.json` (if needed)
4. Run the app on an emulator or physical Android device

## 📌 Notes

This project was developed as part of a Mobile Programming course. It is a beginner-friendly app to practice JSON parsing, image loading, and interactive UI design in Android.

