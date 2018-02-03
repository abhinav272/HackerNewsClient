package com.abhinav.hackernewsclient.adapter_test;

import android.test.AndroidTestCase;

import com.abhinav.hackernewsclient.data.network.adapter.EpochDateTypeAdapter;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.CommentsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by appinventiv on 3/2/18.
 */

public class CommentAdapterTest extends AndroidTestCase {

    private String comment1String = "{\n" +
            "    \"by\": \"oasisbob\",\n" +
            "    \"id\": 16213346,\n" +
            "    \"kids\": [\n" +
            "        16213499,\n" +
            "        16214064,\n" +
            "        16214624,\n" +
            "        16213853\n" +
            "    ],\n" +
            "    \"parent\": 16212276,\n" +
            "    \"text\": \"Since the danger seems to be receding -- I hope NWS &amp; related agencies can use this as a good drill to fix their web properties.<p>Tsunami.gov couldn&#x27;t handle the load, and many other official links (like mobile.weather.gov) just dump you into a government shutdown notice. Cell.weather.gov isn&#x27;t even resolving.<p>I&#x27;m sure someone thought the critical products would still be easily available even with a government shutdown, it sure didn&#x27;t work out that way.\",\n" +
            "    \"time\": 1516713917,\n" +
            "    \"type\": \"comment\"\n" +
            "}";

    private String comment2String = "{\n" +
            "    \"by\": \"robtaylor\",\n" +
            "    \"id\": 16212818,\n" +
            "    \"kids\": [\n" +
            "        16213028,\n" +
            "        16213027,\n" +
            "        16213793,\n" +
            "        16213880\n" +
            "    ],\n" +
            "    \"parent\": 16212276,\n" +
            "    \"text\": \"<a href=\\\"https:&#x2F;&#x2F;twitter.com&#x2F;SF_emergency&#x2F;status&#x2F;955764708442714113\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;twitter.com&#x2F;SF_emergency&#x2F;status&#x2F;955764708442714113</a><p>San Francisco Department of Emergency Management has tweeted that people within three blocks of the coast or five blocks of the San Francisco Bay should be prepared to evacuate.<p>Tsunami Watch for #SF. If you are w&#x2F;in SF &amp; 3 blocks of the Pacific Coast or w&#x2F;in 5 blocks of SF Bay, PREPARE TO EVACUATE SO YOU ARE READY IF EVACUATION IS NEEDED. Check on neighbors who may need help. Visit <a href=\\\"http:&#x2F;&#x2F;sf72.org&#x2F;hazard&#x2F;tsunamis\\\" rel=\\\"nofollow\\\">http:&#x2F;&#x2F;sf72.org&#x2F;hazard&#x2F;tsunamis</a>  to see if you are in the evacuation zone.\",\n" +
            "    \"time\": 1516708318,\n" +
            "    \"type\": \"comment\"\n" +
            "}";

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

    private ArrayList<Comment> comments;
    private Comment comment1, comment2, comment1Reply, comment2Reply;
    private CommentsAdapter adapter;

    public CommentAdapterTest() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        comment1 = prepareCommentObject(comment1String);
        comment2 = prepareCommentObject(comment2String);
        comment1Reply = prepareCommentObject(comment1ReplyString);
        comment2Reply = prepareCommentObject(comment2ReplyString);

        comments = new ArrayList<>();
        comments.add(comment1);

        adapter = new CommentsAdapter(comments);
    }

    private Comment prepareCommentObject(String commentJson) {
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
        assertEquals(comment1, adapter.getItem(0));
    }

    @Test
    public void testAddCommentItem() throws Exception {
        adapter.addCommentItem(comment2);
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testUpdateCommentReply() throws Exception {
        assertEquals(null, adapter.getItem(0).getKidComments());
        comment1.setKidComments(comment1Reply);
        adapter.updateCommentReplies(comment1);
        assertNotNull(adapter.getItem(0));
        assertNotNull(adapter.getItem(0).getKidComments());
        assertEquals(1, adapter.getItem(0).getKidComments().size());
        assertEquals(comment1Reply, adapter.getItem(0).getKidComments().get(0));
    }

    @Test
    public void testAddPreLoadedComments() throws Exception {
        comments.add(comment2);
        adapter.addPreLoadedComments(new ArrayList<>(comments));
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testRemoveAllComments() throws Exception {
        adapter.removeAllComments();
        assertEquals(0, adapter.getItemCount());
    }
}
