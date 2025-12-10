package com.channel2.mobile.ui.settings.models;

/* loaded from: classes2.dex */
public class ContactMessage {
    String email;
    String message;
    String name;

    public ContactMessage(String str, String str2, String str3) {
        this.message = str;
        this.name = str2;
        this.email = str3;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }
}
