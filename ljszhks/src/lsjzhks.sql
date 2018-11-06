create table airinfo(
    id number(5,0) primary key,  --航班编号
    flight_number number(5,0) unique, --航班号
    destination varchar2(20), --目的地
    flight_date date  --起飞日期
)

create sequence airinfo_id_sq;

insert into airinfo 
values(airinfo_id_sq.nextval,1001,'北京',to_date('2018-01-01','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1002,'广东',to_date('2018-02-02','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1003,'上海',to_date('2018-03-03','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1004,'四川',to_date('2018-04-04','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1005,'西藏',to_date('2018-05-05','yyyy-MM-dd'));



