CREATE TRIGGER update_service
ON emp
AFTER UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted WHERE avaliability_stat = 'unavailable')
    BEGIN
        UPDATE service
        SET empid = NULL
        WHERE empid IN (SELECT empid FROM inserted WHERE avaliability_stat = 'unavailable');
    END
END;
