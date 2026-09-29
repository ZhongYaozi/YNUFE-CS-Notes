  --建库的基本操作
create database 财务数据 --数据库名字
on(name=财务数据1,--主数据文件，逻辑文件名为teacher，物理文件名为teacher.mdf
filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\财务数据1.mdf',
size=8mb,
filegrowth=1mb),
(name=财务数据2,--次要数据文件，逻辑文件名为teacher_data1，物理文件名为teacher_data1.ndf
 filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\财务数据2.ndf',
 size=3mb,
 filegrowth=10%
)
log on(
name=财务日志1,--日志文件
filename='D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\财务日志1.ldf',--一定要确保路径找的到
size=2mb,
filegrowth=10%--增量 可以用mb也可以用百分号
),
(
    NAME = 财务日志2,
    FILENAME = 'D:\数据库\MSSQL13.MSSQLSERVER\MSSQL\DATA\财务日志2.ldf',
    SIZE = 4MB--临近括号的语句结尾不加逗号，其余语句结尾就加一个逗号
)
