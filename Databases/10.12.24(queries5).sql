alter table emp
add avaliability_stat varchar(25);

update emp 
set avaliability_stat = 'unavailable'
where emp.empid = 'E7291';

------
Select emp.empid, emp.empname, avaliability_stat
from emp
where avaliability_stat = 'unavailable'

