--생성
    -- writer 테이블 생성
    drop table if exists writer;
    create table if not exists writer
    (
        writer_id    int AUTO_INCREMENT primary key,
        name         varchar(20) not null,
        email        varchar(30) not null,
        created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
        edit_date    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    -- schedule 테이블 생성
    drop table if exists schedule;
    create table if not exists schedule
    (
       schedule_id  int AUTO_INCREMENT not null
           primary key,
       todo         varchar(50) not null,
       password     varchar(20) not null,
       created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
       edit_date    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       writer_id INT not null,
       writer       varchar(20) not null,
       foreign key (writer_id) references writer (writer_id)
    );

    -- 일정 생성
    insert into schedule (todo,password,writer_id,writer) values("오늘 할일1","abc123!",1,"아무개1");

    -- 일정 모두 조회
    select * from schedule;

    -- 일정 한개 조회
    select *
    from schedule
    where schedule_id = 1;

    -- 일정 한개 수정
    update schedule
    set todo = "hard코딩"
    where schedule_id = 1;

    -- 일정 한개 삭제
    delete from schedule
    where schedule_id = 3;

    -- 작성자 생성
    insert into writer (name,email) values("아무개1","a@b.com");

    -- 작성자 조회
    select *
    from writer
    where writer_id = 1;

    -- 작성자 수정
    update writer
    set email = 'b@c.com'
    where writer_id = 1;

    -- 작성자 삭제
    delete from writer
    where writer_id = 1;




