CREATE TABLE jellyfish_memo(
  idx number(4) primary key,
  name varchar2(20) not null,
  msg varchar2(100),
  wdate date default sysdate
  );
  
  create sequence jellyfish_memo_seq nocache;
  
  select * from jellyfish_memo;
  
  select * from jellyfish_memo order by idx desc;
  
  select * from(select rownum rn, a.* from(select * from jellyfish_memo order by idx desc) a) where rn between 1 and 5;
  
  drop table jellyfish_memo;
  
  
select * from reply;

select * from memo;

select * from board;

create table tisgame_board(
	idx number(8) constraint tisgame_board_pk primary key,--�۹�ȣ
	name varchar2(30) not null,--�ۼ���
	pwd varchar2(20) not null,--���
	subject varchar2(200),--����
	content varchar2(2000),--����
	wdate timestamp default systimestamp,--�ۼ���
	readnum number(8) default 0, --��ȸ��
	filename varchar2(200),--÷�����ϸ�[����Ͻú���_a.jpg]
	filesize number(8) --÷������ ũ��
);

drop table tisgame_board;

create sequence tisgame_board_seq nocache;

select * from tisgame_board;

create table tisgame_reply(
	num number(8) primary key,
	name varchar2(30) not null,
	content varchar2(500),
	wdate date default sysdate,
	idx_fk number(3) not null,
	--constraint tisgame_reply_userid_fk foreign key (userid) references member(userid),
	constraint tisgame_reply_idx_fk foreign key (idx_fk) references tisgame_board(idx)
);

create sequence tisgame_reply_seq nocache;

select * from tisgame_reply;

----------------------------------------------------------------------------------------

create table tisgame_notice(
	idx number(8) constraint tisgame_notice_pk primary key,--�۹�ȣ
	name varchar2(30) not null,--�ۼ���
	pwd varchar2(20) not null,--���
	subject varchar2(200),--����
	content varchar2(2000),--����
	wdate timestamp default systimestamp,--�ۼ���
	readnum number(8) default 0 --��ȸ��
);

create sequence tisgame_notice_seq nocache;

select * from tisgame_notice;

select * from tisgame_notice order by idx desc;

select * from(select rownum rn, a.* from(select * from tisgame_notice order by idx desc) a) where rn between 1 and 5;

select * from tab;