Use socialmedia;
describe userAccounts;

-- get user account by username
SELECT *
FROM userAccounts
WHERE username = ?;


-- get user account from email
SELECT *
FROM userAccounts
WHERE email = ?;


-- get user account from email and user name  
-- for forget password?
SELECT *
FROM userAccounts
WHERE (username = ? and email = ?);


-- get user account from username and password
-- log in
SELECT *
FROM userAccounts
WHERE (username = ? and password = ?);



