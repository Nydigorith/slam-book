package com.example.slambook;

public class Entry {
    private int entryPicture;
    private String entryFullName;
    private String entryRemark;

    public Entry(int entryPicture, String entryFullName, String entryRemark) {
        this.setEntryPicture(entryPicture);
        this.setEntryFullName(entryFullName);
        this.setEntryRemark(entryRemark);
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
}
