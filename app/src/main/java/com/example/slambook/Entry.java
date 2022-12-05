package com.example.slambook;

public class Entry {
    private int entryPicture;
    private String entryFullName;
    private String entryRemark;
    private String entryHobbies;
    private String entryGender;
    private String entryBirthday;

    public Entry(int entryPicture, String entryFullName, String entryRemark,String entryHobbies, String entryGender, String entryBirthday) {
        this.setEntryPicture(entryPicture);
        this.setEntryFullName(entryFullName);
        this.setEntryRemark(entryRemark);
        this.setEntryHobbies(entryHobbies);
        this.setEntryGender(entryGender);
        this.setEntryBirthday(entryBirthday);
    }


    public int getEntryPicture() {
        return entryPicture;
    }

    public void setEntryPicture(int entryPicture) {
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
