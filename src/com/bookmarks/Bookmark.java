package com.bookmarks;

public enum Bookmark
{

    NEW(System.getProperty("user.home") + "\\Desktop\\Bookmark Backups\\Bookmark - New"),
    OLD(System.getProperty("user.home") + "\\Desktop\\Bookmark Backups\\Bookmark - Old"),
    OLDEST(System.getProperty("user.home") + "\\Desktop\\Bookmark Backups\\Bookmark - Oldest"),
    BOOKMARKFOLDER(System.getProperty("user.home") + "\\Desktop\\Bookmark Backups"),
    GOOGLE(System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Bookmarks");

    private final String bookmarkLocation;

    Bookmark(String bookmarkLocation)
    {
        this.bookmarkLocation = bookmarkLocation;
    }

    public String getLocation()
    {
        return bookmarkLocation;
    }
}
