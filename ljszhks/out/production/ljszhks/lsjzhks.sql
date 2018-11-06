create table airinfo(
    id number(5,0) primary key,  --������
    flight_number number(5,0) unique, --�����
    destination varchar2(20), --Ŀ�ĵ�
    flight_date date  --�������
)

create sequence airinfo_id_sq;

insert into airinfo 
values(airinfo_id_sq.nextval,1001,'����',to_date('2018-01-01','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1002,'�㶫',to_date('2018-02-02','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1003,'�Ϻ�',to_date('2018-03-03','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1004,'�Ĵ�',to_date('2018-04-04','yyyy-MM-dd'));

insert into airinfo 
values(airinfo_id_sq.nextval,1005,'����',to_date('2018-05-05','yyyy-MM-dd'));



