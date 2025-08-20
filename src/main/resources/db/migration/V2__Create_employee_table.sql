create table employee (
                          id bigint not null auto_increment,
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
-- IT Department
INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('John Smith', 'john.smith@example.com', 'MALE', '1990-05-15', '09123456789', 1),
                                                                                        ('Alice Johnson', 'alice.johnson@example.com', 'FEMALE', '1992-08-22', '09987654321', 1),
                                                                                        ('Ethan Clark', 'ethan.clark@example.com', 'MALE', '1991-02-10', '09111111111', 1),
                                                                                        ('Grace Hall', 'grace.hall@example.com', 'FEMALE', '1993-04-18', '09111111112', 1),
                                                                                        ('Henry Young', 'henry.young@example.com', 'MALE', '1989-07-09', '09111111113', 1),
                                                                                        ('Isabella King', 'isabella.king@example.com', 'FEMALE', '1994-10-25', '09111111114', 1),
                                                                                        ('James Adams', 'james.adams@example.com', 'MALE', '1990-12-12', '09111111115', 1),
                                                                                        ('Mia Baker', 'mia.baker@example.com', 'FEMALE', '1995-06-30', '09111111116', 1),
                                                                                        ('Noah Scott', 'noah.scott@example.com', 'MALE', '1987-03-05', '09111111117', 1),
                                                                                        ('Sophia Turner', 'sophia.turner@example.com', 'FEMALE', '1996-09-21', '09111111118', 1);

-- Finance Department
INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('Michael Brown', 'michael.brown@example.com', 'MALE', '1988-11-30', '09234567890', 2),
                                                                                        ('Sophia Lee', 'sophia.lee@example.com', 'FEMALE', '1995-01-12', '09456789012', 2),
                                                                                        ('Oliver Harris', 'oliver.harris@example.com', 'MALE', '1992-02-02', '09222222211', 2),
                                                                                        ('Ava Lewis', 'ava.lewis@example.com', 'FEMALE', '1993-07-14', '09222222212', 2),
                                                                                        ('Liam Walker', 'liam.walker@example.com', 'MALE', '1990-08-08', '09222222213', 2),
                                                                                        ('Charlotte Allen', 'charlotte.allen@example.com', 'FEMALE', '1994-11-29', '09222222214', 2),
                                                                                        ('William Young', 'william.young@example.com', 'MALE', '1989-03-20', '09222222215', 2),
                                                                                        ('Emily King', 'emily.king@example.com', 'FEMALE', '1996-04-04', '09222222216', 2),
                                                                                        ('Benjamin Wright', 'benjamin.wright@example.com', 'MALE', '1991-10-17', '09222222217', 2),
                                                                                        ('Harper Hill', 'harper.hill@example.com', 'FEMALE', '1995-12-06', '09222222218', 2);

-- HR Department
INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('David Wilson', 'david.wilson@example.com', 'MALE', '1993-03-19', '09345678901', 3),
                                                                                        ('Emma Davis', 'emma.davis@example.com', 'FEMALE', '1996-06-25', '09567890123', 3),
                                                                                        ('Lucas Lopez', 'lucas.lopez@example.com', 'MALE', '1990-01-22', '09333333311', 3),
                                                                                        ('Amelia Moore', 'amelia.moore@example.com', 'FEMALE', '1992-09-09', '09333333312', 3),
                                                                                        ('Mason Taylor', 'mason.taylor@example.com', 'MALE', '1988-05-03', '09333333313', 3),
                                                                                        ('Evelyn Anderson', 'evelyn.anderson@example.com', 'FEMALE', '1995-03-27', '09333333314', 3),
                                                                                        ('Logan Thomas', 'logan.thomas@example.com', 'MALE', '1991-06-11', '09333333315', 3),
                                                                                        ('Abigail Jackson', 'abigail.jackson@example.com', 'FEMALE', '1993-08-19', '09333333316', 3),
                                                                                        ('Elijah White', 'elijah.white@example.com', 'MALE', '1987-04-30', '09333333317', 3),
                                                                                        ('Scarlett Martin', 'scarlett.martin@example.com', 'FEMALE', '1996-12-01', '09333333318', 3);

-- Marketing Department
INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('Daniel Miller', 'daniel.miller@example.com', 'MALE', '1985-09-10', '09678901234', 4),
                                                                                        ('Olivia Garcia', 'olivia.garcia@example.com', 'FEMALE', '1994-12-05', '09789012345', 4),
                                                                                        ('Matthew Robinson', 'matthew.robinson@example.com', 'MALE', '1990-11-02', '09444444411', 4),
                                                                                        ('Sofia Clark', 'sofia.clark@example.com', 'FEMALE', '1993-07-07', '09444444412', 4),
                                                                                        ('Jackson Rodriguez', 'jackson.rodriguez@example.com', 'MALE', '1991-01-19', '09444444413', 4),
                                                                                        ('Avery Lewis', 'avery.lewis@example.com', 'FEMALE', '1995-09-23', '09444444414', 4),
                                                                                        ('Carter Walker', 'carter.walker@example.com', 'MALE', '1989-02-15', '09444444415', 4),
                                                                                        ('Ella Hall', 'ella.hall@example.com', 'FEMALE', '1996-08-30', '09444444416', 4),
                                                                                        ('Jack Allen', 'jack.allen@example.com', 'MALE', '1992-05-05', '09444444417', 4),
                                                                                        ('Lily Young', 'lily.young@example.com', 'FEMALE', '1994-10-11', '09444444418', 4);

-- Sales Department
INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('Sebastian Scott', 'sebastian.scott@example.com', 'MALE', '1988-12-20', '09555555511', 5),
                                                                                        ('Victoria Green', 'victoria.green@example.com', 'FEMALE', '1992-06-14', '09555555512', 5),
                                                                                        ('Aiden Baker', 'aiden.baker@example.com', 'MALE', '1990-02-25', '09555555513', 5),
                                                                                        ('Hannah Adams', 'hannah.adams@example.com', 'FEMALE', '1995-03-07', '09555555514', 5),
                                                                                        ('Samuel Perez', 'samuel.perez@example.com', 'MALE', '1987-11-11', '09555555515', 5),
                                                                                        ('Chloe Rivera', 'chloe.rivera@example.com', 'FEMALE', '1993-01-01', '09555555516', 5),
                                                                                        ('Joseph Campbell', 'joseph.campbell@example.com', 'MALE', '1991-04-04', '09555555517', 5),
                                                                                        ('Aria Mitchell', 'aria.mitchell@example.com', 'FEMALE', '1996-07-21', '09555555518', 5),
                                                                                        ('David Carter', 'david.carter@example.com', 'MALE', '1989-09-09', '09555555519', 5),
                                                                                        ('Zoe Roberts', 'zoe.roberts@example.com', 'FEMALE', '1994-12-12', '09555555520', 5);


INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('Ryan Evans', 'ryan.evans@example.com', 'MALE', '1991-08-08', '09666666611', 1),
                                                                                        ('Madison Torres', 'madison.torres@example.com', 'FEMALE', '1993-05-15', '09666666612', 2),
                                                                                        ('Dylan Murphy', 'dylan.murphy@example.com', 'MALE', '1988-04-20', '09666666613', 3),
                                                                                        ('Layla Cooper', 'layla.cooper@example.com', 'FEMALE', '1996-09-01', '09666666614', 4),
                                                                                        ('Nathan Reed', 'nathan.reed@example.com', 'MALE', '1992-11-11', '09666666615', 5),
                                                                                        ('Zara Bailey', 'zara.bailey@example.com', 'FEMALE', '1995-06-22', '09666666616', 1),
                                                                                        ('Owen Foster', 'owen.foster@example.com', 'MALE', '1989-07-07', '09666666617', 2),
                                                                                        ('Lillian Gray', 'lillian.gray@example.com', 'FEMALE', '1994-03-18', '09666666618', 3),
                                                                                        ('Gabriel Hughes', 'gabriel.hughes@example.com', 'MALE', '1990-02-02', '09666666619', 4),
                                                                                        ('Nora Ward', 'nora.ward@example.com', 'FEMALE', '1992-10-10', '09666666620', 5);