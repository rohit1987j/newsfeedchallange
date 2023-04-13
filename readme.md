# My Assumption

* One thing which confused me was what you meant by "The frequency of news items being emitted by the feed".
I have assumed frequency as number of newsfeed items sent from newfeedgenerator service per second.


# How to Build both services
To run the services first we need to build both services.
Following is the command to build the services.
we are in current folder
* ./mvnw clean install


# How to run both services
To run the services please run following command
* java -jar ./newsanalyser/target/newsanalyser-1.0-SNAPSHOT.jar
* java -jar ./newsfeedgenerator/target/newsfeedgenerator-1.0-SNAPSHOT.jar <frequency>


# Unit testing
* I have written unit tests for NewsAnaylserService and NewsFeedData.
More test have to be written in reallife.


# Common library missing
* in real world I would have created common library having all the data objects - HeadlineWord, NewsFeed, Priority.
* I didnt want to spend more time so just copied them in both services.
* Mentioning here just to clarify

# Total time spend = 8-10 hours


