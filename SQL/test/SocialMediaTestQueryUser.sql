USE socialmediatest;

-- test


show tables;

select * from useraccounts;

-- insert test user accounts
-- password is password1 and password2
INSERT INTO useraccounts (username,password,email,fullname)
VALUES
ROW ("testUsername1","CNrWX1fB/zo1klU+U1/iPA==:mSYiB/gV71idejmz21Cf+YQwKJtJIeOi/SPzsmvi1jc=","test1@email.com","test name1"), 
ROW ("testUsername2","qYfpzqf2NkXVDOSdBGyRTQ==:g7rsUGTMBs6tclMeIO25+PPOSgEr/8lY6VS3pEBt32k=","test2@email.com","test name2");




