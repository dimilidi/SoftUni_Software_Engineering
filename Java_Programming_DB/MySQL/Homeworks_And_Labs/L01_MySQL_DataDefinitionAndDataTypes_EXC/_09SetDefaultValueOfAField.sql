ALTER TABLE minions.users
    MODIFY last_login_time DATETIME DEFAULT NOW();