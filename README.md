# SQLiteORM
use SQLite for android
그대에게
지금우리

### DDL (Data Definition Language)   : 테이블을 생성하기 위한 언어
#### 1. 테이블 생성 쿼리 (CREATE) 
// create table 테이블이름 (컬럼이름1 컬럼속성, 컬럼이름2 속성...);
```SQL
create table memo(memoid int, title varchar(250), content text);
```
int 숫자  
char(250)   // 사용은 얼마를 하든 공간은 무조건 250byte. 넘지만 않으면 됨
varchar(250) // 내가 사용할수 있는 최대 공간 250 byte  
text // 대용량의 데이터를 넣을 수 있다.

### DML (Manipulation) : 데이터를 조작하기 위한 언어
#### 2. 데이터 입력쿼리 (INSERT)                                         ↓외따옴표
// insert into 테이블이름 (컬럼이름1, 컬럼이름2.....) values(숫자값,'문자값'...);
```SQL
insert into memo(memoid, title, content) values(1, '제목','내용입니다');
```
#### 3. 데이터 조회쿼리(SELECT)
// select 컬럼이름1, 컬럼이름2.... from 테이블이름 where 조건절;
```SQL
select memoid,title from memo; // 조건절이 없으면 전체 데이터를 가져온다.
```
select * from memo; // 컬럼이름 대신에 * 를 사용하면 전체 컬럼을 가져온다.
// 일반조건절
```SQL
select memoid,title,content from memo where momoid=1;
```
// 문자열 중간검색    '%검색문자열%'
```SQL
select * from memo where content like '%내용3%';
```
#### 4. 데이터 수정쿼리
// update 테이블이름 set 컬럼이름=숫자값, 컬럼이름='문자값' where 조건절;
```SQL
update memo set content='수정되었습니다', title='제목이 수정되었습니다' where memoid=1;
```
#### 5. 데이터 삭제쿼리
// delete from 테이블이름 where 조건절
```SQL
delete from memo where memoid=2;
```

#### 6. 컬럼값 자동 증가 
//만들기
```SQL
create table memo2(memoid integer primary key, title varchar(250), content text);
```
//실행
```SQL
insert into memo(title, content) values( '제목','내용입니다');
```