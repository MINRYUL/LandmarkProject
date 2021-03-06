package com.example.example02.Info;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class FollowInfo {
    public int followerCount = 0;
    public int followingCount = 0;
    public Map<String, Boolean> follower = new HashMap<>();
    public Map<String, Boolean> following = new HashMap<>();

    public FollowInfo(){
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("followerCount", followerCount);
        result.put("followingCount", followingCount);
        return result;
    }
}
