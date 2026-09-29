--查询全体学生的学号和姓名 
select sno,sname from student
--查询全体学生的详细记录
select *from student
--查询全体学生的姓名及其出生年份 √
select sname,2024-sage from student
--查询全体学生的姓名及其出生年份，并在出生年份列前加入一列，此列的每行数据均为“出生年份”常量值
--注意 如果中文不是列名而是列内容则一定带单引号
select sname,'出生年份' 出生年份,2024-sage 年 from student
--查询选修了课程的学生学号
select sno from sc
--查询计算机系全体学生的姓名
select sname from student
where sdept='计算机系'
--查询所有年龄在20岁以下的学生的姓名及年龄
select sname,sage from student
where sage<20
--查询考试成绩中有不及格的学生的学号
select sno from sc
where grade <60
--查询年龄在20-23岁的学生的姓名、所在系和年龄
select sname,sdept,sage from student
where sage between 20 and 23
--查询年龄不在20-23岁的学生的姓名、所在系和年龄
select sname,sdept,sage from student
where sage not between 20 and 23
--查询信息管理系、通信工程系和计算机系学生的姓名和性别
select sname,ssex from student
where sdept in('信息管理系','通信工程系','计算机系')
--查询不在第2、4、6学期开设的课程名和开课学期
select cname,semester from course
where semester not in(2,4,6)
--查询所有姓“王”的学生的详细信息
select * from student
where sname like '王%'  
--like 后面要加单引号
--%....代表最后的内容要匹配 ....%代表最前的内容要匹配
--查询姓“王”且姓名是3个字节的学生
select * from student
where sname like '王__'
--查询姓“张”、姓“李”和姓“刘”的学生的详细信息
select * from student
--多个内容匹配，要[]来存放内容
where sname like '[张李刘]%'--[]里面的汉字不用加顿号
--查询姓名中第2个字为“小”或“大”字的学生的姓名和学号
select sname,sno from student
where sname like '_[小大]%'
--不要忘记加最后的%，只要求第二个字为小或大并没有要求只有两个字
--查询所有不姓“刘”的学生
select * from student
where sname not like '刘%'
--从学生表中查询学号的最后一位不是2、3、5的学生信息
select * from student
where sno not like '%[235]'
--查询没有考试成绩的学生的学号、课程号和成绩
select sno,cno,grade from sc
where grade is null
--查询计算机系年龄在20岁以下的学生的姓名和年龄
select sname,sage from student
where sdept='计算机系' and sage<20
--查询计算机系和信息管理系学生中年龄为18-20岁的学生的学号、姓名、所在系和年龄
select sno,sname,sdept,sage from student
where sdept in ('计算机系','信息管理系') and sage between 18 and 20
--将学生按年龄的升序排列
select  * from student
order by sage
--查询选了“C002”号课程的学生的学号及其成绩，查询结果按成绩降序排列
select sno,grade from sc
where cno='C002'
order by grade desc
--程序就是从上到下执行，同时也代表查询的逻辑变化
--本题为例就是代表先选出课程号为C002的学生的学号成绩，然后再将这些记录按照成绩降序
 

--查询全体学生的信息，查询结果按所在系的系名升序排列，同系的学生按年龄降序排列。
select * from student 
order by sdept,sage desc
--统计学生总人数。
select COUNT(*) from student
--统计选修了课程的学生的人数
select COUNT(distinct sno) from sc
 
--这道题主要是防止重复，存在有的学生选了多门课程
--计算“0611101”号学生的考试总成绩。
select sum(grade) from sc
where sno='0611101'
--查询计算机系学生的最大年龄和最小年龄。
select max(sage),min(sage) from student
where sdept='计算机系'
---统计每个系的学生人数。
select count(sno) from student
group by sdept
---统计每门课程的选课人数和考试最高分。记得加上每门课程的编号
select cno,count(sno),max(grade) from sc
group by cno
-- 统计每个学生的选课门数和考试总成绩，并按选课门数升序显示结果。
select sno,count(cno),sum(grade) from sc
group by sno
order by count(cno) 
--查询总成绩超过200分的学生，要求列出学号和总成绩。
select sno,sum(grade) from sc
group by sno
having sum(grade)>200
--查询选课门数超过2门的学生的学号、平均成绩和选课门数。
select  sno,avg(grade),COUNT(cno) from sc
group by sno
having COUNT(cno)>2
--查询选了“C002”课程的学生的姓名和所在系。
select sname,sdept from student join sc on student.sno=sc.sno
where cno='C002'
--查询成绩80分以上的学生的姓名、课程号和成绩，并按成绩降序排列结果。
select sname,cno,grade from student join sc on student.sno=sc.sno
where grade >80
order by  grade desc
--查询计算机系男生修了“数据库基础”的学生的姓名、性别和成绩。
select sname ,ssex,grade from student join sc on student.sno=sc.sno
join course on sc.cno=course.cno
where sdept='计算机系' and cname='数据库基础' and ssex='男'
select sname,ssex,grade 
from student s join sc on s.sno = sc.sno
              join course c on c.cno = sc.cno
where sdept = '计算机系' and ssex = '男' and cname = '数据库基础'
--查询学生的选课情况，要求列出每位学生的选课情况（包括未选课的学生），并列出学生的学号、姓名、课程号和考试成绩。
select s.sno,sname,cno,grade from student s left outer join sc ss on s.sno=ss.sno
--查询哪些课程没有人选，要求列出课程号和课程名。--这里要注意 范围大的强行连上范围小的 则范围小的必然有null,如果要选的话用范围大的来选 
select course.cno,cname from course left outer join sc on course.cno=sc.cno
where sc.cno is null
--查询计算机系没有选课的学生，列出学生姓名。
select sname  from student s left outer join sc ss on s.sno=ss.sno
where sdept='计算机系' and ss.sno is null
--列出“数据库基础”课程考试成绩前三名的学生的学号、姓名、所在系和考试成绩。
select top 3 with ties s1.sno,sname,sdept,grade  from student s1 join sc s2 on s1.sno=s2.sno
join course c on s2.cno=c.cno
where c.cname='数据库基础'
order by grade desc
select top 3 s.sno, sname, sdept, grade
      from Student s join SC on s.Sno = SC.Sno
          join Course c on c.Cno = SC.Cno
      where cname = '数据库基础'
      order by grade desc
--查询VB考试成绩最低的学生的姓名、所在系和VB成绩。
select sname,sdept,grade from student s1 join sc s2 on s1.sno=s2.sno
join course c on s2.cno=c.cno
where cname ='VB' and grade =(
select min(grade) from sc join course
 on sc.cno=course.cno
 where course.cname='VB')
 select top 1 with ties sname,sdept,grade from student s
        join sc on s.sno = sc.sno
        join course c on c.cno = sc.cno
     where cname = 'VB'
     order by grade asc 
 --查询有考试成绩的所有学生的姓名、修课名称及考试成绩，要求将查询结果放在一张新的永久表中，假设新表名为new_sc。
 SELECT sname,cname,grade into new_sc
 from student join sc on student.sno=sc.sno join course on course.cno=sc.cno
 where grade is not null
 -- 分别查询信息管理系和计算机系的学生的姓名、性别、修课名称、修课成绩，
 --并要求将这两个查询结果合并成一个结果集，并以系名、姓名、性别、修课名称、修课成绩的顺序显示各列。
 select sname,ssex,cname,grade from student join sc on student.sno=sc.sno join course on sc.cno=course.cno
 where sdept='信息管理系'
 union 
 select sname,ssex,cname,grade from student join sc on student.sno=sc.sno join course on sc.cno=course.cno
 where sdept='计算机系'
 --查询选了“C001”课程的学生姓名和所在系。
 select sname,sdept from student
 where sno in(
 select sno from sc
 where cno='C001')
 --查询通信工程系成绩80分以上的学生学号和姓名。
 select sno,sname from student 
 where sdept='通信工程系' and sno in(
 select sno from sc
 where grade>80)
 --查询计算机系考试成绩最高的学生姓名。
 select  sname from student 
 where sno in(
 select  top 1 sno from sc
 order by grade desc )
 and sdept='计算机系'
 select top 1 sname from student join sc on student.sno=sc.sno
 where sdept='计算机系'
 order by grade desc
 --查询年龄最大的男生的姓名和年龄。
 --找到男生里面年龄最大的后把数值记住
 --根据这个数值在男生中找到对应 的人
 --里层和外层都是缺一不可的
 --关键是分得清楚步骤
 
 select sname,sage from student
 where sage=(
 select top 1 sage from student 
 order by sage desc)
 and   ssex='男'
 
 select sname,sage from student
      Where sage = (select max(sage) from student where ssex = '男')
        and ssex = '男'
--查询“C001”课程的考试成绩高于“C001”课程的平均成绩的学生的学号和“C001”课程成绩。
select sc.sno,grade from student join sc on student.sno=sc.sno
where cno='C001' and grade>(
select avg(grade) from sc 
where cno='C001')
--将 “C001”课程的考试成绩加10分。
update sc set grade =grade +10
where cno='C001'
--将计算机系所有选修了“计算机文化学”课程的学生成绩加10分，分别用子查询和多表连接形式实现。
--子查询
update sc set grade =grade+10 
where sno in(
select sno from student
where sdept='计算机系') and
cno in(
select cno from course 
where cname='计算机文化学')
--多表查询
update sc set grade =grade+10
from student join sc on student.sno=sc.sno join course on sc.cno=course.cno 
where sdept='计算机系' and cname='计算机文化学'
--删除修课成绩小于50分的学生的选课记录。
delete from sc
where grade <50
--删除信息管理系考试成绩小于50分的学生的该门课程的修课纪录，分别用子查询和多表连接形式实现。
delete from sc 
where grade <50 and sno in(
select sno from student
where sdept='信息管理系')
delete from sc
from sc join student  on sc.sno=student.sno
where sdept='信息管理系' and grade <50 
--删除VB考试成绩最低的学生的VB修课记录。
--注意存在两个约束 一个是课程名 一个是最低分 所以要找的课程名对应的课程号 后还要找到这个课程下的最低分
delete from sc
where grade =(
select min(grade) from sc join course on sc.cno=course.cno
where cname='VB'
) and cno  in(
select cno from course
where cname='VB')
--在删除这里不能用top 只能通过子查询实现最
--计算“C001”号课程的考试平均成绩
select avg(grade) from sc
where cno='C001'
--查询“C001”号课程的考试最高分和最低分
select max(grade) 最高分,min(grade)最低分 from sc
where cno='C001'
--where子句中不能使用集函数
--统计每门课程的选课人数，列出课程号和人数
select cno 课程号,COUNT(sno) 人数 from sc
group by cno
--查询每个的选课门数和平均成绩
select sno 学号,count(cno) 选课门数, AVG(grade)平均成绩 from sc
group by sno
--统计每个系的学生人数和平均年龄
select sdept 系名,count(sno) 学生人数,AVG(sage) 平均年龄 from student
group by sdept
--带WHERE子句的分组。统计每个系的女生人数
select sdept  系名, count(sno) 女生人数 from student
where ssex='女'
group by sdept
--按多列分组。统计每个系的男生人数和女生人数以及男生的最大年龄和女生的最大年龄。结果按系名的升序排序。
--分块后分别对每个块进行聚合函数运算
select sdept 系名, ssex 姓别,COUNT(sno) 人数 ,max(sage)最大年龄 from student
group by sdept,ssex
order by sdept
-- 查询选课门数超过3门的学生的学号和选课门数。
select sno,count(cno)选课门数 from sc
group by sno
having COUNT(cno) >3
--如果用集函数作为选择条件，那么用having来选，不能用where
--这个题的思路就会分好组之后 用having来选择
--或者这样来处理 如果是集函数作为选择条件的话 那么必然用having 如果用having 则必然要分组
--将学号进行分组时，一般题干信息不明显，找不到用谁分组时可能就是用学号
--查询平均成绩大于或等于80 分的学生的学号、选课门数和平均成绩，
select sno 学号,COUNT(cno) 选课门数,AVG(grade) 平均成绩 from sc
group by sno
having AVG(grade) >=80
-- 查询每个学生及其选课的详细息。
--select * from sc
--group by sno
--上述代码会报错，因为使用了group by但是select中出现了不光sno这列
 --主要出现group by子句那么select中的列只能是group by子句中的列或者聚合函数中的列
--这道题要用到表连接 学生的信息在student表中而选课的详细信息在sc表中
select * from student join sc 
on student.sno=sc.sno --连接的条件，不符合这个条件的全部剔除 即在学生表中有姓名且已经选了课程，没选课会被剔去
--观察答案可知，“每”的实现不一定全部是group by 可能是内连接
--去掉例4.40中的重复列。
select student.sno,sname,ssex,sage,sdept,cno,grade from student join sc
on student.sno=sc.sno --使用内连接的时候一定注意，如果两个表中存在相同的列，那么在选择的时候一定要指明用的是哪个表的列
--查询计算机系学生的修课情况，要求列出学生的名字、所修课的课程号和成绩
--这道题要注意，因为指明了计算机系选课的学生，因此不包括没有选的，如果只是简单查询student的会把没有选课的也查出来，因此需要
--两表连接实现剔除的功能这是隐含功能要注意读题干发现 只是一个表会不会出现范围大，需不需要剔除
select s1.sno,cno,grade from student s1 join sc s2
on s1.sno=s2.sno
where s1.sdept='计算机系'
--查询信息管理系选修“计算机文化”课程的信息，要求列出学生的姓名、课程名和成绩。
select s1.sno,s2.cno,c.cname,grade from student s1 join sc s2 
on s1.sno=s2.sno 
join course c on s2.cno=c.cno --三表连接的方式
where sdept='信息管理系' and c.cname='计算机文化'
--查询选了 VB 课程的学生姓名和所在系
select s1.sname,s1.sdept from student s1 join sc s2 
on s1.sno=s2.sno 
join course c on s2.cno=c.cno --三表连接的方式
where c.cname='VB'
--有分组的多表连接查询。
--查询每个系的学生的考试平均成绩
select sdept,AVG(grade) 平均成绩 from student join sc
on student.sno=sc.sno
group by sdept
--有分组和行筛选的多表连接查询。
--查询计算机系每门课程的选课人数，学生的平均成绩、最高成绩和最低成绩
select cname,AVG(grade) 平均成绩,COUNT(s1.sno) 选课人数,max(grade) 最高成绩,min(grade) 最低成绩 
from student s1 join sc s2 on s1.sno=s2.sno
join course c on s2.cno=c.cno
where s1.sdept='计算机系'
group by cname

--查询学生的选课情况，包括选课的学生和没有选课的学生
select student.sno,sname,cno,grade from student left outer join sc
on student.sno=sc.sno
--查询没人选的课程，列出课程名。
--先把选的和没选的都找出来，然后再筛选出没人选的
select cname from course left outer join sc 
on course.cno=sc.cno
where sc.sno is null
--查询计算机系没选课的学生，并列出学生姓名和性别
select sname,ssex from student left outer join sc
on student.sno =sc.sno
 where sdept='计算机系' and sc.cno is  null
--统计计算机每个学生的选课门数，包括没有选课的学生。
select student.sno,count(cno) 选课门数 from student left outer join sc
on student.sno=sc.sno
where sdept='计算机系'
group by student.sno

--查询年龄最大的三名学生的姓名、年龄及所在系
select top 3 sname,sage,sdept from student
order by sage desc
--查询 VB 课程考试成绩前三名的学生的姓名和成绩
select top 3 with ties sname,grade 
from student join sc on student.sno=sc.sno
join course on course.cno=sc.cno
where cname='VB '
order by grade desc
--查询选课人数最少的两门课程(不包括没有人选的课程，并列出课程号和选课人数
select top 2 with ties cno,count(sno)选课人数 from sc
group by cno
order by count(sno)
--查询计算机系选课门数超过两门的学生中，考试平均成绩最高的前两名(包括并列的情况)学生的学号、选课门数和平均成绩
select top 2 with ties s.sno,count(cno)选课门数,AVG(grade)平均成绩
from student s join sc on s.sno=sc.sno
where sdept='计算机系'
group by s.sno
having count(cno)>2
order by AVG(grade) desc
--如果是是最高，则降序
--将计算机系学生的查询结果与信息管理系学生的查询结果合并为一个结果集
select sno,sname,sage,sdept from student where  sdept='计算机系'
union --把重复的给删掉
select sno,sname,sage,sdept from student where  sdept='信息管理系'
--将计算机系学生的查询结果与信息管理系学生的查询结果合并为一个结果集，并将年龄从大到小排序
select sno,sname,sage,sdept from student where  sdept='计算机系'
union --把重复的给删掉
select sno,sname,sage,sdept from student where  sdept='信息管理系'
order by sage desc
--查询计算机系学生的姓名、选修的课程名和成绩，并将查询结果保存到永久表S_C_G中
select sname,cname,grade into S_C_G
from student join sc on student.sno=sc.sno
join course on sc.cno=course.cno
where sdept='计算机系'
--查询与刘晨在同一个系学习的学生，列出他们的学号、姓名和所在系。
select sno,sname,sdept from student
where sdept in (
  select sdept from student
  where sname='刘晨')
  --查询与刘晨在同一个系学习的学生，列出他们的学号、姓名和所在系,并且不包括刘晨
  select sno,sname,sdept from student
where sdept in (
  select sdept from student
  where sname='刘晨')
  and sname !='刘晨'
--查询考试成绩大于 90 分的学生的学号和姓名
--使用子查询
select sname,sno from student
where sno in(
select sno from sc
where grade>90)
--使用多表连接查询
select sname, student.sno
from student join sc on student.sno=sc.sno
where grade >90
--查询选修了“VB”课程的学生的学号和姓名
select sno,sname from student
where sno in(
select sno from sc
where cno in(
 select cno from course
 where cname='VB'))
--在选修了VB 课程的这些学生中，统计他们的选课门数和平均成绩
select count(cno)选课门数,AVG(grade)平均成绩 from sc
where sno in(
select sno from sc
where cno in(
 select cno from course
 where cname='VB'))
 group by sno
--查询选修了“VB”课程的学生的学号、姓名和 VB 成绩。
select s2.sno,sname,grade VB成绩  
from student s1 join sc s2 on s1.sno=s2.sno
join course c on c.cno=s2.cno
where c.cname='VB'
--没有办法使用多表连接查询，因为要求返回结果中涉及到了多个表的属性
--查询 C004 课程的平均考试成绩，找出高于此课程的平均考试成绩的学生的学号和C004 课程成绩。
select sno,grade from sc
where grade >(
 select AVG(grade) from sc
 where cno ='C004') 
 and cno='C004'
 
--查询计算机系年龄最大的学生的姓名和年龄
--使用top实现
select  top 1 with ties sname ,sage from student 
where sdept='计算机系' 
order by sage desc
--使用子查询
select sname,sage from student
where sdept ='计算机系' 
and sage =(
select max(sage) from student
where sdept ='计算机系')
---查询信息管理系学生中年龄大于该系学生平均年龄的学生姓名和年龄
select sname,sage from student 
where sdept ='信息管理系'
and sage >(
select AVG(sage) from student
where sdept='信息管理系')
--将一个新生插入 Student 表中，其学号为0621105，姓名为陈冬，性别为男，年龄为18岁，该生为信息管理系的学生
insert into student values('0621105','陈冬','男',18,'信息管理系')
--在SC表中插入一条新记录，学号为“0621105”，选修的课程的课程号为“CO01”成绩暂缺。
insert into sc(sno,cno) values('062115','C001')
--将所有学生的年龄加1
update student set sage =sage+1
--将学号为“0611104”学生的年龄改为18岁
update student set sage=18
where sno='0611104'
--将计算机系全体学生的成绩加5分
update student set sage=sage+5
where sdept='计算机系'
--将学分最低的课程的学分加2分
update course set credit=credit+2
where credit =(
select min(credit) from course
)
--修改全体学生的 VB 考试成绩，修改规则为:对通信工程系学生，成绩加 10分;对信息管理系学生，成绩加5分;对其他系学生，成绩不变。
-- 删除所有学生的选课记录。
delete from sc
--删除所有不及格学生的选课记录
delete from sc 
where grade<60
--删除计算机系不及格学生的选课记录
delete from sc
where grade<60 and sno in(
select sno from student 
where sdept='计算机系')
--insert into 表名（列） value(列对应的值)
 
 --查询计算机系年龄最大的学生的姓名和年龄
 select top 1 sname,sage from student
 where sdept='计算机系'
 order by sage desc
 select sname sage from student
 where sdept ='计算机系' and sage =(
 select max(sage) from student
 where sdept='计算机系')
 --查询信息管理系学生中年龄大于该系学生平均年龄的学生姓名和年龄
 select sname,sage from student 
 where sdept='信息管理系' and sage >(
 select AVG(sage) from student 
 where sdept='信息管理系')
 --使用子查询一定是想清楚是集合还是找值
 --查询哪些课程没有人选，并列出课程号和课程名
 select c.cno,cname from course c left outer join sc s on c.cno=s.cno
 where s.cno is null
 --统计每个系的男生人数和女生人数以及男生的最大年龄和女生的最大年龄，结果按照系名进行排列
 select sdept,ssex,count(sno),max(sage) from student
 group by sdept,ssex
 order by sdept 
 --统计计算机系每个学生的选课门数，包括没有选课的学生
 select s1.sno,count(cno)
from student s1 left outer join sc s2 on s1.sno=s2.sno
where sdept='计算机系'
group by s1.sno
