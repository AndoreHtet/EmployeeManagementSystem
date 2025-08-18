create table employee (
                          id bigint not null auto_increment,
                          name varchar(255),
                          email varchar(255),
                          gender varchar(255),
                          birth_date date,
                          phone_number varchar(255),
                          department_id bigint,
                          created_at datetime,
                          updated_at datetime,
                          primary key (id),
                          constraint fk_employee_department
                              foreign key (department_id)
                                  references department (id)
);

INSERT INTO employee (name, email, gender, birth_date, phone_number, department_id) VALUES
                                                                                        ('John Smith', 'john.smith@example.com', 'MALE', '1990-05-15', '09123456789', 1), -- IT
                                                                                        ('Alice Johnson', 'alice.johnson@example.com', 'FEMALE', '1992-08-22', '09987654321', 1), -- IT
                                                                                        ('Michael Brown', 'michael.brown@example.com', 'MALE', '1988-11-30', '09234567890', 2), -- Finance
                                                                                        ('Sophia Lee', 'sophia.lee@example.com', 'FEMALE', '1995-01-12', '09456789012', 2), -- Finance
                                                                                        ('David Wilson', 'david.wilson@example.com', 'MALE', '1993-03-19', '09345678901', 3), -- HR
                                                                                        ('Emma Davis', 'emma.davis@example.com', 'FEMALE', '1996-06-25', '09567890123', 3), -- HR
                                                                                        ('Daniel Miller', 'daniel.miller@example.com', 'MALE', '1985-09-10', '09678901234', 4), -- Marketing
                                                                                        ('Olivia Garcia', 'olivia.garcia@example.com', 'FEMALE', '1994-12-05', '09789012345', 4);
