package com.kwave.android.sqliteorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kwave on 2017-06-09.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {     // OrmLiteSqliteOpenHelper ORMSQLite 사용하기 위해 상속

//------------------------ 데이터베이스 테이블 만들기 -------------------------------------------------------------------------------------------------------------------------------
    public static final String DATABASE_NAME = "database.db";       // 데이터 베이스 제목
    public static final int DATABASE_VERSION = 1;                    // 데이터 베이스 첫 버전

    private static DBHelper instance = null;        // DBHelper의 인스턴스를 만들기 위해 변수를 생성

    // DBHelper를 메모리에 하나만 있게 해서 효율을 높이기
    public static DBHelper getInstance(Context context){   // 싱글턴 패턴으로 하기
        // 호출한 쪽으로 DBHelper를 보내주려면 리턴타입이 DBHelper여야한다.

        // 외부에서 객체를 함부로 생성하지 못 하도록 싱글턴으로 만든다.
        // 외부에서 DBHelper의 인스턴스 생성시 new로는 생성하지 못 하고 DBHelper.getIntance(Context명)으로 해야한다.
        if(instance == null){
            instance = new DBHelper(context);       // DBHelper의 객체를 만들 수 있는 곳은 컨텍스트가 있는 액티비티이다.
        }
        return instance;                            //
    }


    // 최초 호출 될때 database.db 파일을 /data/ data/ 패키지명 / database / 디렉토리 아래에 생성해준다. - 파일을 만듦
    // - 생성자에서 DATABASE_NAME과 DATABASE_VERSION을 설정하기 때문이다.
    // 최초 호출 될때 database.db 파일을 /data/ data/ 패키지명 / database / database.db
//    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
//        super(context, databaseName, factory, databaseVersion);
//    }
    private DBHelper(Context context) {             // 생성자
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  //    => onCreate 실행
    }
// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//------------------버전과 설치에 따른 실행문 -------------------------------------------------------------------------------------------------------
    // 최초 생성되면 버전체크를 해서 onCreate를 호출한다.
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // 테이블을 생성해야한다.
        try {
            TableUtils.createTable(connectionSource, Memo.class);
//            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스가 변경 사항이 생겨 버전이 변경되었을때 onUpgrade이  호출된다.
    // 버전이 몇이건 이전에 설치된 적이 없는 사람은 OnCreate로 가고 설치된 적 있는 사람은 이곳으로 온다.
    // DATABASE_VERSION = 2;
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            // Memo 테이블의 특정 컬럼만 변경됐을 경우
            // 1. 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다.
            // ex) create table temp_memo,  <- 기존 데이터
            // 2. Memo 테이블을 삭제하고 다시 생성한다.
            // 3. 백업된 데이터를 다시 입력합니다.
            // 4. 임시 테이블 삭제

            TableUtils.createTable(connectionSource, Memo.class);
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------



//---------------------------ORMLite 사용하기-------------------------------------------------------------------------------------------------------------
    // 타입을 모를때는 일단 void 함수명을 만들어서 사용한다.

    // Create   - 데이터를 입력
    public void create(Memo memo){  // - new로 메모리에 올라가 있는 상태임을 알려줌
        try {
            // 1. 테아블에 연결
            //Dao : Data Access Object
            Dao<Memo, Integer> dao = getDao(Memo.class);
            //객체타입,id타입
            //2. 데이터를 입력
            dao.create(memo);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Read All     - 데이터 전체 읽기
    public List<Memo> readAll(){
        List<Memo> datas = null;
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            //2. 데이터 전체 읽어오기
            datas = dao.queryForAll();  // 테이블의 전체 데이터를 가지고 옴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    // read one     -- 데이터 1개 읽기
    public Memo read(int memoid){       // where 컬럼명 인자값을 해야 1개의 데이터베이스를 찾는데 이때 인자값을 주로 id를 쓴다.
        Memo memo = null;
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            //2. 데이터 1개 읽어오기
            memo = dao.queryForId(memoid);  // 테이블의 1개 데이터베이스를 가지고 옴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }

    // search - 데이터 검색하기(단어)     select * from memo where content like '%내용3%';
    public List<Memo> search(String word){  // 검색할 단어가 필요하므로 word를 인자로 갖는다.
        // 어떤 것을 검색할지 모르니 리턴타입을 List<Memo>로 한다.
        List<Memo> datas = null;
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            //2. 데이터 검색하기
            String query = "select * from memo where content like '%"+word+"%'";
            GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());  //쿼리를 사용하여 테이블의 전체 데이터를 가지고 옴
            datas = temps.getResults();     // 리스트 형태로 결과값을 가져옴

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }


    // Update
    public void update(Memo memo){  // update는 create와 같다.
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            //2. 데이터를 수정
            dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete object        // 전체 데이터를 삭제
    public void delete(Memo memo){
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            //2. 데이터를 삭제
            dao.delete(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Delete by Id - id를 입력하여 해당하는 줄을 삭제하기
    public void delete(int id){
        try {
            // 1. 테아블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);    //Dao : Data Access Object
            // 아이디를 삭제할 시
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//------------------------------------------------------------------------------------------------------------------------------------





}


