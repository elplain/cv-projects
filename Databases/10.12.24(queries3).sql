DECLARE @start_date DATE = '2020-06-22';
DECLARE @end_date DATE = '2020-06-22';
SELECT emp.empid, emp.empname, SUM(DATEDIFF(SECOND, '00:00:00', work.timespent)) / 60.0 AS total_minutes_spent
FROM work, emp
WHERE work.sid IN (SELECT service.sid FROM service WHERE service.dropoff_date BETWEEN @start_date AND @end_date) AND work.empid IN (SELECT empid FROM emp)
GROUP BY emp.empid, emp.empname
ORDER BY total_minutes_spent DESC;
