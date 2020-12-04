package me.catsbi.study.step4.domain;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

public class Dashboard {
    private final String token = "";
    private final GitHub gitHub;
    private final GHRepository repo;
    private final IssueParticipants issueParticipants;


    public Dashboard() throws IOException {
        this.gitHub = init();
        this.repo = getRepo();
        this.issueParticipants = new IssueParticipants();
    }

    private GHRepository getRepo() throws IOException {
        return gitHub.getRepository("catsbi/java-live-study");
    }

    private GitHub init() throws IOException {
         return new GitHubBuilder().withOAuthToken(token).build();
    }

    private Comments getComments() throws IOException {
        List<GHIssue> issues = getIssues();
        for (GHIssue issue : issues) {
            List<GHIssueComment> comments = issue.getComments();
            comments.forEach(comment->{
                try {
                    GHUser user = comment.getUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return new Comments();
    }

    public int getIssueListSize() throws IOException {
        List<GHIssue> issues = getIssues();
        return issues.size();
    }

    private List<GHIssue> getIssues() throws IOException {
        return repo.listIssues(GHIssueState.ALL).toList();
    }

    public IssueParticipants getParticipants() throws IOException {
        return IssueParticipants.from(getIssues());

    }
}
