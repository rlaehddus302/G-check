INSERT INTO STUDENT (USERID, PASSWORD, ADMISSION_YEAR, MAJOR, ROLE, NAME) 
VALUES ('admin', '{noop}pass123', 2022, '전자공학과', 'ROLE_USER', '홍길동' );

INSERT INTO DEPARTMENT (department)
SELECT * FROM CSVREAD('classpath:major.csv', null, 'charset=UTF-8');
