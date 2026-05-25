CREATE PROCEDURE UpdateCustomerInfo
@custid VARCHAR(20),
@custname CHAR(100),
@custemail CHAR(100),
@custphone VARCHAR(20)
AS
BEGIN
UPDATE customer
SET custname = @custname,
custemail = @custemail,
custphone = @custphone
WHERE custid = @custid;
END;

EXEC UpdateCustomerInfo 'R180-61', 'New Name2', 'NewEmail@gmail.com', '123456789';