# Last FM Top 5
## App Description:
The app simply displays the top 5 albums, artists and tracks for a [last.fm](https://www.last.fm/) user, you can type any user name and check his or her top fives.
## Technical Description:
* The app is structured in MVP.
* My most valuable reference for implementing MVP in android is [Antonio Leiva's Post](https://github.com/antoniolg/androidmvp)
* The app uses last fm's [APIs](http://www.last.fm/api/intro) to get the user top lists.
* To be able to use their APIs you need first to [create](https://www.last.fm/api/account/create) an API key.
* You can use methods to get top artists, search for artits/tracks etc..
* To not only display top fives you can extend the limit for each request as much as you want but with max 50 records per call by changing the value of TOP_ITEMS_LIMIT to 50 in Constants.Java file.
* I have used Dagger2 to inject dependencies in userslisting module. 
* I have added an android unit test using espresso to test that the listing data is displayed correctly.
* I have added a unit test for time duration converter.
* 3rd party libraries used:RxJava/RxAndroid 2, Dagger2, Butterknife and Retrofit2.
* you can download an apk file for the app [here](https://www.dropbox.com/s/f9go7j9w02fcpai/Top5.apk?dl=0) or you can see an app run on [youtube](https://www.youtube.com/watch?v=g_takE--15Q).

![Alt](https://media.giphy.com/media/zR7Kx2hAXazCw/giphy.gif) ![Alt](https://media.giphy.com/media/lLfs1tSDSbQ08/giphy.gif)

