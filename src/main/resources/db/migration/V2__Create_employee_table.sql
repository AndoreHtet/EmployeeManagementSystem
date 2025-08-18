create table employee (
                          id bigint not null auto_increment,
                          first_name varchar(255),
                          last_name varchar(255),
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
