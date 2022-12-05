package com.example.slambook;

import android.graphics.Bitmap;

public class Entry {
    private Bitmap entryPicture;
    private String entryFullName;
    private String entryRemark;
    private String entryHobbies;
    private String entryGender;
    private String entryBirthday;

    public Entry(Bitmap entryPicture, String entryFullName, String entryRemark,String entryHobbies, String entryGender, String entryBirthday) {
        this.setEntryPicture(entryPicture);
        this.setEntryFullName(entryFullName);
        this.setEntryRemark(entryRemark);
        this.setEntryHobbies(entryHobbies);
        this.setEntryGender(entryGender);
        this.setEntryBirthday(entryBirthday);
    }

    public Bitmap getEntryPicture() {
        return entryPicture;
    }

    public void setEntryPicture(Bitmap entryPicture) {
        this.entryPicture = entryPicture;
    }

    public String getEntryFullName() {
        return entryFullName;
    }

    public void setEntryFullName(String entryFullName) {
        this.entryFullName = entryFullName;
    }

    public String getEntryRemark() {
        return entryRemark;
    }

    public void setEntryRemark(String entryRemark) {
        this.entryRemark = entryRemark;
    }

    public String getEntryHobbies() {
        return entryHobbies;
    }

    public void setEntryHobbies(String entryHobbies) {
        this.entryHobbies = entryHobbies;
    }

    public String getEntryGender() {
        return entryGender;
    }

    public void setEntryGender(String entryGender) {
        this.entryGender = entryGender;
    }

    public String getEntryBirthday() {
        return entryBirthday;
    }

    public void setEntryBirthday(String entryBirthday) {
        this.entryBirthday = entryBirthday;
    }
}
