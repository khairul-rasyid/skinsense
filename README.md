# "Skin Sense" Application for Capstone Project
## Mobile Development

### Introduction
SkinSense is an Android application that aims to help users determine skincare ingredients that suit their facial skin type. This application uses the MVVM (Model-View-ViewModel) architecture with the implementation of DataStore, Room Database, and Retrofit.

### Project Structure
Here is the main folder and file structure in the project:
#### 1. data
Contains the data layer that manages the application's data sources, including settings, local data, and remote APIs.
##### datastore
SettingsPreference.kt: Class for managing user preferences using DataStore.
##### local
entity: Folder for defining local database entities.
room: Folder for defining DAO and Room database configuration.
##### remote/retrofit
ApiConfig.kt: Class for Retrofit configuration.
ApiService.kt: Interface for defining API endpoints.
Article.kt: Data model for articles received from API.
Result.kt: Class for wrapping API response results.
SkinSenseRepository.kt: Repository that manages data from various sources (remote and local).

#### 2. di
Contains the Dependency Injection module.
##### Injection.kt: Class for providing instances needed in the application.

#### 3. ui
Contains the application's display layer.
##### analysis: Folder for skin analysis feature.
##### detail: Folder to display article details or analysis results.
##### history: Folder to display analysis history.
##### home: Folder to display the main page.
##### settings: Folder for application settings.

### Main Features
#### Skin Analysis
Helps users find out their skin condition and suitable skincare ingredients.

#### Analysis History
Saves and displays user analysis result history.

#### Related Skincare Articles
Displays the latest information and articles related to skincare.

#### Settings
Provides options to set application preferences, such as themes (dark mode).

### Technologies Used

MVVM Architecture: For clear separation between application logic and user interface.

DataStore: To store user preferences securely.

Room Database: To store data locally.

Retrofit: For communication with API.
