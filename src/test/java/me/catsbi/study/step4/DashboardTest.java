package me.catsbi.study.step4;

import me.catsbi.study.step4.domain.Dashboard;
import me.catsbi.study.step4.domain.IssueParticipants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest {
    private Dashboard dashboard;

    @BeforeEach
    void setup() throws IOException {
        dashboard = new Dashboard();
    }


    @DisplayName("이슈 사이즈 테스트")
    @Test
    void getSize() throws IOException {
        int size= dashboard.getIssueListSize();
        assertThat(size).isEqualTo(1);
    }

    @DisplayName("이슈 별 참가자 조회 테스트")
    @Test
    void getParticipants() throws IOException {
        IssueParticipants participants = dashboard.getParticipants();

    }

}
