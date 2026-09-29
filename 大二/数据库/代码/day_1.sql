 --建库的基本操作
create database teacher --数据库名字
on(name=teacher,--主数据文件，逻辑文件名为teacher，物理文件名为teacher.mdf
filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\teacher.mdf',
size=3mb,
maxsize=unlimited),
(name=teacher_data1,--次要数据文件，逻辑文件名为teacher_data1，物理文件名为teacher_data1.ndf
 filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\teacher_data1.ndf',
 size=5mb,
 maxsize=10mb,
 filegrowth=1mb
)
log on(
name=teacher_log,--日志文件
filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\teacher_log.ldf',--一定要确保路径找的到
size=2mb,
maxsize=6mb,
filegrowth=10%--增量 可以用mb也可以用百分号
)
--临近括号的语句结尾不加逗号，其余语句结尾就加一个逗号