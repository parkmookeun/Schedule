--생성
    -- writer 테이블 생성
    create table if not exists mydb.writer
    (
        writer_id    int         not null
            primary key,
        name         varchar(20) null,
        email        varchar(30) null,
        created_date varchar(10) null,
        edit_date    varchar(10) null
    );
    -- schedule 테이블 생성
    create table if not exists mydb.schedule
    (
        schedule_id  int         not null
            primary key,
        todo         varchar(50) null,
        password     varchar(20) null,
        created_date varchar(10) null,
        edit_date    varchar(10) null,
        writer_id    int         null,
        constraint schedule_ibfk_1
            foreign key (writer_id) references mydb.writer (writer_id)
    );

    create index writer_id
        on mydb.schedule (writer_id);

    -- 일정 생성
    insert into schedule values(2,"sparta코딩1","sparta","2024-10-30","2024-10-30",1);
    insert into schedule values(3,"sparta코딩2","sparta","2024-10-30","2024-10-30",1);

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




