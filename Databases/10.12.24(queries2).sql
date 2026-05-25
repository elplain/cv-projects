SELECT e.empname AS mechanic_name, e.grade AS mechanic_grade, COUNT(w.[sid]) AS number_of_jobs
FROM work w
JOIN emp e ON w.empid = e.empid
JOIN [service] s1 ON w.[sid] = s1.[sid]
JOIN [service] s2 ON s1.dropoff_date = s2.dropoff_date AND s1.regnum = s2.regnum
WHERE s2.regnum = 'CEZ 563'
GROUP BY e.empname, e.grade 
HAVING COUNT(w.[sid]) > 0
ORDER BY number_of_jobs DESC;