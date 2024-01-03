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
WHERE (username = ? and email = ?);


-- add new user to db

CREATE PROCEDURE socialmedia.addUser
	(username VARCHAR(25),	
    password VARCHAR(25),
    email VARCHAR(50),
    fullname VARCHAR(50)) OUTPUT
AS
BEGIN
	SET NOCOUNT ON 
    
   
    
		INSERT INTO userAccounts
		(username, password, email, fullname)
		values ("testUser",HASHBYTES('SHA2_512',"password"),"email@email.com","test_user")
        
END






