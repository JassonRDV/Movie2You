# 🎬 Movie App Android - Portfolio Project

**Purpose:** 🎯 To demonstrate skills in Android and Kotlin development for a portfolio project.

**Features:**
* ✅ Listing movies by category (e.g., Popular, Top Rated, Upcoming).
* ✅ Detailed view for each movie (title, overview, release date, rating, etc.).
* ✅ Intuitive and functional user interface.

**Technologies:**
<p align="left">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/TheMovieDB-01D277?style=for-the-badge&logo=themoviedb&logoColor=white" alt="TheMovieDB"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose"/>
</p>

<table align = "center" style = "border-collapse: collapse;">
  <tr>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/pokedex_job_vacancy_test_result/blob/main/images/1.png" width="200" alt="Movie App Screenshot 1 (Replace with your screenshots)"></td>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/pokedex_job_vacancy_test_result/blob/main/images/2.png" width="200" alt="Movie App Screenshot 2 (Replace with your screenshots)"></td>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/pokedex_job_vacancy_test_result/blob/main/images/3.png" width="200" alt="Movie App Screenshot 3 (Replace with your screenshots)"></td>
  </tr>
</table>

**Skills:** 💻 Android development, 🚀 Kotlin, 🎬 Consuming RESTful APIs (TheMovieDB), 🎨 Jetpack Compose

**Contact:**
<p align="left">
  <a href="https://www.linkedin.com/in/jasson-ramos-66b897340/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a> ✉️
</p>

**License:** 🛡️ MIT License 🔓

**Setup:**

To use this application, you need to add your TheMovieDB API key. Follow these steps:

1.  Open your project in Android Studio.
2.  Navigate to the `app` module's `build.gradle` file.
3.  Inside the `android.defaultConfig` block, add a `buildConfigField` to store your API key:

    ```gradle
    android {
        // ...
        defaultConfig {
            // ...
            buildConfigField("String", "THE_MOVIE_DB_API_KEY", "\"YOUR_API_KEY\"")
        }
        // ...
    }
    ```

4.  Replace `"YOUR_API_KEY"` with your actual API key from TheMovieDB ([https://www.themoviedb.org/](https://www.themoviedb.org/)).

**Note:** The screenshots are placeholders. Please replace the image URLs with actual screenshots of your Movie App.
