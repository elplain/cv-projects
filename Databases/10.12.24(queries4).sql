DECLARE @start_date DATE = '2021-06-21' ;
DECLARE @end_date DATE = '2021-06-22'; 

SELECT c.custname, c.custemail, car.make, car.regnum, car.nextsdate
FROM car
JOIN customer c ON car.custid = c.custid
WHERE car.nextsdate BETWEEN @start_date AND @end_date;