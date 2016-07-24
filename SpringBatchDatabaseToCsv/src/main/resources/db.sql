create table EXAM_RESULT (
   student_name VARCHAR(30) NOT NULL,
   dob DATE NOT NULL,
   percentage  double NOT NULL
);
 
insert into exam_result(student_name,dob,percentage) value
('Brian Burlet','1985-02-01',76),
('Rita Paul','1993-02-01',92),
('Han Yenn','1965-02-01',83),
('John Kerr','1988-05-12',27),
('Chris Rogers','1974-08-30',35),
('Peter Pan','1987-02-03',62);


SET SQL_SAFE_UPDATES=0;