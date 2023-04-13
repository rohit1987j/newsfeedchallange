package service;

import data.Result;

public class ResultPrintService {
    public void print(Result result) {

        System.out.println("<<<<<<< totalcount >>>>>>>" +  result.getCount());
        System.out.println("<<<<<<< top 3 headlines in last 10 seconds are: >>>>>>>");
        result.getNewsFeedList().stream().forEach(headline-> System.out.println(headline.getHeadlineAsString()));

    }
}
