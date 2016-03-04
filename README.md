# welcome-android-gradle
---------
Author: Daniel Passos (dpassos@redhat.com)   
Level: Intermediate   
Technologies: Java, Android, RHMAP   
Summary: A sampling of features offered by RHMAP and the FeedHenry SDK   
Community Project : [Feed Henry](http://feedhenry.org)   
Target Product: RHMAP   
Product Versions: RHMAP 3.8.0+   
Source: https://github.com/feedhenry-templates/welcome-android-gradle   
Prerequisites: fh-android-sdk : 3.0.+, Android Studio : 1.4.0 or newer, Android SDK : 22+ or newer   

## What is it?

This application demonstrates how to use several pieces of the FeedHenry SDK including setup, making cloud calls, storing data in the cloud, and (soon) push notifications.

If you do not have access to a RHMAP instance, you can sign up for a free instance at [https://openshift.feedhenry.com/](https://openshift.feedhenry.com/).

## How do I run it?

### RHMAP Studio

This source repository may be imported into any project by using the "Import Existing App" feature when you add a new client app to a project.  It may be imported into any project.

### Local Clone (ideal for Open Source Development)
If you wish to contribute to this template, the following information may be helpful; otherwise, RHMAP and its build facilities are the preferred solution.

###  Prerequisites
 * fh-android-sdk : 3.0.+
 * Android Studio : 1.4.0 or newer
 * Android SDK : 16+ or newer

## Build instructions
 * Edit [fhconfig.properties](app/src/main/assets/fhconfig.properties) to include the relevant information from RHMAP.
 * Attach running Android Device with API 16+ running
 * ./gradlew installDebug

