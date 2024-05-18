USE socialmediatest;

-- test


show tables;

select * from useraccounts;

-- insert test user accounts
-- password is password1 and password2
INSERT INTO useraccounts (username,password,email,fullname)
VALUES
ROW ("testUsername1","CNrWX1fB/zo1klU+U1/iPA==:mSYiB/gV71idejmz21Cf+YQwKJtJIeOi/SPzsmvi1jc=","test1@email.com","test name1"), 
ROW ("testUsername2","Ttoe8VonkKAOsWrJDsBflA==:M07/Na6Tje0/oRP469hL05Yb+ioeuFCm0yIYZN8jmnQ=","test2@email.com","test name2");


delete from useraccounts where username = 'testUsername2';




