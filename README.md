# InstaScope

A privacy-focused JavaFX desktop application that analyses your Instagram follower and following data.

## What it does

InstaScope lets you import your Instagram `followers_1.json` and `following.json` files and compares them to show:

* Followers and following counts
* People who don't follow you back
* People you don't follow back
* Mutual followers
* Username lists for each category

## Privacy

Your Instagram data is processed **locally on your device**.

InstaScope does not require your Instagram password, does not connect to your Instagram account, and does not upload your data to a server.

> **Never upload your personal Instagram JSON files to GitHub.**

## How to use

1. Download your Instagram information through Instagram's **Accounts Center**.
2. Select **Followers and following** and use **JSON** format.
3. Download the data to your device.
4. Open InstaScope.
5. Select `followers_1.json` and `following.json`.
6. Click **Analyse**.

## Built with

* Java
* JavaFX
* Maven
* Jackson

## Project structure

```text
src/
└── main/
    ├── java/
    │   └── com/aadya/instascope/
    │       ├── Main.java
    │       ├── InstagramParser.java
    │       └── AnalysisResult.java
    │
    └── resources/
        └── style.css
```

## Status

🚧 **In development**

The core Instagram data parsing and relationship analysis are currently implemented. The user interface is being refined, with additional features planned.

## Future ideas

* Search usernames
* Filter relationship categories
* Better error handling for Instagram export variations
* Additional privacy-focused analysis
