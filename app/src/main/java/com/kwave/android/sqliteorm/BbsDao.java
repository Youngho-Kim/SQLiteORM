package com.kwave.android.sqliteorm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by kwave on 2017-06-09.
 */

//---------------------------------BbsDao 데이터베이스 ------------------------------------------------------------------------
public class BbsDao {
    DBHelper helper;
    private static BbsDao bbsDao = null;
    Dao<Bbs, Integer> dao;

    public static BbsDao getInstance(Context context){
        if(bbsDao == null){
            bbsDao = new BbsDao(context);
        }

        return bbsDao;
    }

    private BbsDao(Context context) {
        // 1. 데이터베이스 연결
        helper = DBHelper.getInstance(context);
        try {
            // 2. 테이블 연결
            dao =  helper.getDao(Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------------------------------------



//---------------------------------Bbs 데이터베이스 ------------------------------------------------------------------------
    public void create(Bbs bbs){
        try {
            dao.create(bbs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bbs> read(){
        // 1. 데이터베이스 연결

        // 2. 테이블 연결
        List<Bbs> datas = null;
        try {
            // 1. 테아블에 연결
            dao = helper.getDao(Bbs.class);    //Dao : Data Access Object
            //2. 데이터 전체 읽어오기
            datas = dao.queryForAll();  // 테이블의 전체 데이터를 가지고 옴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }
    public void update(Bbs bbs){
        try {
            // 1. 테아블에 연결
            dao = helper.getDao(Bbs.class);    //Dao : Data Access Object
            //2. 데이터를 수정
            dao.update(bbs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Bbs bbs){
        try {
            // 1. 테아블에 연결
            dao = helper.getDao(Bbs.class);    //Dao : Data Access Object
            // 아이디를 삭제할 시
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------

}
