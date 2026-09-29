--例4.1 查询全体学生的学号与姓名。
select sno,sname
from student


--例4.2 查询全体学生的姓名、学号和所在系
select sname,sno,sdept
from [dbo].[student]

--例4.4 查询全体学生的姓名及其出生年份。
--没有出生年份，通过修改age来创建该列
select sname,2023-[sage] as 出生年份
from [dbo].[student]
--同样效果
select sname,出生年份=2023-sage
from [dbo].[student]


--例4.6 在修课表中查询有哪些学生修了课程，
------要求列出学生的学号。
--distinct 避免学号重复
select distinct sno
from sc

--例4.7 查询计算机系全体学生的姓名。
select sname
from student 
where sdept='计算机系'

--例4.9 查询考试成绩有不及格的学生的学号

--例4.10 查询年龄在20～23之间的学生姓名、所在系和年龄。
select sname ,[sdept],[sage]
from student 
--where sage<=23 and sage>=20
where sage between 20 and 23 --两边都是闭区间
--例4.11 查询年龄不在20～23之间的学生姓名、所在系和年龄。
select sname ,[sdept],[sage]
from student 
where sage<20 or sage >23

--例4.13 查询信息管理系、通信工程系和计算机系学生
--的姓名和性别。
select sname,ssex,sdept
from student 
--where sdept='信息管理系' or sdept='通信工程系' or sdept='计算机系'
where sdept in ('信息管理系','通信工程系','计算机系')

--例4.14 查询不在第2、4、6学期开设的课程的课程名和开课学期。
select cname,semester
from course
where semester not in(2,4,6)
--例4.15 查询学生表中姓‘王’的学生的详细信息。
select *
from student
where sname='王%'--表示王开头的一串文字
select *
from student
where ltrim(rtrim(sname)) like '王_'--表示王开头的一串文字
--rtrim代表去掉右边的空格
--ltrim代表去掉左边的空格

--例4.17 查询姓“张”、姓“李”和姓“刘”的学生详细信息。
select *
from student
where sname  like '[刘张李]%'--方括号里面没有分隔符

--例4.18 查询名字中第2个字为“小”或“大”字的学生的姓名和学号。
select *
from student
where sname  like '_[小大]%'

--例4.19 查询所有不姓“刘”的学生。
select *
from student
where sname not like '刘%'
--where sanme like '[^刘]%'

--例4.20 查询学号的最后一位不是2、3、5的学生信息。
select *
from student
where sno like '%[^235]'
--where fieldl like '%30！%%' escape '!' !是转义符 他后面的一个%此时不是通配符而是百分号
--例4.21 查询没有考试的学生的学号和相应的课程号。
select *
from sc
where grade is null

--例4.28 统计学生总人数。
select count(*) 
from student

--例4.29 统计选修了课程的学生的人数。
select count(distinct sno)
from sc
--例32  查询选了“C001” 课程的最高分和最低分。
select Max(grade) 最高分,min(grade) 最低分
from sc where cno='c001'
--统计函数不能出现在where后面

--例4.33 统计每门课程的选课人数，列出课程号和人数。
--题目出现“每”字，一般需要分组 每+集合函数
select cno as 课程号 ,COUNT(sno) as 课程人数
from sc
group by cno

--例4.34 查询每名学生的选课门数和平均成绩。
select sno,  COUNT(cno)选课门数,AVG(grade)平均成绩
from sc 
group by sno --按照sno 也就是每名学生 这四个关键字 分组 查询每个组的内容
--例4.34 统计每个系的女生人数
select sdept, COUNT(sno)人数
from student
where ssex='女'
group by sdept
--例4.37 统计每个系的男生人数和女生人数以及男生的最大年龄的女生的最大年龄。结果按照系名升序
select sdept as 系名,ssex as 性别,count(sno)人数,MAX(sage)最大年龄
from student
group by sdept,ssex --按照系和性别分组
order by sdept
--例38  查询选课门数超过3门的学生的学号和选课门数。
--集函数中不能使用where条件语句，要是用having来代替
--如果使用集函数，那么select中选中的列就要被包括在集函数中或者使用group by
--因此我们讲分组一个重要的标志就是集函数
--看不出对什么进行分组，就是对学号
select sno as 学号,count(cno) as 门数
from sc
group by sno
having count(cno)>3
--例39 查询平均成绩大于等于80的学生的学号、选课门数和平均成绩。
select sno as 学号,COUNT(cno) as 选课门数 ,AVG(grade) as 平均成绩
from sc
group by sno
having AVG(grade)>=80
--例4.49 查询学生的选课情况，包括选修了课程的学生和没有选修课程的学生。
--如果出现A和A反说明此时需要外连接
--题目意为学生一定要出现，没有选修课程就直接为null 因此学生表就要为left
--不要牵扯三个表
select s1.sname as 姓名 ,s2.cno as 选课编号
from student s1 left join sc s2   on s1.sno=s2.sno
 


--例4.50 查询没人选的课程，列出课程名。
--这个题目和上面不同的点就在于只要没人选的课程，而不是选的和没人选的课程都要列出来，
--因此要在上一个题目的基础上加一个筛选，只保留没人选的课程
select cname as 没人选的课程
from course c left join sc s on c.cno=s.cno
where s.sno is null

--例4.52 统计计算机系每个学生的选课门数，包括没选课的学生。
select s.sno,sname,COUNT(cno) as 选课门数
from student s left join sc c on s.sno=c.sno
group by s.sdept,s.sno,s.sname
having s.sdept='计算机系'
--第二种写的方法
select s.sno,sname,COUNT(cno) as 选课门数
from student s left join sc c on s.sno=c.sno
where s.sdept='计算机系'
group by  s.sno,s.sname
--例4.54 查询VB课程考试成绩前三名的学生的姓名和成绩。





--例4.63 查询与刘晨在同一个系的学生。
--子查询：可以分步写
select *
from student
where sdept=(select sdept
from student
where sname='刘晨') and sname<>'刘晨' --不相关子查询

--找刘晨的系
select sdept
from student
where sname='刘晨'

--这个是自连接 需要先从一个表中查到刘晨的系后在从这个表中找到其他同学
select s2.sname as 姓名
from  student s1 join student s2 on s1.sdept=s2.sdept
where s1.sname='刘晨' and s2.sname !='刘晨'



--例4.66 在选修了VB课程的这些学生中，
 
--统计他们的选课门数和平均成绩。 ==>统计每一位学生的选课门数和平均成绩。
select count(sc.cno)选课门数,AVG(grade)平均成绩
from sc join course on sc.cno=course.cno
where cname='vb'
select sno,count(sc.cno),avg(grade)
from sc
where sno in (select sno
from course join sc on course.cno=sc.cno
where cname='vb')
group by sno
--例4.68 查询C004课程考试成绩高于此课程的考试平均成绩的学生的学号和C004课程成绩
select sno,grade
from sc
where cno='C004' and grade>(select AVG(grade)
from sc
where cno='c004')
--这里可以使用集函数，因为父亲看不到代码
--此课程的平均成绩


--例4.71 查询选了“C002”课程的学生姓名。
select sname 
from student s join sc c on s.sno=c.sno
where c.cno='C002'


--例4.72 查询没选“C001”课程的学生姓名和所在系。
select sname ,sdept
from student s join sc c on s.sno=c.sno
where c.cno!='C001'
--出现冗余就通过group by来解决
group by s.sno,s.sname,s.sdept
SELECT sname, sdept
FROM student s
WHERE NOT EXISTS (
    SELECT 1 
    FROM sc c 
    WHERE s.sno = c.sno AND c.cno = 'C001'
)
select sname,sdept
from student 
select sno
from sc
where cno='C001'
select sname,sdept
from student
where sno not in (select sno 
from sc
where cno='c001')

--例4.73 查询计算机系没有选修VB课程的学生的姓名和性别。
 --全体课程
 select cno,cname
 from course
 --有人选的课程
 select distinct cno
 from sc 
 --合并
 select cno,cname
 from course
 where cno not in(select distinct cno
 from sc )

-- update
--
--
--例4.76  将所有学生的年龄加1。
update student
set sage=sage+1
 
--例4.77 将学号为“0611104”学生的年龄改为18岁。 
update student
set sage=18
where sno='0611104'
--例4.78 将计算机系全体学生的成绩加5分。
update sc
set grade=grade+5
from sc join student on sc.sno=student.sno
--where中要用student中的sdept，因此要多表连接
where sdept='计算机系'


--例4.79  将学分最低的课程的学分加2分。
update course
set credit=credit+2
--where 后面不能用极函数MIN(credit)，因此利用子查询换掉
where credit=(select min(credit) from course)
--修改全体学生的VB考试成绩，修改规则如下：
--对通信工程系的学生，成绩加10分
--对信息管理系的学生，成绩加5分
--对其他系的学生，成绩不变
update sc
set grade=grade+
case sdept
when '通信工程系' then 10
when '信息管理系' then 5
else  0
end
from student S join sc on s.sno=sc.sno
join course C on C.cno=sc.cno
where cname='VB'


--delete
--
--
--例4.81 删除所有学生的选课记录。
delete  from sc


--例4.82 删除所有不及格学生的修课记录。
delete from  sc 
where grade<60

--例4.83 删除计算机系不及格学生的修课记录。
delete from sc 
from sc join student on sc.sno=student.sno
where sdept='计算机系' and grade<60
--删除选课总学分最多的学生的记录
delete sc 
--考虑并列，则要用in不能用等号
where sno in(select top 1 sno
from sc join course on sc.cno=course.cno
where grade>=60 
group by sno
order by sum(credit) desc)


--课堂练习
--将没有选课的学生记录删除
--查询李勇同学高等数学考了多少分
--三表联合 用的是累加效应 先连两个表后成一个表之后和最后一个表在连
select grade
from student s1 join sc s2 on s1.sno=s2.sno
join  course c on s2.cno=c.cno
where s1.sname='李勇' and c.cname='高等数学'
 --查询每个学生及其选课的详细信息。
 select *
 from student join sc on student.sno=sc.sno
 --查询每个系学生考试的平均成绩
 select sdept as 系,AVG(grade) as 平均成绩
 from student join sc on student.sno=sc.sno
 group by sdept
 --查询和“数据结构”在同一个学期开设的课程名和开课学期
 select c2.cname ,c2.semester
 from course c1  join course c2 on c1.semester=c2.semester
 where c1.cname='数据结构' and c2.cname!='数据结构'

 select  top 3 Sname,Sage,sdept
 from student
 order by sage DESC
 --4.56 查询计算机系选课门数超过2门的学生中，考试平均成绩最高的前两名（包括并列的情况）学生的学号
 --，选课门数和平均成绩
SELECT TOP 2 WITH TIES S.Sno, 
    COUNT(*) 选课门数,AVG(Grade) 平均成绩
    FROM Student S JOIN SC ON S.Sno = SC.Sno
    WHERE Sdept = '计算机系'
    GROUP BY S.sno
    HAVING COUNT(*) > 2
    ORDER BY AVG(Grade) DESC --DESC代表降序
--
select sname from student
union --要确保兼容，也就是全部都是字符型或者数字 使用union可以实现将两个并在一起
select cname from course
--可以用or或者其他形式代替union

select *
into boy--以表的形式将结果永久保存在数据库中
from student
where ssex='男'


select *
into #girl--以表的形式将结果临时保存在数据库中
--临时表不属于当前数据库 保存在系统数据库tempdb的临时表中
from student
where ssex='女'
--如果要查询临时表 要用这种的格式
select * from #girl

--课本习题
--p116 16题
--查询哪些课程没人选，并列出课程号和课程名
 
 --插入
 --将一个新生插入到Student表中
 insert into Student
 values('0621105','陈冬','男',18,'信息管理系');
 --必须按照顺序写，如果不按照顺序写的话，就要写出来字段列表
 --eg
 insert into sc(sno,cno)
 values('0621105','C045')  
 --将查询结果插入到新表中
 select sno,sname,ssex
 into boy1 --这里必须是新表
 from student
 where ssex='男' and sage<=20
 --追加到boy1表格中 
 insert into boy1
 select sno,sname,ssex
 from student
 where ssex='男' and sage>20
