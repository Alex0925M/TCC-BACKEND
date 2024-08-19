package br.com.project.SB.NameProject.model.user;

public enum UserRole {

    ADMIN("admin"),
    USER("user"),
    INTERN("intern");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
