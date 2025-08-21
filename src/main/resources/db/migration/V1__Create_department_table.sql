
create table department (
                            id bigint not null auto_increment,
                            code varchar(255),
                            name varchar(255),
                            region varchar(255),
                            created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            primary key (id)
);


INSERT INTO department (id, code, name, region, created_at, updated_at)
VALUES
    (1, 'HR', 'Human Resources', 'Yangon', NOW(), NOW()),
    (2, 'IT', 'Information Technology', 'Mandalay', NOW(), NOW()),
    (3, 'FIN', 'Finance', 'Naypyidaw', NOW(), NOW()),
    (4, 'MKT', 'Marketing', 'Yangon', NOW(), NOW()),
    (5, 'SALE','Sale','Yangon',NOW(),NOW());