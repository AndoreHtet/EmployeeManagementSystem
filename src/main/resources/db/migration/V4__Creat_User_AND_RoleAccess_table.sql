-- ROLE
CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(45) NOT NULL UNIQUE,
                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ROLE_ACCESS
CREATE TABLE role_access (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(45) NOT NULL,
                             url VARCHAR(255) NOT NULL,
                             request_method VARCHAR(20) NOT NULL,   -- must match your enum values (e.g., GET, POST)
                             crud_operation VARCHAR(20) NOT NULL,   -- must match your enum values (e.g., READ, CREATE)
                             description VARCHAR(255),
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- JOIN TABLE (many-to-many: role <-> role_access)
CREATE TABLE role_role_access (
                                  role_id BIGINT NOT NULL,
                                  role_access_id BIGINT NOT NULL,
                                  PRIMARY KEY (role_id, role_access_id),
                                  CONSTRAINT fk_rra_role      FOREIGN KEY (role_id)       REFERENCES role(id)        ON DELETE CASCADE,
                                  CONSTRAINT fk_rra_roleacc   FOREIGN KEY (role_access_id) REFERENCES role_access(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- USER
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(45) NOT NULL,
                      email VARCHAR(45) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      user_role_id BIGINT NOT NULL,
                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      CONSTRAINT fk_user_role FOREIGN KEY (user_role_id) REFERENCES role(id)
) ENGINE=InnoDB;