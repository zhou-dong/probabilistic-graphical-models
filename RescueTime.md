# Use HMM to analyze the RescueTime data

1. Collect the data with RescueTime API
    - Daily Summary Feed API
    - https://www.rescuetime.com/anapi/setup/documentation#daily-summary-feed-reference

2. Clean Data
    - We don't need to clean the data, because we fetch data from RecureTime API which they already helped us clean the data 

3. Find the Features 
    - We don't have to many days data, in case of overfitting, we did not choose so many features
    - We choose 4 main categores and also we think both number and percent are important so:
        1. total_hours
        2. Product hour
            + all_productive_hours
            + all_productive_percentage
        3. Distracting hour
            + all_distracting_hours
            + all_distracting_percentage
        4. Neural hour
            + neutral_hours
            + neutral_percentage

4. Because we have data, so we wanna find relations between them, At first, we thought, the online doing things well affect sleep, or eating thing, also we thind different day of the week will have different attension on different area, like study or watch vedios. It just have some many ideas, but hard to choose the best one.
    - And then we thought, let the data told us, then we decidec use K-means to cluster data for us, then research the data.

5. Use K-means to seperate the all date into 3 different categories
    - At first we just wanna analyze our data, but we did not know what is real relations inside our data, so we use kmeans to help us to find the relations.
    - we use the example and write K-means by ourselives
    - We been search k-means online, but no perfect one for us, so we write it by ourselves
    - https://github.com/apache/flink/blob/master/flink-examples/flink-java-examples/src/main/java/org/apache/flink/examples/java/clustering/KMeans.java
    1. productive day
    2. entertainment day
    3. neutral day 

6. Based on the result of kmeans we found, our data could divided into to parts
    1. activity day
        - use laptop time more than 2 hour
    2. unactivity day
        - use laptop time less than 2 hour
    - Activity day
        + more percent of time to study
    - Unactivity day
        + more percent of time to distract of communicate

7. We divided whole data into 2 parts (also because we don't have too much data, we only split our data into 2 part):
    - study or working. like "coursera","github","ecipse","atom"
    - entertainment. like "wechat","weibo","youtube" and other
