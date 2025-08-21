

create table employee (
                          id bigint not null auto_increment,
                          employee_id varchar(50),
                          name varchar(255),
                          email varchar(255),
                          gender varchar(255),
                          birth_date date,
                          phone_number varchar(255),
                          department_id bigint,
                          created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          primary key (id),
                          constraint fk_employee_department
                              foreign key (department_id)
                                  references department (id)
);

INSERT INTO employee (employee_id, name, email, gender, birth_date, phone_number, department_id)
VALUES
    ('EMP-0001', 'Aung Aung', 'aung.aung1@example.com', 'MALE', '1990-01-01', '0911111111', 1),
    ('EMP-0002', 'Hla Hla', 'hla.hla2@example.com', 'FEMALE', '1992-05-10', '0922222222', 2),
    ('EMP-0003', 'Kyaw Kyaw', 'kyaw.kyaw3@example.com', 'MALE', '1988-07-15', '0933333333', 1),
    ('EMP-0004', 'Su Su', 'su.su4@example.com', 'FEMALE', '1995-09-20', '0944444444', 3),
    ('EMP-0005', 'Mya Mya', 'mya.mya5@example.com', 'FEMALE', '1993-12-30', '0955555555', 2),
    ('EMP-0006', 'Ko Ko', 'ko.ko6@example.com', 'MALE', '1991-02-11', '0966666666', 1),
    ('EMP-0007', 'Thandar', 'thandar7@example.com', 'FEMALE', '1994-03-22', '0977777777', 2),
    ('EMP-0008', 'Min Min', 'min.min8@example.com', 'MALE', '1989-04-25', '0988888888', 3),
    ('EMP-0009', 'Swe Swe', 'swe.swe9@example.com', 'FEMALE', '1996-05-15', '0999999999', 1),
    ('EMP-0010', 'Tun Tun', 'tun.tun10@example.com', 'MALE', '1992-06-18', '0910101010', 2),
    ('EMP-0011', 'Zaw Zaw', 'zaw.zaw11@example.com', 'MALE', '1993-07-20', '0911111011', 3),
    ('EMP-0012', 'Sandar', 'sandar12@example.com', 'FEMALE', '1994-08-22', '0912121212', 2),
    ('EMP-0013', 'Thura', 'thura13@example.com', 'MALE', '1995-09-25', '0913131313', 1),
    ('EMP-0014', 'May May', 'may.may14@example.com', 'FEMALE', '1996-10-28', '0914141414', 3),
    ('EMP-0015', 'Chan Chan', 'chan.chan15@example.com', 'MALE', '1997-11-30', '0915151515', 2),
    ('EMP-0016', 'Wai Wai', 'wai.wai16@example.com', 'FEMALE', '1990-01-12', '0916161616', 1),
    ('EMP-0017', 'Soe Soe', 'soe.soe17@example.com', 'MALE', '1991-02-14', '0917171717', 3),
    ('EMP-0018', 'Ei Ei', 'ei.ei18@example.com', 'FEMALE', '1992-03-16', '0918181818', 2),
    ('EMP-0019', 'Bo Bo', 'bo.bo19@example.com', 'MALE', '1993-04-18', '0919191919', 1),
    ('EMP-0020', 'Yu Yu', 'yu.yu20@example.com', 'FEMALE', '1994-05-20', '0920202020', 2),
    ('EMP-0021', 'Htet Htet', 'htet.htet21@example.com', 'MALE', '1995-06-22', '0921212121', 3),
    ('EMP-0022', 'Shwe Shwe', 'shwe.shwe22@example.com', 'FEMALE', '1996-07-24', '0922222223', 1),
    ('EMP-0023', 'Nyein Nyein', 'nyein.nyein23@example.com', 'FEMALE', '1997-08-26', '0923232323', 2),
    ('EMP-0024', 'Moe Moe', 'moe.moe24@example.com', 'MALE', '1990-09-28', '0924242424', 3),
    ('EMP-0025', 'Khaing Khaing', 'khaing.khaing25@example.com', 'FEMALE', '1991-10-30', '0925252525', 2),
    ('EMP-0026', 'Thazin', 'thazin26@example.com', 'FEMALE', '1992-11-02', '0926262626', 1),
    ('EMP-0027', 'Lin Lin', 'lin.lin27@example.com', 'MALE', '1993-12-04', '0927272727', 3),
    ('EMP-0028', 'Pyae Pyae', 'pyae.pyae28@example.com', 'MALE', '1994-01-06', '0928282828', 2),
    ('EMP-0029', 'Nandar', 'nandar29@example.com', 'FEMALE', '1995-02-08', '0929292929', 1),
    ('EMP-0030', 'Htut Htut', 'htut.htut30@example.com', 'MALE', '1996-03-10', '0930303030', 2);
