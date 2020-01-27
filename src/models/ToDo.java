package models;

import java.time.LocalDate;

public class ToDo {
    private int todoId;
    private int userId;
    private String title;
    private String text;
    private LocalDate aimDate;
    private boolean status;

    public ToDo(int todoId, int userId, String title, String text, LocalDate aimDate, boolean status) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.aimDate = aimDate;
        this.status = status;
    }

    public ToDo() {
    }

    public ToDo(int userId, String title, String text, LocalDate aimDate, boolean status) {
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.aimDate = aimDate;
        this.status = status;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getAimDate() {
        return aimDate;
    }

    public void setAimDate(LocalDate aimDate) {
        this.aimDate = aimDate;
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
                "todoId=" + todoId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", aimDate=" + aimDate +
                ", status=" + status +
                '}';
    }
}
