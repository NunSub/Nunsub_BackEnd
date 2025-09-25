-- 1. 은행 데이터 먼저 삽입
INSERT INTO banks (bank_code_std, bank_name) VALUES ('097', '오픈은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('088', '신한은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('002', 'KDB산업은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('003', 'IBK기업은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('004', 'KB국민은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('007', '수협은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('011', 'NH농협은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('020', '우리은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('023', 'SC제일은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('027', '한국씨티은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('031', '아이엠뱅크');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('032', '부산은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('034', '광주은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('035', '제주은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('037', '전북은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('039', '경남은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('081', '하나은행');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('089', '케이뱅크');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('090', '카카오뱅크');
INSERT INTO banks (bank_code_std, bank_name) VALUES ('092', '토스뱅크');

-- 2. 사용자 데이터 삽입
INSERT INTO mock_users (user_seq_no, ci, user_name, user_info, user_gender, user_cell_no, user_email)
VALUES
    ('1000000106', 'MOCK_CI_1234567890', '윤도훈', '20010203', 'M', '01063613481', 'ehgns79513@gmail.com'),
    ('1000000107', 'MOCK_CI_2345678901', '육나윤', '20040202', 'F', '01012345602', 'yuknayoon@example.com'),
    ('1000000108', 'MOCK_CI_3456789012', '이예원', '20040303', 'F', '01012345603', 'leeyewon@example.com'),
    ('1000000109', 'MOCK_CI_4567890123', '이준상', '20020404', 'M', '01012345604', 'leejoonsang@example.com');

-- --------------------------
-- Mock Accounts
-- --------------------------
-- 윤도훈(user_id: 1) Accounts
INSERT INTO mock_accounts (user_id, fintech_use_num, account_alias, account_num_masked, balance, bank_code_std, account_holder_name, account_type, inquiry_agree_yn, transfer_agree_yn)
VALUES
    (1, '123456789012345678901111', 'KB주거래통장', '111-222-***', 1250000, '004', '윤도훈', '1', 'Y', 'Y'),
    (1, '123456789012345678902222', '카카오세이프박스', '222-333-***', 5000000, '090', '윤도훈', '1', 'Y', 'Y'),
    (1, '123456789012345678903333', '신한용돈계좌', '333-444-***', 300000, '088', '윤도훈', '1', 'Y', 'Y'),
    (1, '123456789012345678904444', '토스생활비', '444-555-***', 750000, '092', '윤도훈', '1', 'Y', 'Y');

-- 육나윤(user_id: 2) Accounts
INSERT INTO mock_accounts (user_id, fintech_use_num, account_alias, account_num_masked, balance, bank_code_std, account_holder_name, account_type, inquiry_agree_yn, transfer_agree_yn)
VALUES
    (2, '234567890123456789011111', '우리월급통장', '555-666-***', 2800000, '020', '육나윤', '1', 'Y', 'Y'),
    (2, '234567890123456789022222', '하나적금', '666-777-***', 10000000, '081', '육나윤', '2', 'Y', 'N'),
    (2, '234567890123456789033333', '농협비상금', '777-888-***', 500000, '011', '육나윤', '1', 'Y', 'Y'),
    (2, '234567890123456789044444', '케이뱅크투자', '888-999-***', 1500000, '089', '육나윤', '6', 'Y', 'Y');

-- 이예원(user_id: 3) Accounts
INSERT INTO mock_accounts (user_id, fintech_use_num, account_alias, account_num_masked, balance, bank_code_std, account_holder_name, account_type, inquiry_agree_yn, transfer_agree_yn)
VALUES
    (3, '34567890123456789011111', 'IBK사업자통장', '999-000-***', 15000000, '003', '이예원', '1', 'Y', 'Y'),
    (3, '34567890123456789022222', '부산은행여행자금', '000-111-***', 2000000, '032', '이예원', '1', 'Y', 'Y'),
    (3, '34567890123456789033333', 'SC외화통장', '111-222-***', 850000, '023', '이예원', '1', 'Y', 'Y');

-- 이준상(user_id: 4) Accounts
INSERT INTO mock_accounts (user_id, fintech_use_num, account_alias, account_num_masked, balance, bank_code_std, account_holder_name, account_type, inquiry_agree_yn, transfer_agree_yn)
VALUES
    (4, '4567890123456789011111', '산업은행예금', '222-333-***', 20000000, '002', '이준상', '2', 'Y', 'N'),
    (4, '4567890123456789022222', '신한주식계좌', '333-444-***', 5500000, '088', '이준상', '6', 'Y', 'Y'),
    (4, '4567890123456789033333', '토스파킹통장', '444-555-***', 3000000, '092', '이준상', '1', 'Y', 'Y');
