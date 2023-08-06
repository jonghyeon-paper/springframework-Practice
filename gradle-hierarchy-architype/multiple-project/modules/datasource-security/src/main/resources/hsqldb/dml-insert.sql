INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('scream', 'qwer', 'scream', 36);
INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('green7', 'qwer', 'greennn', 21);
INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('tester1', '1234', 't', 28);
INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('tester2', '1234', 'tt', 28);
INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('tester3', '1234', 'ttt', 28);
INSERT INTO MEMBER(MEMBER_ID, PASSWORD, NAME, AGE) VALUES ('tester4', '1234', 't4', 28);


INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'ARCICLE');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'SEARCHING');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'RECORD_READ');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'RECORD_WRITE');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'STATIC');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('scream', 'OTHERS');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('green7', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester1', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester1', 'RECORD_READ');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester2', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester2', 'RECORD_WRITE');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester3', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester3', 'RECORD_READ');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester3', 'RECORD_WRITE');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester4', 'PERSONAL');
INSERT INTO MEMBERS_AUTHORITY(MEMBER_ID, AUTHORITY_ID) VALUES ('tester4', 'STATS');


INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('PERSONAL', '개인권한', true);
INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('ARCICLE', '기사', true);
INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('RECORD_READ', '기록(읽기)', true);
INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('RECORD_WRITE', '기록(쓰기)', true);
INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('STATS', '통계', true);
INSERT INTO AUTHORITY(AUTHORITY_ID, NAME, ENABLED) VALUES ('OTHERS', '기타', true);


INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 100);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 101);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 102);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 103);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 104);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 105);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 106);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('PERSONAL', 107);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 200);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 201);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 202);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 203);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 204);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 205);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('ARCICLE', 206);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 300);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 301);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 302);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 303);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 304);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_READ', 305);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('RECORD_WRITE', 306);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 400);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 401);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 402);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 403);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 201);
INSERT INTO AUTHORITYS_FUNCTION(AUTHORITY_ID, FUNCTION_ID) VALUES ('STATS', 304);


INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (100, null, 'accessable-menu', 'API', '/accessable-menu', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (101, null, 'member', 'UI', null, null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (102, 101, 'general-info', 'UI', '/member/general-info', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (103, 101, 'security-info', 'UI', '/member/security-info', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (104, null, 'general-info-read', 'API', '/member/general-info', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (105, null, 'general-info-patch', 'API', '/member/general-info', 'PATCH', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (106, null, 'security-info-read', 'API', '/member/security-info', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (107, null, 'security-info-oatch', 'API', '/member/security-info', 'PATCH', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (200, null, 'article', 'UI', '/article', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (201, null, 'article-read', 'API', '/article', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (202, null, 'article-read2', 'API', '/article/{identifier}', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (203, null, 'article-write', 'API', '/article', 'POST', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (204, null, 'article-patch', 'API', '/article/{identifier}', 'PUT', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (205, null, 'article-patch2', 'API', '/article/{identifier}', 'PATCH', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (206, null, 'article-remove', 'API', '/article/{identifier}', 'DELETE', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (300, null, 'record', 'UI', null, null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (301, 300, 'record-alpha', 'UI', '/record/alpha', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (302, 300, 'record-beta', 'UI', '/record/beta', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (303, 300, 'record-gamma', 'UI', '/record/gamma', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (304, null, 'record-read', 'API', '/record', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (305, null, 'record-read2', 'API', '/record/{identifier}', 'GET', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (306, null, 'record-write', 'API', '/record', 'POST', true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (400, null, 'statistics', 'UI', null, null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (401, 400, 'statistics-month', 'UI', '/statistics-month', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (402, 400, 'statistics-year', 'UI', '/statistics-year', null, true);
INSERT INTO FUNCTION(FUNCTION_ID, PARENT_FUNCTION_ID, NAME, TYPE, PATH, METHOD, ENABLED) VALUES (403, null, 'statistics-read', 'API', '/statistics', 'GET', true);