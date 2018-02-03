package com.abhinav.hackernewsclient.adapter_test;

import android.test.AndroidTestCase;

import com.abhinav.hackernewsclient.data.network.adapter.EpochDateTypeAdapter;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.CommentRepliesAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by appinventiv on 3/2/18.
 */

public class CommentRepliesAdapterTest extends AndroidTestCase {

    private String comment1ReplyString = "{\n" +
            "    \"by\": \"pc86\",\n" +
            "    \"id\": 16213499,\n" +
            "    \"parent\": 16213346,\n" +
            "    \"text\": \"Most of the labor contracts (not to mention ADA litigation) for the government require a certain level of service if the property is in use. With the government shut down that&#x27;s not the case. It&#x27;s the same reason for the infamous &quot;this website is only available from ~9am to ~5pm&quot; nonsense you can find on certain Social Security&#x2F;IRS pages.\",\n" +
            "    \"time\": 1516715361,\n" +
            "    \"type\": \"comment\"\n" +
            "}";

    private String comment2ReplyString = "{\n" +
            "    \"by\": \"nanch\",\n" +
            "    \"id\": 16213028,\n" +
            "    \"parent\": 16212818,\n" +
            "    \"text\": \"Tsunami watch has been cancelled for Washington, Oregon, and California.<p><a href=\\\"https:&#x2F;&#x2F;twitter.com&#x2F;NWSEureka&#x2F;status&#x2F;955777068377296896\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;twitter.com&#x2F;NWSEureka&#x2F;status&#x2F;955777068377296896</a>\",\n" +
            "    \"time\": 1516710472,\n" +
            "    \"type\": \"comment\"\n" +
            "}";

    private ArrayList<Comment> kidComments;
    private CommentRepliesAdapter adapter;
    private Comment kidComment1, kidComment2;

    public CommentRepliesAdapterTest() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        kidComment1 = prepareCommentObj(comment1ReplyString);
        kidComment2 = prepareCommentObj(comment2ReplyString);

        kidComments = new ArrayList<>();
        kidComments.add(kidComment1);

        adapter = new CommentRepliesAdapter(kidComments);
    }

    private Comment prepareCommentObj(String commentJson) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                EpochDateTypeAdapter.getEpochDateTypeAdapter()).create();
        return gson.fromJson(commentJson, Comment.class);
    }

    @Test
    public void testGetItemCount() throws Exception {
        assertEquals(1, adapter.getItemCount());
    }

    @Test
    public void testGetItem() throws Exception {
        assertEquals(kidComment1, adapter.getItem(0));
    }

    @Test
    public void testAddCommentItem() throws Exception {
        kidComments.add(kidComment2);
        adapter.updateDataSet(new ArrayList<>(kidComments));
        assertEquals(2, adapter.getItemCount());
        assertEquals(kidComment2, adapter.getItem(1));
        assertEquals(kidComment1, adapter.getItem(0));
    }
}
