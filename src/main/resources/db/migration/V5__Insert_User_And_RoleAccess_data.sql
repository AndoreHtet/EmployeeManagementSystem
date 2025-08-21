-- Roles
INSERT INTO role (name) VALUES
                            ('ADMIN'),
                            ('MANAGER');

-- Access rule for employee list
INSERT INTO role_access (name, url, request_method, crud_operation, description) VALUES
    ('ADMIN', '/api/employee/employee-list', 'POST', 'READ', 'Can view employee list'),
    ('ADMIN','/api/employee/create-employee','POST','CREATE','Register Employee'),
    ('ADMIN', '/api/employee/edit-employee','POST','UPDATE','Edit Employee'),
    ('ADMIN','/api/employee/employee-detail','GET','READ','Employee Detail'),
    ('ADMIN','/api/employee/delete-employee','DELETE','DELETE','Delete Employee');

-- Link both roles to the access rule
INSERT INTO role_role_access (role_id, role_access_id)
SELECT r.id, ra.id
FROM role r CROSS JOIN role_access ra
WHERE r.name IN ('ADMIN', 'MANAGER') AND ra.name = 'ADMIN';

-- Users (use real hashed passwords in practice)
INSERT INTO user (name, email, password, user_role_id)
SELECT 'Admin',  'admin@gmail.com', '$2a$12$hwnK4aATbgovplf17zeN0.jI5lbh0ISB9C3uemvdNmKFbLy5AhHx2', id FROM role WHERE name = 'ADMIN';
INSERT INTO user (name, email, password, user_role_id)
SELECT 'Manager',  'manager@gmail.com',   '$2a$12$hwnK4aATbgovplf17zeN0.jI5lbh0ISB9C3uemvdNmKFbLy5AhHx2', id FROM role WHERE name = 'MANAGER';
