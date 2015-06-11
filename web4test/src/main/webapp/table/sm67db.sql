-- 회원정보
DROP TABLE IF EXISTS USER RESTRICT;

-- 모듈개인설정
DROP TABLE IF EXISTS MYSETTING RESTRICT;

-- 모듈
DROP TABLE IF EXISTS MODULE RESTRICT;

-- 회원정보
CREATE TABLE USER (
	email    VARCHAR(40) NOT NULL COMMENT '이메일', -- 이메일
	username VARCHAR(50) NULL     COMMENT '이름', -- 이름
	birthday DATE        NULL     COMMENT '생년월일', -- 생년월일
	theme    VARCHAR(20) NOT NULL COMMENT '테마', -- 테마
	language VARCHAR(20) NOT NULL COMMENT '언어', -- 언어
	countmod INTEGER     NOT NULL COMMENT '모듈개수' -- 모듈개수
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE USER
	ADD CONSTRAINT PK_USER -- 회원정보 기본키
		PRIMARY KEY (
			email -- 이메일
		);

-- 모듈개인설정
CREATE TABLE MYSETTING (
	email  VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	modno  INTEGER      NOT NULL COMMENT '모듈번호', -- 모듈번호
	modloc VARCHAR(50)  NOT NULL COMMENT '위치', -- 위치
	modset VARCHAR(255) NULL     COMMENT '설정' -- 설정
)
COMMENT '모듈개인설정';

-- 모듈개인설정
ALTER TABLE MYSETTING
	ADD CONSTRAINT PK_MYSETTING -- 모듈개인설정 기본키
		PRIMARY KEY (
			email -- 이메일
		);

-- 모듈
CREATE TABLE MODULE (
	modno    INTEGER      NOT NULL COMMENT '모듈번호', -- 모듈번호
	modname  VARCHAR(50)  NOT NULL COMMENT '모듈이름', -- 모듈이름
	moddesc  VARCHAR(255) NOT NULL COMMENT '모듈설명', -- 모듈설명
	modpic   VARCHAR(255) NOT NULL COMMENT '모듈사진', -- 모듈사진
	category VARCHAR(50)  NOT NULL COMMENT '카테고리' -- 카테고리
)
COMMENT '모듈';

-- 모듈
ALTER TABLE MODULE
	ADD CONSTRAINT PK_MODULE -- 모듈 기본키
		PRIMARY KEY (
			modno -- 모듈번호
		);

ALTER TABLE MODULE
	MODIFY COLUMN modno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모듈번호';

-- 모듈개인설정
ALTER TABLE MYSETTING
	ADD CONSTRAINT FK_USER_TO_MYSETTING -- 회원정보 -> 모듈개인설정
		FOREIGN KEY (
			email -- 이메일
		)
		REFERENCES USER ( -- 회원정보
			email -- 이메일
		);

-- 모듈개인설정
ALTER TABLE MYSETTING
	ADD CONSTRAINT FK_MODULE_TO_MYSETTING -- 모듈 -> 모듈개인설정
		FOREIGN KEY (
			modno -- 모듈번호
		)
		REFERENCES MODULE ( -- 모듈
			modno -- 모듈번호
		);