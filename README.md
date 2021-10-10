# Bookmark-History
Due to a certain bug with Chrome, there exists an error with bookmarks where Chrome will only sync an old version of the bookmarks file, i.e., you effectively can't sync your new bookmarks file if an older one existed, even if you tried to import a new bookmark folder.

When you have an enormous amount of bookmarks and syncing doesn't work, you might want to have a local backup of your precious bookmarks!

## Installation

Download the zip file and extract into your project. Call BookmarkWatcher.watcher() which runs a for-loop, checking your Google Bookmark file (Checks the default location "\AppData\Local\Google\Chrome\User Data\Default\Bookmarks" [Note that the file doesn't have an extension]) with three backup bookmarks (Creates them if there are currently none on your desktop in a folder called Bookmark Backups). 
