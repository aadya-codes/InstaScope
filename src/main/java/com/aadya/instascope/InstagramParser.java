package com.aadya.instascope;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class InstagramParser {
    
    private final ObjectMapper mapper = new ObjectMapper();

    public AnalysisResult parse(File followersFile, File followingFile) throws Exception {
        
        Set<String> followers = new HashSet<>();
        Set<String> following = new HashSet<>();

        JsonNode followersData = mapper.readTree(followersFile);
        for (JsonNode person : followersData) {
            JsonNode data = person.get("string_list_data").get(0);
            followers.add(data.get("value").asText());
        }

        JsonNode followingData = mapper.readTree(followingFile);
        JsonNode followingList = followingData.get("relationships_following");
        for (JsonNode person : followingList) {
            following.add(person.get("title").asText());
        }

        Set<String> notFollowingBack = new HashSet(following);
        notFollowingBack.removeAll(followers);

        Set<String> iDontFollowBack = new HashSet(followers);
        iDontFollowBack.removeAll(following);

        Set<String> mutualFollowers = new HashSet(following);
        mutualFollowers.retainAll(followers);

        return new AnalysisResult(
            followers,
            following,
            notFollowingBack,
            iDontFollowBack,
            mutualFollowers
        );

    }

}
