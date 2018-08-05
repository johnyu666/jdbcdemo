
drop sequence john_seq10;
drop table books;

create sequence john_seq10;

create table books(id number primary key,book_name varchar2(20),price number(5,2));

insert into books(id,book_name,price) values(john_seq10.nextval,'sss',234.87);
select * from books;