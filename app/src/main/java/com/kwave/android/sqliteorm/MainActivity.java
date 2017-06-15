package com.kwave.android.sqliteorm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = DBHelper.getInstance(this);       // DBHelper가 없으면 새로 생성. 있으면 생성 X
                                                            // new로 생성하지 않음.

//        // 1. 데이터 입력
//        for(int i=0; i<10; i++){          // 메모테이블 10개 만들기
//            Memo memo = new Memo();       // 메모객체 생성
//            memo.setTitle("제목");        // 메모 제목 작성
//            memo.setContent("내용");      // 메모에 넣을 내용 입력
////        memo.setDate();                 // 작성 시간 세팅
//            helper.create(memo);          // 위에 입력된 데이터를 바탕으로 Memo의 인스턴스인 memo를 만들어줌
//        }
//
//        // 2. 데이터 한개 읽어오기
//        Memo one = helper.read(3);        Memo 타입의 변수인 one은 DBHelper에 저장된 3번째 데이터베이스를 갖는다.
//        Log.i("Memo", + one.getId() + ": title = "+one.getTitle() + ", content = "+one.getContent());
//
//        // 3. 데이터 전체 읽어오기
//        List<Memo> datas = helper.readAll();  전체 데이터를 가져와 Memo타입의 datas변수에 리스트형태로 갖고온다.
//        for(Memo memo : datas){               datas에 입력된 데이터를 memo 순서로 출력한다.
//            Log.i("Memo", + memo.getId() + ": title = "+memo.getTitle() + ", content = "+memo.getContent());
//        }

        // 4. 데이터 검색하기
        // 기본 데이터 넣기
        helper.create(new Memo("제목1", "내용1"));
        helper.create(new Memo("제목2", "내용2"));
        helper.create(new Memo("제목3", "내용3"));
        helper.create(new Memo("제목4", "내용4"));
//        //검색하기
//        List<Memo> datas = helper.search("내용3");
//        for(Memo memo : datas){
//            Log.i("Memo", + memo.getId() + ": title = "+memo.getTitle() + ", content = "+memo.getContent());
//        }


        // 5. 수정하기
        Memo memo = helper.read(3); // 데이터베이스에 있는 수정할 내용을 가져와서 수정한다.
        memo.setContent("내용");
        helper.update(memo);


        // 6. 삭제하기
        helper.delete(5);



        // BbsDao의 접근제한자를 private으로 만들고 사용해보세요
        BbsDao dao = BbsDao.getInstance(getBaseContext());
        dao.create(new Bbs());
    }
}
