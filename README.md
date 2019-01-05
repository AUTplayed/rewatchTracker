# Rewatch Tracker

This is a quick program i put together to

1. learn how to build a PWA with plain js
2. get used to building projects with [spark](http://sparkjava.com) + [ldf](https://github.com/AUTplayed/ldf) with static rendering using [mustache](http://mustache.github.io/)
3. track my rewatch progress of some shows on a shared app for all my devices

the reason I use this to track my rewatch progress and not the site where I'm watching is that I don't want to change my "finished" status on that show on the site

## deploying

deploying is really easy, just execute the fatJar gradle task and then deploy the jar from build/libs/rewatchTracker-fat-1.0.jar

the application will create a store.json file in the working directory which retains the persisted shows (autosaves every hour)