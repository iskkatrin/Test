package mobi.sevenwinds.models;

import java.time.LocalDateTime;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private LocalDateTime createdAt;

    public Author(String fullName, LocalDateTime createdAt) {
        this.fullName = fullName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

