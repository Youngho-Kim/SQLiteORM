package com.kwave.android.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by kwave on 2017-06-09.
 */

@DatabaseTable(tableName = "memo")  // 어노테이션의 기능 : 데이터베이스의 테이블로 쓸것이다라고 알려줌
                                    // 그런데 그 테이블의 이름은 memo 라고 말해줌.
public class Memo {

//--------------------------- 속성값 정의-----------------------------------------------------------
    @DatabaseField(generatedId = true)  // generatedId : 자동증가값
    private int id;
    @DatabaseField  // 아래 변수를 데이터 베이스의 필드로 사용 할 것이라고 알림
    private String title;
    @DatabaseField
    private String content;

    String remark;  // 어노테이션이 없는 것은 데이터베이스 필드로 사용 X
    @DatabaseField
    private Date date;
//-------------------------------------------------------------------------------------------------



//----------------------기본 생성자 ----------------------------------------------------------------
    public Memo(){
        //OrmLite는 기본 생성자가 없으면 동작하지 않습니다.
        setDate();      // 작성 시간 세팅
    }
//-------------------------------------------------------------------------------------------------

//----------------------Getter Setter ----------------------------------------------------------------
    public Date getDate() {
        return date;
    }

    private void setDate() {
        Date date = new Date(System.currentTimeMillis());
        this.date = date;
    }

    public Memo(String title, String content){
     this.title = title;
        this.content = content;
        setDate();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {        // id 값은 자동입력이라 세팅되면 안되므로 private로 해준다.
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
//-------------------------------------------------------------------------------------------------