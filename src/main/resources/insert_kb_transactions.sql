-- KB Bank Transaction History (Account ID: 1) --

BEGIN;
INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250921', '040558', '출금', 'FBS 출금', '토스페이', 1251, 70749, '임베디', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250920', '042119', '출금', '체크카드', '카카오T일반택시(법', 28000, 72000, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250920', '035201', '입금', '체크카드', '카카오T택시_가승인', 27600, 100000, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250920', '035157', '출금', '체크카드', '카카오T택시_가승인', 27600, 72400, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250916', '141951', '출금', '오픈뱅킹출금', '토뱅 윤도훈', 185630, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250916', '013839', '출금', '체크카드', '카카오T일반택시_0', 23000, 285630, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250916', '011513', '입금', '체크카드', '카카오T택시_가승인', 23700, 308630, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250916', '011508', '출금', '체크카드', '카카오T택시_가승인', 23700, 284930, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250915', '194925', '출금', '지로출금', 'ＳＫＴ３４８１', 82010, 308630, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250914', '021535', '출금', '체크카드', 'Netflix.com', 13500, 390640, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250913', '234304', '출금', '체크카드', 'NICE결제대행', 1500, 404140, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250912', '224659', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 405640, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250912', '204631', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 505640, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250911', '210124', '출금', '오픈뱅킹출금', '토스 윤도훈', 200000, 605640, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250911', '123619', '출금', '오픈뱅킹출금', '토스 이관형', 30000, 805640, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250911', '111131', '출금', '오픈뱅킹출금', '토스페이', 26900, 835640, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250910', '103031', '입금', 'TOP 급여', '8월일반근로장학', 732190, 862540, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250910', '094024', '입금', '체크카드', '구글플레이', 1000, 130350, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250910', '094011', '출금', '체크카드', '구글플레이', 1000, 129350, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250908', '200910', '출금', '오픈뱅킹출금', '토스 조은영(', 30000, 130350, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250908', '151644', '출금', '오픈뱅킹출금', '토스 윤도훈', 30000, 160350, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250908', '103854', '출금', '오픈뱅킹출금', '토스페이', 14900, 190350, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250906', '040242', '출금', '체크카드', '카카오T일반택시_0', 30800, 205250, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250906', '040240', '출금', '체크카드', '카카오T일반택시(법', 3000, 236050, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250906', '032820', '입금', '체크카드', '카카오T택시_가승인', 33200, 239050, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250906', '032815', '출금', '체크카드', '카카오T택시_가승인', 33200, 205850, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250905', '125855', '입금', '스마트입금', '윤도훈(에스디상사)', 200000, 239050, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250903', '190835', '출금', '국민카드', 'KB카드출금', 60950, 39050, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250901', '150810', '출금', '오픈뱅킹출금', '토스 윤도훈', 27430, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250830', '204607', '출금', '오픈뱅킹출금', '토스 송민석', 63000, 127430, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250830', '050843', '출금', '체크카드', '카카오T일반택시(법', 3000, 190430, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250830', '050841', '출금', '체크카드', '카카오T일반택시(법', 29800, 193430, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250830', '042748', '입금', '체크카드', '카카오T택시_가승인', 31900, 223230, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250830', '042743', '출금', '체크카드', '카카오T택시_가승인', 31900, 191330, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250829', '103456', '입금', '카드입금', 'K-패스 환급금', 23230, 223230, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250824', '214156', '출금', '오픈뱅킹출금', '토스 윤도훈', 164600, 200000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250824', '214126', '출금', '오픈뱅킹출금', '토스 정서윤', 35400, 364600, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250821', '155807', '출금', '오픈뱅킹출금', '토스 윤도훈', 123840, 400000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250821', '040856', '출금', '체크카드', 'APPLE', 3300, 523840, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250821', '002416', '출금', '체크카드', 'NICE결제대행', 1750, 527140, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250820', '165159', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 528890, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250818', '194927', '출금', '지로출금', 'ＳＫＴ３４８１', 101110, 628890, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250818', '173821', '출금', '오픈뱅킹출금', '토스 윤도훈', 44800, 730000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250817', '121344', '입금', '스마트입금', '윤도훈(에스디상사)', 30000, 774800, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250815', '171116', '출금', '체크카드', '카카오_택시_0', 34700, 744800, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250815', '160444', '입금', '체크카드', '카카오_택시_선승인', 30500, 779500, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250815', '160439', '출금', '체크카드', '카카오_택시_선승인', 30500, 749000, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250814', '005548', '출금', '체크카드', 'Netflix.com', 13500, 779500, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250814', '000732', '출금', '체크카드', '지에스(GS)25장현스', 700, 793000, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250814', '000609', '출금', '체크카드', '지에스(GS)25장현스', 6300, 793700, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250813', '220041', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 800000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250813', '112523', '출금', '오픈뱅킹출금', '토스 윤도훈', 63746, 900000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250812', '182413', '출금', '국민카드', 'KB카드출금', 18600, 963746, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '153428', '출금', '오픈뱅킹출금', '토스 민도현', 150000, 982346, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '110257', '입금', 'TOP 급여', '7월일반근로장학', 842520, 1132346, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '021254', '출금', '체크카드', '카카오T일반택시_0', 17900, 289826, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '014820', '입금', '체크카드', '카카오T택시_가승인', 15600, 307726, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '014816', '출금', '체크카드', '카카오T택시_가승인', 15600, 292126, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250811', '014737', '출금', '오픈뱅킹출금', '토스 윤도훈', 40000, 307726, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250810', '165434', '출금', '오픈뱅킹출금', '토스 윤도훈', 20000, 347726, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250809', '020139', '입금', '결산이자', '이자세금:0원', 56, 367726, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250808', '222324', '출금', '오픈뱅킹출금', '토스 윤도훈', 40000, 367670, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250808', '103856', '출금', '오픈뱅킹출금', '토스페이', 14900, 407670, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250805', '214040', '입금', '스마트입금', '윤도훈(에스디상사)', 200000, 422570, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250805', '191950', '출금', '국민카드', 'KB카드출금', 77430, 222570, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250804', '102020', '출금', '오픈뱅킹출금', '토스 윤도훈', 45038, 300000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250731', '105720', '입금', '카드입금', 'K-패스 환급금', 24190, 345038, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250730', '183414', '출금', '체크카드', '리그PC방', 5000, 320848, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250729', '090303', '출금', '체크카드', 'NICE결제대행', 1500, 325848, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250725', '201414', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 327348, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250722', '083055', '입금', '대체입금', '윤도훈 07279900017652', 330648, 427348, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250721', '040533', '출금', '체크카드', 'APPLE', 3300, 96700, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250717', '224624', '출금', '오픈뱅킹출금', '토스 윤도훈', 154380, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250717', '224519', '출금', '오픈뱅킹출금', '토스 (사)', 12000, 254380, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250715', '194755', '출금', '지로출금', 'ＳＫＴ３４８１', 101110, 266380, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250714', '122606', '입금', '대체입금', '윤도훈', 243080, 367490, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250714', '022818', '출금', '체크카드', 'Netflix.com', 13500, 124410, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250712', '113317', '출금', '오픈뱅킹출금', '토스 윤도훈', 381740, 137910, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250712', '035153', '출금', '오픈뱅킹출금', '토뱅 윤도훈', 40000, 519650, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250712', '023816', '출금', 'FBS 출금', '윤도훈', 50000, 559650, '임베디', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250712', '002102', '출금', '오픈뱅킹출금', '토스 윤도훈', 10000, 609650, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250710', '121856', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 619650, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250710', '120905', '입금', 'TOP 급여', '6월일반근로장학', 581740, 719650, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250709', '081603', '입금', '전자금융', '윤도훈', 37910, 137910, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250708', '204846', '출금', '오픈뱅킹출금', '토스 윤도훈', 37910, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250708', '103856', '출금', '오픈뱅킹출금', '토스페이', 14900, 137910, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250705', '161421', '출금', '오픈뱅킹출금', '토스 윤도훈', 47190, 152810, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250705', '160707', '입금', '스마트입금', '윤도훈(에스디상사)', 200000, 200000, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250704', '145511', '출금', '오픈뱅킹출금', '토스 윤도훈', 21610, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250704', '122218', '입금', '스마트입금', '윤도훈(에스디상사)', 20000, 21610, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250703', '185911', '출금', '국민카드', 'KB카드출금', 80620, 1610, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250702', '173836', '출금', '오픈뱅킹출금', '토스 윤도훈', 16000, 82230, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250702', '163606', '입금', '전자금융', '7.2기본중식', 8000, 98230, '농협', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250702', '163130', '입금', '전자금융', '7.2기본교통', 8000, 90230, '농협', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250630', '085214', '출금', '체크카드', 'NICE결제대행', 1680, 82230, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250630', '010649', '입금', '전자금융', '윤도훈', 20000, 83910, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250629', '230409', '출금', '오픈뱅킹출금', '토스 윤도훈', 20000, 63910, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250629', '183759', '출금', '오픈뱅킹출금', '토스 윤도훈', 20000, 83910, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250628', '223903', '입금', '카드입금', 'K-패스 환급금', 20210, 103910, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250625', '075037', '출금', '체크카드', 'NICE결제대행', 1680, 83700, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250621', '040546', '출금', '체크카드', 'APPLE', 3300, 85380, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250619', '095520', '출금', '체크카드', 'NICE결제대행', 1320, 88680, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250618', '133139', '출금', '오픈뱅킹출금', '토스 윤도훈', 166940, 90000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250618', '085454', '출금', '체크카드', 'NICE결제대행', 1500, 256940, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250616', '195049', '출금', '지로출금', '０１０６３６１３', 101110, 258440, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250614', '004516', '출금', '체크카드', 'Netflix.com', 13500, 359550, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250612', '200414', '출금', '기일출금', '072707**52 ,2506-12회차', 20000, 373050, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250612', '090004', '출금', '체크카드', 'NICE결제대행', 1680, 393050, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250611', '003028', '출금', '체크카드', 'NICE결제대행', 1900, 394730, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250610', '182748', '출금', '오픈뱅킹출금', '토스 윤도훈', 300000, 396630, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250610', '095027', '입금', 'TOP 급여', '5월일반근로장학', 511530, 696630, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250608', '103854', '출금', '오픈뱅킹출금', '토스페이', 14900, 185100, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250607', '143247', '출금', '오픈뱅킹출금', '토스 윤도훈', 47361, 200000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250607', '111350', '출금', '체크카드', 'NICE결제대행', 1320, 247361, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250607', '000113', '출금', '체크카드', '지에스(GS)25장현스', 1500, 248681, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250606', '235549', '출금', '체크카드', 'NICE결제대행', 1680, 250181, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250606', '143609', '입금', '스마트입금', '윤도훈(에스디상사)', 200000, 251861, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250605', '191038', '출금', '국민카드', 'KB카드출금', 67340, 51861, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250530', '100514', '입금', '카드입금', 'K-패스 환급금', 25860, 119201, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250527', '120323', '출금', '체크카드', 'NICE결제대행', 1680, 93341, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250524', '142636', '입금', '전자금융', '빗썸258', 1, 95021, '농협', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250521', '040535', '출금', '체크카드', 'APPLE', 3300, 95020, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250519', '125705', '출금', '체크카드', 'NICE결제대행', 1680, 98320, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250516', '174757', '출금', '오픈뱅킹출금', '토스 윤도훈', 559006, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250515', '195903', '출금', '지로출금', '０１０６３６１３', 101110, 659006, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250514', '012046', '출금', '체크카드', 'Netflix.com', 13500, 760116, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250512', '210659', '출금', '기일출금', '072707**52 ,2505-11회차', 20000, 773616, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250512', '142423', '입금', 'TOP 급여', '4월일반근로장학', 621860, 793616, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250510', '021313', '입금', '결산이자', '이자세금:0원', 66, 171756, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250509', '092111', '입금', '체크카드', '쿠팡(와우멤버십)', 7890, 171690, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '184700', '출금', '국민카드', 'KB카드출금', 86200, 163800, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '135245', '출금', '오픈뱅킹출금', '토스 윤도훈', 97680, 250000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '135056', '입금', '스마트입금', '윤도훈(에스디상사)', 250000, 347680, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '103856', '출금', '오픈뱅킹출금', '토스페이', 14900, 97680, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '100618', '출금', '체크카드', '쿠팡(와우멤버십)', 7890, 112580, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250430', '101747', '입금', '카드입금', 'K-패스 환급금', 26840, 120470, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250430', '002517', '출금', '체크카드', 'NICE결제대행', 1750, 93630, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250424', '075521', '출금', '체크카드', 'NICE결제대행', 1320, 95380, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250421', '040537', '출금', '체크카드', 'APPLE', 3300, 96700, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250417', '111840', '출금', '오픈뱅킹출금', '토스 윤도훈', 466451, 100000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250416', '090252', '출금', '체크카드', 'NICE결제대행', 1500, 566451, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250415', '195742', '출금', '지로출금', '０１０６３６１３', 101110, 567951, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250415', '125454', '출금', '체크카드', '도서출판글로세움', 1750, 669061, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250415', '125241', '출금', '체크카드', '도서출판글로세움', 250, 670811, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250414', '201140', '출금', '기일출금', '072707**52 ,2504-10회차', 20000, 671061, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250414', '165418', '입금', '스마트입금', '윤도훈(에스디상사)', 24000, 691061, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250414', '021654', '출금', '체크카드', 'Netflix.com', 13500, 667061, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250410', '144438', '입금', 'TOP 일반', '3월일반근로장학', 521560, 680561, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250410', '085724', '출금', '체크카드', 'NICE결제대행', 1860, 159001, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250408', '103855', '출금', '오픈뱅킹출금', '토스페이', 14900, 160861, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250408', '100708', '출금', '체크카드', '쿠팡(와우멤버십)', 7890, 175761, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250404', '212437', '입금', '스마트입금', '윤도훈(에스디상사)', 150000, 183651, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250403', '185905', '출금', '국민카드', 'KB카드출금', 89450, 33651, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250331', '165526', '입금', '전자금융', '윤도훈', 92711, 123101, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250329', '221637', '입금', '카드입금', 'K-패스 환급금', 18920, 30390, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250329', '102406', '출금', '체크카드', 'NICE결제대행', 1680, 11470, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250321', '040606', '출금', '체크카드', 'APPLE', 3300, 13150, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250320', '002212', '출금', '체크카드', 'NICE결제대행', 1750, 16450, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250317', '195333', '출금', '지로출금', '01063613481SKT', 101110, 18200, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250314', '020059', '출금', '체크카드', 'Netflix.com', 13500, 119310, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250312', '200457', '출금', '기일출금', '072707**52 ,2503- 9회차', 20000, 132810, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250312', '181148', '출금', '국민카드', 'KB카드출금', 14900, 152810, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250312', '015611', '입금', '전자금융', '윤도훈', 14900, 167710, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '223927', '출금', '체크카드', '쿠팡(쿠페이)', 151590, 152810, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '223907', '입금', '전자금융', '윤도훈', 151590, 304400, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '223507', '입금', '전자금융', '윤도훈', 90, 152810, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '223449', '입금', '전자금융', '윤도훈', 7800, 152720, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '223347', '출금', '체크카드', '쿠팡(와우멤버십)', 7890, 144920, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '222939', '출금', '오픈뱅킹출금', '토스 윤도훈', 779427, 152810, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '222856', '입금', '전자금융', '윤도훈', 19847, 932237, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '143713', '입금', '스마트입금', '윤도훈(에스디상사)', 100000, 912390, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250308', '103854', '출금', '체크카드', '구글플레이', 14900, 812390, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250306', '184209', '출금', '국민카드', 'KB카드출금', 63050, 827290, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250306', '121454', '입금', '전자금융', '윤도훈', 100000, 890340, '카뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250228', '100146', '입금', '카드입금', 'K-패스 환급금', 22830, 790340, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250227', '075702', '출금', '체크카드', 'NICE결제대행', 1500, 767510, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250221', '050534', '출금', '체크카드', 'APPLE', 3300, 769010, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250220', '113449', '입금', 'TOP 일반', '2월일반근로장학', 772310, 772310, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250218', '124122', '출금', '오픈뱅킹출금', '토스 윤도훈', 477354, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250217', '210017', '출금', '체크카드', 'APPLE', 2500, 477354, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250217', '195720', '출금', '지로출금', '01063613481SKT', 101110, 479854, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250215', '020859', '입금', '결산이자', '이자세금:0원', 44, 580964, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250214', '021331', '출금', '체크카드', 'Netflix.com', 13500, 580920, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250212', '200026', '출금', '기일출금', '072707**52 ,2502- 8회차', 20000, 594420, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250211', '093841', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 614420, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250210', '112719', '입금', 'TOP 급여', '1월일반근로장학', 611830, 714420, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250208', '124711', '입금', '스마트입금', '윤도훈(에스디상사)', 100000, 102590, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250205', '185457', '출금', '국민카드', 'KB카드출금', 76100, 2590, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250202', '234646', '출금', '체크카드', 'NICE결제대행', 1320, 78690, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250125', '221654', '입금', '카드입금', 'K-패스 환급금', 22220, 80010, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250122', '184440', '출금', '오픈뱅킹출금', '토스 윤도훈', 400000, 57790, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250122', '091015', '출금', '체크카드', 'NICE결제대행', 1500, 457790, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250121', '050540', '출금', '체크카드', 'APPLE', 3300, 459290, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250120', '093525', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 462590, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250117', '214650', '출금', '오픈뱅킹출금', '토스 김은별', 43000, 562590, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250116', '095001', '출금', '체크카드', 'NICE결제대행', 1680, 605590, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250115', '195540', '출금', '지로출금', '01063613481SKT', 101110, 607270, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250114', '024058', '출금', '체크카드', 'Netflix.com', 13500, 708380, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250113', '200257', '출금', '기일출금', '072707**52 ,2501- 7회차', 20000, 721880, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250113', '185438', '출금', '오픈뱅킹출금', '토스 윤도훈', 100000, 741880, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250113', '085531', '출금', '체크카드', 'NICE결제대행', 1500, 841880, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250112', '191533', '입금', '스마트입금', '윤도훈(에스디상사)', 30000, 843380, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250110', '191031', '출금', '오픈뱅킹출금', '토스 이봉희', 30000, 813380, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250110', '122058', '입금', 'TOP 급여', '12월일반근로', 650760, 843380, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250108', '103855', '출금', '체크카드', '구글플레이', 14900, 192620, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250107', '151444', '입금', '스마트입금', '윤도훈(에스디상사)', 100000, 207520, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250106', '191043', '출금', '국민카드', 'KB카드출금', 74050, 107520, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250103', '182019', '입금', '전자금융', '윤도훈', 100000, 181570, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250101', '161831', '입금', '전자금융', '윤도훈', 10000, 81570, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241231', '095327', '입금', '카드입금', 'K-패스 환급금', 29360, 71570, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241224', '101148', '입금', '체크카드', 'NICE결제대행', 1140, 42210, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241224', '101111', '입금', '체크카드', 'NICE결제대행', 1140, 41070, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241223', '234800', '출금', '체크카드', 'NICE결제대행', 1680, 39930, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241223', '234104', '출금', '체크카드', 'NICE결제대행', 1140, 41610, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241223', '233738', '출금', '체크카드', 'NICE결제대행', 1140, 42750, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241221', '050553', '출금', '체크카드', 'APPLE', 3300, 43890, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241216', '195852', '출금', '지로출금', '01063613481SKT', 101110, 47190, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241214', '030705', '출금', '체크카드', 'NETFLIX_INICIS', 13500, 148300, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241212', '200013', '출금', '기일출금', '072707**52 ,2412- 6회차', 20000, 161800, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241212', '181100', '출금', '국민카드', 'KB카드출금', 18200, 181800, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241210', '102037', '입금', '전자금융', '윤도훈', 200000, 200000, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241210', '102014', '출금', '오픈뱅킹출금', '토스 윤도훈', 746580, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241210', '101914', '입금', 'TOP 급여', '11월일반근로', 660620, 746580, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241208', '103854', '출금', '체크카드', '구글플레이', 14900, 85960, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241205', '172450', '입금', '스마트입금', '윤도훈(에스디상사)', 100000, 100860, '시흥능', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241204', '185500', '출금', '국민카드', 'KB카드출금', 97850, 860, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241204', '124646', '입금', '전자금융', '윤도훈', 3000, 98710, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241202', '074631', '입금', '전자금융', '윤도훈', 70000, 95710, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241129', '094522', '입금', '카드입금', 'K-패스 환급금', 25710, 25710, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241116', '192743', '출금', '오픈뱅킹출금', '토스 윤도훈', 12501, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241115', '195934', '출금', '지로출금', '01063613481SKT', 101110, 12501, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241115', '112927', '입금', '전자금융', '빗썸313', 1, 113611, '농협', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241114', '084621', '입금', '전자금융', '윤도훈', 30000, 113610, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241114', '021908', '출금', '체크카드', 'NETFLIX_INICIS', 13500, 83610, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241113', '155332', '입금', '전자금융', '11.13기본훈', 16000, 97110, '농협', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241112', '200447', '출금', '기일출금', '072707**52 ,2411- 5회차', 20000, 81110, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241111', '203504', '출금', '오픈뱅킹출금', '토스 윤도훈', 440803, 101110, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241111', '203214', '출금', '오픈뱅킹출금', '토스 윤도훈', 70000, 541913, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241111', '103507', '입금', 'TOP 급여', '10월일반근로', 611320, 611913, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241111', '075727', '출금', '체크카드', 'NICE결제대행', 1680, 593, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241111', '075721', '입금', '전자금융', '윤도훈', 2000, 2273, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241109', '020217', '입금', '결산이자', '이자세금:0원', 23, 273, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241107', '193359', '출금', '체크카드', 'NICE결제대행', 1750, 250, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241107', '193348', '입금', '전자금융', '윤도훈', 2000, 2000, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241106', '234313', '출금', '오픈뱅킹출금', '토스 윤도훈', 600, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241105', '190935', '출금', '국민카드', 'KB카드출금', 85700, 600, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241104', '120406', '입금', '전자금융', '윤도훈', 600, 86300, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241031', '191830', '입금', '전자금융', '윤도훈', 59810, 85700, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241031', '094602', '입금', '카드입금', 'K-패스 환급금', 25890, 25890, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241031', '090049', '출금', 'FBS 출금', '윤도훈', 140, 0, '임베디', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241031', '075055', '출금', '체크카드', 'NICE결제대행', 1860, 140, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241031', '074327', '입금', '전자금융', '윤도훈', 2000, 2000, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241029', '155729', '출금', 'FBS 출금', '윤도훈', 2850, 0, '임베디', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241027', '135217', '출금', '오픈뱅킹출금', 'KB카드출금', 2350, 2850, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241026', '012004', '입금', '전자금융', '윤도훈', 5200, 5200, '토스뱅크', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241023', '202221', '출금', '오픈뱅킹출금', '토스 윤도훈', 500, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241021', '203312', '출금', '오픈뱅킹출금', '토스 윤도훈(', 10000, 500, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241021', '203002', '입금', '전자금융', '윤도훈', 10000, 10500, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241021', '074555', '출금', '체크카드', 'NICE결제대행', 1500, 500, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241021', '073917', '입금', '전자금융', '윤도훈', 2000, 2000, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241017', '074855', '출금', '체크카드', 'NICE결제대행', 1500, 0, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241017', '074850', '입금', '전자금융', '윤도훈', 1500, 1500, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241016', '090550', '출금', '오픈뱅킹출금', '토스 윤도훈', 59080, 0, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241016', '002751', '출금', '체크카드', 'NICE결제대행', 1450, 59080, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241015', '195706', '출금', '지로출금', '01063613481SKT', 101110, 60530, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241015', '133150', '출금', '체크카드', 'NICE결제대행', 1140, 161640, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241014', '200755', '출금', '기일출금', '072707**52 ,2410- 4회차', 20000, 162780, '신정네', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241014', '020445', '출금', '체크카드', 'NETFLIX_INICIS', 13500, 182780, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241011', '084918', '출금', '체크카드', 'NICE결제대행', 2040, 196280, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241010', '233758', '출금', '체크카드', 'NICE결제대행', 1680, 198320, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241010', '125426', '출금', '오픈뱅킹출금', '토스 윤도훈', 514540, 200000, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241010', '124740', '출금', '오픈뱅킹출금', '토뱅 윤도훈', 38000, 714540, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241010', '101309', '입금', 'TOP 급여', '9월일반근로', 631040, 752540, '동양점', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241009', '131938', '출금', '오픈뱅킹출금', '토스 윤도훈', 10000, 121500, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241009', '021246', '입금', '전자금융', '윤도훈', 130000, 131500, '하나은행', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241009', '015005', '출금', '오픈뱅킹출금', '토뱅 윤도훈', 20000, 1500, '스타뱅', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241008', '103856', '출금', '체크카드', '구글플레이', 14900, 21500, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241007', '192243', '출금', '국민카드', 'KB카드출금', 86300, 36400, '수신상', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20241003', '223443', '출금', '체크카드', '코레일유통주식회사', 400, 122700, 'KB카드', 1);

INSERT INTO mock_transactions
(tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20240928', '220906', '입금', '카드입금', 'K-패스 환급금', 23100, 123100, '수신상', 1);

-- ========== 디즈니 플러스 (매월 약 20일) ==========
INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250920', '110512', '출금', '해외승인', 'DisneyPlus', 13900, 377140, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250820', '105501', '출금', '해외승인', 'DisneyPlus', 13900, 470300, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250720', '111033', '출금', '해외승인', 'DisneyPlus', 13900, 550450, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250620', '104950', '출금', '해외승인', 'DisneyPlus', 13900, 605100, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250520', '110215', '출금', '해외승인', 'DisneyPlus', 13900, 710200, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250420', '105822', '출금', '해외승인', 'DisneyPlus', 13900, 871000, 'KB카드', 1);


-- ========== 유튜브 프리미엄 (매월 약 8일) ==========
INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250908', '082015', '출금', '해외승인', 'YouTubePremium', 14900, 366690, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250808', '081533', '출금', '해외승인', 'YouTubePremium', 14900, 459850, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250708', '090102', '출금', '해외승인', 'YouTubePremium', 14900, 540000, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250608', '082240', '출금', '해외승인', 'YouTubePremium', 14900, 594650, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250508', '081918', '출금', '해외승인', 'YouTubePremium', 14900, 700000, 'KB카드', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250408', '082155', '출금', '해외승인', 'YouTubePremium', 14900, 860550, 'KB카드', 1);


-- ========== 쿠팡 와우 멤버십 (매월 약 1일) ==========
INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250901', '041020', '출금', '자동이체', '쿠팡와우월회비', 7890, 361700, '자동납부', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250801', '040930', '출금', '자동이체', '쿠팡와우월회비', 7890, 454860, '자동납부', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250701', '050111', '출금', '자동이체', '쿠팡와우월회비', 7890, 535010, '자동납부', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250601', '041522', '출금', '자동이체', '쿠팡와우월회비', 7890, 589660, '자동납부', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250501', '041105', '출금', '자동이체', '쿠팡와우월회비', 7890, 695010, '자동납부', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250401', '041348', '출금', '자동이체', '쿠팡와우월회비', 7890, 855560, '자동납부', 1);

-- ========== 자유적금 (매월 10일 입금) ==========
INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250910', '090500', '입금', '자동이체', '자유적금', 100000, 466690, '계좌이체', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250810', '090215', '입금', '자동이체', '자유적금', 100000, 559850, '계좌이체', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250710', '090330', '입금', '자동이체', '자유적금', 100000, 640000, '계좌이체', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250610', '090145', '입금', '자동이체', '자유적금', 100000, 694650, '계좌이체', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250510', '090401', '입금', '자동이체', '자유적금', 100000, 800000, '계좌이체', 1);

INSERT INTO mock_transactions (tran_date, tran_time, inout_type, tran_type, print_content, tran_amt, after_balance_amt, branch_name, account_id)
VALUES ('20250410', '090255', '입금', '자동이체', '자유적금', 100000, 960550, '계좌이체', 1);

COMMIT;