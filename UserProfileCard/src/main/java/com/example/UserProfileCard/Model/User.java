package com.example.UserProfileCard.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String role;
    private boolean accountLocked;

    public String getUsername() {   return username;    }
    public void setUsername(String Username) {  this.username = Username;    }

    public String getRole() {   return role;    }
    public void setRole(String Role) {  this.role = Role;    }

    public boolean isAccountLocked() {  return accountLocked;    }
    public void setAccountLocked(boolean AccountLocked) {   this.accountLocked = AccountLocked;    }
}