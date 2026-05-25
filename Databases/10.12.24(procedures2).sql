CREATE PROCEDURE UpdateEmpoyeeAvailiability
@empid VARCHAR(20),
@avaliability_stat CHAR(100)
AS
BEGIN
UPDATE emp
SET avaliability_stat = @avaliability_stat
WHERE empid = @empid;
END;

EXEC UpdateEmpoyeeAvailiability 'E9274', 'unavailable';
drop procedure UpdateEmpoyeeAvailiability;