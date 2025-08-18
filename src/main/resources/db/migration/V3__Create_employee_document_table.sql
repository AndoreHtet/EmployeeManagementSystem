create table employee_document (
                                   id bigint not null auto_increment,
                                   document_name varchar(255),
                                   position varchar(255),
                                   address varchar(255),
                                   salary decimal(19,2),
                                   duration varchar(255),
                                   employee_id bigint,
                                   created_at datetime,
                                   updated_at datetime,
                                   primary key (id),
                                   constraint fk_document_employee
                                       foreign key (employee_id)
                                           references employee (id)
);