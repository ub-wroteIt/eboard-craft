package org.ubwroteit.follower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FollowerCollector {
    private final static ForkJoinPool pool = new ForkJoinPool();

    public Set<UUID> findAllFollowers(UUID contenderID, Set<UUID> firstLevelFollowers){
        return FollowerCollector.pool.invoke(new FollowerFinder(Pair.of(contenderID, firstLevelFollowers)));
    }

    private static class FollowerFinder extends RecursiveTask<Set<UUID>>{
        final Pair<UUID, Set<UUID>> contenderFollowerPair;

        @Autowired
        FollowerService followerService;

        private FollowerFinder(Pair<UUID, Set<UUID>> contenderFollowerPair) {
            this.contenderFollowerPair = contenderFollowerPair;
        }


        @Override
        protected Set compute() {
            HashSet<UUID> uuids = new HashSet<>();
            Set<UUID> children = contenderFollowerPair.getSecond();
            if(children.isEmpty()){
                uuids.add(contenderFollowerPair.getFirst());
            }else{
               if(!children.isEmpty()){
                   List<ForkJoinTask<Set<UUID>>> tasks = new ArrayList<>();
                   for (final UUID child : children) {
                       Set<UUID> subFollowers = followerService.getFollowersId(child);
                       if(subFollowers.isEmpty()){
                           uuids.add(child);
                       }else{
                           tasks.add(new FollowerFinder(Pair.of(child, subFollowers)));
                       }
                   }

                   for (ForkJoinTask<Set<UUID>> task : invokeAll(tasks)) {
                       uuids.addAll(task.join());
                   }
               }
            }
            return uuids;
        }
    }
}
