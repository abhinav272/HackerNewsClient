package com.abhinav.hackernewsclient.adapter_test;

import android.test.AndroidTestCase;

import com.abhinav.hackernewsclient.data.network.adapter.EpochDateTypeAdapter;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.adapter.TopStoriesAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by appinventiv on 3/2/18.
 */

public class TopStoriesAdapterTest extends AndroidTestCase {

    private String story1Json = "{\n" +
            "    \"by\": \"nergal\",\n" +
            "    \"descendants\": 90,\n" +
            "    \"id\": 16212276,\n" +
            "    \"kids\": [\n" +
            "        16212461,\n" +
            "        16214181,\n" +
            "        16213346,\n" +
            "        16212818,\n" +
            "        16212602,\n" +
            "        16212337,\n" +
            "        16212388,\n" +
            "        16212691,\n" +
            "        16212303,\n" +
            "        16219944,\n" +
            "        16215308,\n" +
            "        16212766,\n" +
            "        16212440,\n" +
            "        16212756,\n" +
            "        16217406,\n" +
            "        16216074,\n" +
            "        16212456,\n" +
            "        16215053,\n" +
            "        16212319\n" +
            "    ],\n" +
            "    \"score\": 233,\n" +
            "    \"time\": 1516701694,\n" +
            "    \"title\": \"Tsunami warning ends for B.C. after large earthquake strikes off Alaska\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"url\": \"http://www.cbc.ca/news/world/alaska-earthquake-tsunami-1.4499558\"\n" +
            "}";

    private String story2Json = "{\n" +
            "    \"by\": \"tonyg\",\n" +
            "    \"descendants\": 77,\n" +
            "    \"id\": 16281346,\n" +
            "    \"kids\": [\n" +
            "        16293256,\n" +
            "        16293254,\n" +
            "        16292903,\n" +
            "        16294034,\n" +
            "        16296512,\n" +
            "        16296532,\n" +
            "        16296451,\n" +
            "        16296482,\n" +
            "        16293969,\n" +
            "        16292815,\n" +
            "        16293319,\n" +
            "        16294245,\n" +
            "        16292939\n" +
            "    ],\n" +
            "    \"score\": 174,\n" +
            "    \"time\": 1517487866,\n" +
            "    \"title\": \"Lying to TCP makes it a best-effort streaming protocol\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"url\": \"https://eighty-twenty.org/2018/02/01/lying-to-tcp\"\n" +
            "}";

    private String story3Json = "{\n" +
            "    \"by\": \"mpweiher\",\n" +
            "    \"descendants\": 6,\n" +
            "    \"id\": 16281270,\n" +
            "    \"kids\": [\n" +
            "        16296177,\n" +
            "        16295842,\n" +
            "        16295813,\n" +
            "        16295819\n" +
            "    ],\n" +
            "    \"score\": 39,\n" +
            "    \"time\": 1517487061,\n" +
            "    \"title\": \"CloudKit: Structured Storage for Mobile Applications [pdf]\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"url\": \"http://www.vldb.org/pvldb/vol11/p540-shraer.pdf\"\n" +
            "}";

    private TopStoriesAdapter adapter;
    private ArrayList<Story> stories;
    private Story story1;
    private Story story2;
    private Story story3;


    public TopStoriesAdapterTest() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        stories = new ArrayList<>();
        story1 = prepareStoryObj(story1Json);
        story2 = prepareStoryObj(story2Json);
        story3 = prepareStoryObj(story3Json);

        stories.add(story1);
        stories.add(story2);

        adapter = new TopStoriesAdapter(stories, null);
    }

    private Story prepareStoryObj(String storyJson) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                EpochDateTypeAdapter.getEpochDateTypeAdapter()).create();
        return gson.fromJson(storyJson, Story.class);
    }

    @Test
    public void testGetItem() throws Exception {
        assertEquals(story1, adapter.getItem(0));
        assertEquals(story2, adapter.getItem(1));
    }

    @Test
    public void testGetItemCount() throws Exception {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testAddStoryItem() throws Exception {
        adapter.addStoryItem(story3);
        assertEquals(3, adapter.getItemCount());
        assertEquals(story3, adapter.getItem(2));
    }

    @Test
    public void testPreLoadItems() throws Exception {
        adapter.addPreLoadedStories(new ArrayList<>(stories));
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testRemoveAllStories() throws Exception {
        adapter.removeAllStories();
        assertEquals(0, adapter.getItemCount());
    }
}
