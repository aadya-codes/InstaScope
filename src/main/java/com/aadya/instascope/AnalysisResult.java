package com.aadya.instascope;

import java.util.Set;

public class AnalysisResult {

    private final Set<String> followers;
    private final Set<String> following;
    private final Set<String> notFollowingBack;
    private final Set<String> iDontFollowBack;
    private final Set<String> mutualFollowers;

    public AnalysisResult(Set<String> followers, Set<String> following, Set<String> notFollowingBack, Set<String> iDontFollowBack, Set<String> mutualFollowers) {
        this.followers = followers;
        this.following = following;
        this.notFollowingBack = notFollowingBack;
        this.iDontFollowBack = iDontFollowBack;
        this.mutualFollowers = mutualFollowers;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public Set<String> getFollowing() {
        return following;
    }

    public Set<String> getNotFollowingBack() {
        return notFollowingBack;
    }

    public Set<String> getiDontFollowBack() {
        return iDontFollowBack;
    }

    public Set<String> getMutualFollowers() {
        return mutualFollowers;
    }
}
