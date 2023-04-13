package service;

import data.HeadlineWord;
import data.NewsFeed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class NewsFeedGenerator {
    public NewsFeed generate() {
        return new NewsFeed(generateHeadline(), PriorityGenerator.getPriority());
    }

    private List<HeadlineWord> generateHeadline() {

        int minimumWordsInHeadline = 3;
        int maximumWordsInHeadline = 5;

        int numberOfWords = ThreadLocalRandom.current().nextInt(minimumWordsInHeadline, maximumWordsInHeadline + 1);

        List<HeadlineWord> headline = new ArrayList<>(numberOfWords);

        while (numberOfWords > 0) {

            int index = ThreadLocalRandom.current().nextInt(HeadlineWord.getMinIndex(), HeadlineWord.getMaxIndex() + 1);

            HeadlineWord headlineWord = Arrays.stream(HeadlineWord.values())
                                                .filter(word-> word.getIndex() == index)
                                                .findFirst()
                                                .get();

            headline.add(headlineWord);
            numberOfWords--;
        }
        return headline;
    }
}
