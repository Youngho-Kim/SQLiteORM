#SQLite
## TABLE(릴레이션)
* 기본 관계형 데이터베이스의 데이터 구조 (엑셀)
* 컬럼(어트리뷰트), 로우(튜플, 엔트리)
* 스키마: 컬럼의 정의
### Data Define Language (DDL) 기본 명령어
#### CREATE: 테이블 생성
```SQL
CREATE TABLE table_name (col1_name col1_type col1_restriction, col2_name col2_type)
```
#### ALTER: 테이블 변경 (테이블 이름 변경, 컬럼 추가; 컬럼 이름 변경 불가, 컬럼 삭제 불가)
```SQL
ALTER TABLE table_name ADD COLUMN col3_name col3_type
```
#### DROP: 테이블 삭제, 복구 불가
```SQL
DROP TABLE table_name
```
## 로우(튜플, 엔트리 등등)
### Data  Manipulation Language (DML)기본 명령어
#### INSERT: 로우 추가
* VALUES: 로우 각 컬럼의 값들
* DEFAULT: 컬럼에 기본값 삽입
```SQL
INSERT INTO table_name (col1_name,col2_name,col3_name) VALUES (col1_data,col2_data,col3_data);INSERT INTO table_name (col1_name,col2_name,col3_name) DEFAULT;
```
#### UPDATE: 로우 수정
```SQL
//테이블 전체 모든 로우의 해당 col1의 데이터를 모두 바꾸는 쿼리문
UPDATE table_name SET col1_name = col1_new_data;
//특정 조건의 로우에만 적용
UPDATE table_name SET col1_name = col1_new_data WHERE col2_name = col2_condition;
```
#### DELETE: 로우 삭제
```SQL
//UPDATE와 유사 WHERE문이 없으면 전체에 영향을 준다.
DELETE FROM table_name WHERE col2_name ==col2_condition;
```
#### SELECT: 로우 조회
* 프로젝션: 조회시 특정 컬럼만 추출하는 동작 목록 또는 와일드카드(*)
```SQL
SELECT col1_name, col3_name FROM table_name WHERE col2_name = col2_condition;
```
* ORDER BY: 질의 결과값 정렬
```SQL
SELECT col1_name, col3_name FROM table_name WHERE col2_name = col2_condition ORDER BY col3_name DESC;
//기본값 ASC
```
* 2개 이상의 테이블의 결과값을 조회해 하나로 합쳐 반환
```SQLSELECT col1_name, col3_name FROM table_name JOIN table2_name ON (table_name.col2_name = col2_name);
```
### SQLite의 타입
* TEXT: 문자열 (VARCHAR, STRING)
* NUMERIC: 모든 데이터, 미지정    
1. 입력 데이터를 INTEGER 또는 REAL로 변환해본다    
2. 성공하면 해당 클래스로 저장 실패하면 문자열로 저장한다
* INTEGER: 정수 (INT, LONG)
* REAL: 부동 소수 (FLOAT,DOUBLE)
* BLOB: 이진데이터 (사실상 모든 데이터를 있는 그대로)
+ NULL: 널
## 안드로이드의 SQLite
###SQLiteOpenHelper
* 추상클래스
* SQLiteDatabase 인스턴스를 만들어 SQLite를 조작한다.
###SQLiteDatabase
* 데이터베이스와 상호작용하는 클래스
* query(), create(), delete() 등
* insert()
```Java    
insert(String table, String[] nullhacks, ContentValues contents)
```
* update()
```Java    
update(String table, ContentValues contentValues, String whereClause, String[] whereArgs);    
update("sample_table", contentValues, "col1 =?, col2 =?",new String[]{"1","150"});    
=> UPDATE sampe_table SET ~contentvalues내용 WHERE ON (col1=1,col2=150);
```
* delete()
* beginTransaction();    
+ 원자성    
+ 효율성
* setTransactionSuccessful();
* endTransaction();
* query();
```Java    
Cursor resultCursor = db.query(tableName, columns, selection, selectionArgs, groupBy,having,orderby)
    db.query("sample_table", new String[] {"col1","col2"}, "col1 = ?", new String[]{"1"},null,null,null)    
    => SELECT col1,col2 FROM sample_table WHERE col1 = 1;    
    db.rawQuery("SELECT col1,col2 FROM sample_table WHERE col1 = ?",new String[]{"1"});
```
###Cursor* 데이터 질의 결과* 커서를 가지고 앱에서 주로 데이터 작업을 함
* 반환 데이터와, 현재 가리키는 로우 를 가지고 있음
* move(), moveToFirst(), moveToLast(), moveToPosition(), moveToPrevious()
* moveToNext();* cursor.close();
```Java    
while(cursor.moveToNext()){        
    String col1 = cursor.getString(0);       
     ...    
     }    
     cursor.close();
```

###ContentValues
* 키/값 데이터를 테이블 로우에 저장한다.(컬럼/로우)
* put###CursorLoader
* 커서를 부르는 로더
* 커서 자원 관리, 비동기
###LoaderManager
* 로더를 관리하는 객체
### Setho*Chrome 기반 디버거
```Gradle    
compile 'com.facebook.stetho:stetho:1.3.1'
```
*디버거 모드에서만 활성화
```Java
public void onCreate(){    
    super.onCreate();    
    if(BuildConfig.DEBUG{        
        Stetho.initializeWithDefaults(this);    
    }
}
```
