package me.catsbi.study.step4.domain;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueParticipants {
    public static final String NO_NAME = "";
    private final Map<Long, Participants> info;

    public IssueParticipants() {
        this(new HashMap<>());
    }

    public IssueParticipants(Map<Long, Participants> info) {
        this.info = info;
    }

    /*public Participants participationRateByParticipants(int size) {

    }*/

    public static IssueParticipants from(List<GHIssue> issues) {
        Map<Long, Participants> collect = issues.stream()
                .map(issue -> {
                    try {
                        return createParticipants(issue);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return Participants.EMPTY_OBJECT;
                    }
                }).filter(Participants::isEmpty)
                .collect(Collectors.toMap(Participants::getIssueNo, p -> p));

        return new IssueParticipants(collect);
    }

    private static Participants createParticipants(GHIssue issue) throws IOException {
        List<GHIssueComment> comments = issue.getComments();
        return new Participants(issue.getId(), comments.stream()
                .map(comment -> {
                    try {
                        return comment.getUser().getName();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return NO_NAME;
                    }
                }).filter(IssueParticipants::isValidName)
                .collect(Collectors.toSet()));
    }

    private static boolean isValidName(String name) {
        return !name.equals(NO_NAME);
    }
}
