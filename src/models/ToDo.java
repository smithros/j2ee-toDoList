package models;

import java.time.LocalDate;

public class ToDo {
    private int id;
    private String title;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;

    protected ToDo() {

    }

    public ToDo(int id, String title, String username, String description, LocalDate targetDate, boolean isDone) {
        super();
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    public ToDo(String title, String username, String description, LocalDate targetDate, boolean isDone) {
        super();
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", status=" + status +
                '}';
    }
}
