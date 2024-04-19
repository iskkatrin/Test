package mobi.sevenwinds.models;

import java.time.LocalDateTime;

@Component
public class BudgetStatsDTO {
    private Budget budget;
    private String authorFullName;
    private LocalDateTime authorCreatedAt;

    public BudgetStatsDTO(Budget budget, String authorFullName, LocalDateTime authorCreatedAt) {
        this.budget = budget;
        this.authorFullName = authorFullName;
        this.authorCreatedAt = authorCreatedAt;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public LocalDateTime getAuthorCreatedAt() {
        return authorCreatedAt;
    }

    public void setAuthorCreatedAt(LocalDateTime authorCreatedAt) {
        this.authorCreatedAt = authorCreatedAt;
    }
}

