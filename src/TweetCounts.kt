import org.junit.Test

class TweetCounts {

    private val tweetMap = HashMap<String, ArrayList<Int>>()

    fun recordTweet(tweetName: String, time: Int) {
        if (tweetMap.containsKey(tweetName)) {
            tweetMap[tweetName]?.add(time)
        } else {
            tweetMap[tweetName] = arrayListOf(time)
        }
    }

    fun getTweetCountsPerFrequency(freq: String, tweetName: String, startTime: Int, endTime: Int): List<Int> {
        return arrayListOf()
    }


    @Test
    fun runTest() {
        val tweetCounts = TweetCounts()
        tweetCounts.recordTweet("tweet3", 0)
        tweetCounts.recordTweet("tweet3", 60)
        tweetCounts.recordTweet("tweet3", 10) // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.

        tweetCounts.getTweetCountsPerFrequency(
            "minute",
            "tweet3",
            0,
            59
        ) // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.

        tweetCounts.getTweetCountsPerFrequency(
            "minute",
            "tweet3",
            0,
            60
        ) // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.

        tweetCounts.recordTweet(
            "tweet3",
            120
        ) // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.

        tweetCounts.getTweetCountsPerFrequency(
            "hour",
            "tweet3",
            0,
            210
        ) // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.

    }
}