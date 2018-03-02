drop database deb8;

create database deb8;

use deb8;

create table user(
	id INT(10) primary key auto_increment,
	email VARCHAR(30),
    passwd CHAR(60) not null,
	name VARCHAR(20),
	bio VARCHAR(100),
	reg_time BIGINT not null,
	code CHAR(30) not null
);

-- 주제 : 입력, 조회 가능
create table topic(
	id INT(10) primary key auto_increment,
    writer_id INT(10) not null,
	title VARCHAR(50) not null,
	reg_time bigint not null,
	code CHAR(30) not null,
    
    FOREIGN KEY(writer_id)
		REFERENCES user(id)
        ON DELETE CASCADE
);

-- 게시글 : 입력, 삭제, 조회 가능
create table post(
	id INT(10) primary key auto_increment,
	writer_id INT(10) not null,
	topic_id INT(10) not null,
	reg_time bigint not null,
	contents VARCHAR(200) not null,
	hearts INT(10) not null default 0,

	FOREIGN KEY(writer_id) 
        REFERENCES user(id)
        ON DELETE CASCADE,

    FOREIGN KEY(topic_id) 
        REFERENCES topic(id)
        ON DELETE CASCADE
);

-- 좋아요 기능 
create table heart(
	id INT(10) not null primary key auto_increment,
	giver_id INT(10) not null,
	post_id INT(10) not null,
	reg_time BIGINT not null,

    FOREIGN KEY(giver_id) 
        REFERENCES user(id)
        ON DELETE CASCADE,

    FOREIGN KEY(post_id) 
        REFERENCES post(id)
        ON DELETE CASCADE,
        
	UNIQUE KEY unique_heart (giver_id, post_id)
);

create table animal_pool(
	id INT(10) not null primary key auto_increment,
	name VARCHAR(20) not null
);

create table adjective_pool(
	id INT(10) not null primary key auto_increment,
	name VARCHAR(20) not null
);

delimiter //

CREATE TRIGGER heart_increment BEFORE INSERT ON heart
  FOR EACH ROW
  BEGIN
    UPDATE post SET hearts = hearts + 1 WHERE id = NEW.post_id;
  END;
//

CREATE TRIGGER heart_decrement BEFORE DELETE ON heart
  FOR EACH ROW
  BEGIN
    UPDATE post SET hearts = hearts - 1 WHERE id = OLD.post_id;
  END;
//

delimiter ;

insert into user values
	(1, "test1", "$2a$10$zVcuVq8q3vn5NWn/wJjJsus0jY78sglaEbAQdBHcritlc.3YMYvWe", "모자 쓴 딱따구리", null, 1518143192358, "2o83u2o83"),
	(2, "test2", "$2a$10$Xn6XokzhXQm9m3JlL8gmXO4ZNoJEFQSFLQsLvp3PlGLifN7Lq3ERW", "발이 큰 시라소니", "안녕하세요!", 1518143192358, "38ufo334f"),
	(3, "test3", "$2a$10$zeLLJjeA2f9PnSrdSZ/HDeJ4vS2GYNvtuhDbWZNuqeGPidR2.d.wK", "날렵한 코끼리", "이 사이트 좋아요", 1518143192358, "38567o784");

insert into topic values
	(1, 1, "피카소는 세잔과 어떤 관계였을까?", 1518143192358, "weNEwrhwTW"),
	(2, 2, "점심 짜장면 짬뽕", 1518143192358, "rrWTjrWhw"),
	(3, 3, "도를 아십니까", 1518143192358, "rgeg2ku33");

insert into post values
	(1, 1, 1, 1518143192358, "피카소가 자기 유일한 스승이 세잔이라고 했다던데요.", 2),
	(2, 1, 2, 1518143192358, "점심엔 짜장면을 먹어야 합니다. 맛있잖아요.", 1),
	(3, 1, 3, 1518143192358, "...", 0),
	(4, 2, 3, 1518143192378, "eeke?", 0),
	(6, 3, 3, 1518143192398, "아요요.", 0);

insert into heart values
	(1, 1, 1, 1518143192358),
	(2, 1, 3, 1518143192358),
	(3, 2, 2, 1518143192358);