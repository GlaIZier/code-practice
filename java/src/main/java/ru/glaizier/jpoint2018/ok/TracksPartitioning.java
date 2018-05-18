package ru.glaizier.jpoint2018.ok;

/**
 * @author mkhokhlushin
 */

// What's wrong with the code for partitioning tracks to shards
public class TracksPartitioning {

    private static class Track{}

    // returns from 0 inclusively to shardsTotal exclusively
    public int partition(Track track, int shardsTotal) {
        assert (track != null);
        return track.hashCode() % shardsTotal; // problem is that hashCode can return negative number, so we can use
        // Math.abs or override hashCode properly not to return negatives
    }
}
