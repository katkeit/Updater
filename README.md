# Updater
![License](https://img.shields.io/github/license/katkeit/Updater)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/99d25ea9c8c04b42b09fa6cd7b83e531)](https://www.codacy.com/gh/katkeit/Updater/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=katkeit/Updater&amp;utm_campaign=Badge_Grade)
[![Java CI with Maven](https://github.com/katkeit/Updater/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/katkeit/SUN-Calculator/actions/workflows/maven.yml)
![Issues](https://img.shields.io/github/issues/katkeit/Updater)
![Size](https://img.shields.io/github/repo-size/katkeit/Updater)

> This Java application is designed to be integrated into a larger project to help update the main application.
> English, Español, and Português are currently supported by the application.

## ❈ Getting Started
1. Clone the project.
2. Change your language preferences in config/preferences.txt (EN, ES, PT).
3. Change your settings in config/settings.txt
   - Add icon under the GENERAL section.
   - The main application needs to update MAIN_APP_PATH and MAIN_APP_NAME for the updater to restart the application.
   - Add the name of the UPDATE_ZIP
   - Can edit the name of the VERSION file.
4. Change the version.txt file.
   - Update the VERSION under MAIN APP section.
   - Update the DOWNLOAD link under MAIN APP section.
5. Upload the version.txt.
   - The main application will download version.txt in the Updater's download folder. If application needs to update, then launch updater.jar.
     - The Updater will delete the file after updating the main application.
6. Compile the project.

## ❈ Features
- Supports three (3) languages (English, Español, and Português).
- Downloads and extracts zip files.
- Restarts the main application (.jar files) after updating.