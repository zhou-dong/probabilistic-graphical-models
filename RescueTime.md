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

4. Use K-means to seperate the all date into 3 different categories
    - because it is hard for us to find decide by ourself, so we use K-means to help us
    1. productive day
    2. entertainment day
    3. neutral day 
