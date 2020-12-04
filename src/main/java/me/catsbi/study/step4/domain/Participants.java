package me.catsbi.study.step4.domain;

import java.util.HashSet;
import java.util.Set;

public class Participants {
    public static final Participants EMPTY_OBJECT = new Participants(-1, new HashSet<>());
    private final long issueNo;
    private final Set<String> participants;

    public Participants() {
        this(0, new HashSet<>());
    }

    public Participants(long issueNo, Set<String> participants) {
        this.issueNo = issueNo;
        this.participants = participants;
    }

    public void add(String name) {
        participants.add(name);
    }

    public int size() {
        return participants.size();
    }

    public long getIssueNo() {
        return issueNo;
    }

    public static boolean isEmpty(Participants participants) {
        return participants == EMPTY_OBJECT;
    }


}
