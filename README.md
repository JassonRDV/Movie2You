<table align = "center" style = "border-collapse: collapse;">
  <tr>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/movie2you_job_vacancy_test_result/blob/master/images/1.png" width="200" alt="Movie App Screenshot 1 (Replace with your screenshots)"></td>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/movie2you_job_vacancy_test_result/blob/master/images/2.png" width="200" alt="Movie App Screenshot 2 (Replace with your screenshots)"></td>
    <td style = "border: none;"><img src="https://github.com/JassonRDV/movie2you_job_vacancy_test_result/blob/master/images/3.png" width="200" alt="Movie App Screenshot 3 (Replace with your screenshots)"></td>
  </tr>
</table>

# 🎬 Movie App Android - Job Application Challenge

**Features:**
* ✅ Listing movies by category (e.g., Popular, Top Rated, Upcoming).
* ✅ Detailed view for each movie (title, overview, rating, etc.).
* ✅ Intuitive and functional user interface.

**Technologies:**
<p align="left">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/TheMovieDB-01D277?style=for-the-badge&logo=themoviedb&logoColor=white" alt="TheMovieDB"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose"/>
</p>

**Skills:** 💻 Android development, 🚀 Kotlin, 🎬 Consuming RESTful APIs (TheMovieDB), 🎨 Jetpack Compose

**Contact:**
<p align="left">
  <a href="https://www.linkedin.com/in/jasson-ramos-66b897340/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a> 
</p>

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
