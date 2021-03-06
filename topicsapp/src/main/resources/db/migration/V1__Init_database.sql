CREATE TABLE ROLES(
ROL_ID INT PRIMARY KEY NOT NULL,
ROL_TYPE VARCHAR(255) NOT NULL,
CONSTRAINT ROL_TYPE_UNIQUE UNIQUE (ROL_TYPE)
);

COMMENT ON COLUMN ROLES.ROL_ID IS 'Role ID (primary key)';
COMMENT ON COLUMN ROLES.ROL_TYPE IS 'Role type (unique)';

CREATE TABLE USERS(
USR_ID INT PRIMARY KEY NOT NULL,
USR_EMAIL VARCHAR(255) NOT NULL,
USR_PASSWORD VARCHAR(255) NOT NULL,
USR_FIRST_NAME VARCHAR(255) NOT NULL,
USR_LAST_NAME VARCHAR(255) NOT NULL,
USR_ROL_ID INT REFERENCES ROLES (ROL_ID) NOT NULL,
USR_INDEX_NO VARCHAR(255),
USR_REGISTRATION_DATE TIMESTAMP DEFAULT now() NOT NULL,
CONSTRAINT USR_EMAIL_UNIQUE UNIQUE (USR_EMAIL),
CONSTRAINT USR_INDEX_NO_UNIQUE UNIQUE (USR_INDEX_NO)
);

COMMENT ON COLUMN USERS.USR_ID IS 'User ID (primary key)';
COMMENT ON COLUMN USERS.USR_EMAIL IS 'User login';
COMMENT ON COLUMN USERS.USR_PASSWORD IS 'User password';
COMMENT ON COLUMN USERS.USR_FIRST_NAME IS 'User first name';
COMMENT ON COLUMN USERS.USR_LAST_NAME IS 'User last name';
COMMENT ON COLUMN USERS.USR_ROL_ID IS 'User associated role (roles foreign key)';
COMMENT ON COLUMN USERS.USR_INDEX_NO IS 'User unique index number (for students only)';

CREATE TABLE SUBJECTS(
SUB_ID INT PRIMARY KEY NOT NULL,
SUB_NAME VARCHAR(255) NOT NULL,
SUB_DESCRIPTION VARCHAR(255),
SUB_TEACHER_ID INT REFERENCES USERS (USR_ID) NOT NULL,
SUB_ECTS INT,
CONSTRAINT SUB_NAME_UNIQUE UNIQUE (SUB_NAME)
);

COMMENT ON COLUMN SUBJECTS.SUB_ID IS 'Subject ID (primary key)';
COMMENT ON COLUMN SUBJECTS.SUB_NAME IS 'Subject name (unique)';
COMMENT ON COLUMN SUBJECTS.SUB_DESCRIPTION IS 'Subject description';
COMMENT ON COLUMN SUBJECTS.SUB_TEACHER_ID IS 'Subject teacher (users foreign key - only teachers)';
COMMENT ON COLUMN SUBJECTS.SUB_ECTS IS 'Subject ects points';

CREATE TABLE TOPICS(
TOP_ID INT PRIMARY KEY NOT NULL,
TOP_NAME VARCHAR(255) NOT NULL,
TOP_DESCRIPTION VARCHAR(255),
TOP_SUB_ID INT REFERENCES SUBJECTS (SUB_ID) NOT NULL,
TOP_LIMIT INT DEFAULT 1 NOT NULL,
TOP_CREATION_DATE TIMESTAMP DEFAULT now() NOT NULL,
TOP_DEADLINE_DATE TIMESTAMP,
CONSTRAINT TOP_NAME_UNIQUE UNIQUE (TOP_NAME)
);

COMMENT ON COLUMN TOPICS.TOP_ID IS 'Topic ID (primary key)';
COMMENT ON COLUMN TOPICS.TOP_NAME IS 'Topic name (unique)';
COMMENT ON COLUMN TOPICS.TOP_DESCRIPTION IS 'Topic description';
COMMENT ON COLUMN TOPICS.TOP_SUB_ID IS 'Topic associated subject (subjects foreign key)';
COMMENT ON COLUMN TOPICS.TOP_LIMIT IS 'Topic students limit (default 1)';
COMMENT ON COLUMN TOPICS.TOP_CREATION_DATE IS 'Topic creation date';
COMMENT ON COLUMN TOPICS.TOP_DEADLINE_DATE IS 'Topic deadline date';

CREATE TABLE TOPIC_RESERVATIONS(
TRS_ID INT PRIMARY KEY NOT NULL,
TRS_TOP_ID INT REFERENCES TOPICS (TOP_ID) NOT NULL,
TRS_USR_ID INT REFERENCES USERS (USR_ID) NOT NULL,
TRS_RESERVATION_DATE TIMESTAMP NOT NULL
);

COMMENT ON COLUMN TOPIC_RESERVATIONS.TRS_ID IS 'Topic reservation ID (primary key)';
COMMENT ON COLUMN TOPIC_RESERVATIONS.TRS_TOP_ID IS 'Topic reservation associated topic (topics foreign key)';
COMMENT ON COLUMN TOPIC_RESERVATIONS.TRS_USR_ID IS 'Topic reservation associated user (users foreign key - only students)';
COMMENT ON COLUMN TOPIC_RESERVATIONS.TRS_RESERVATION_DATE IS 'Topic reservation date';

COMMIT;