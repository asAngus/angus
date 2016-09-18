--查下短信验证码码
select * from URMTSMLG where mbl_no='18820869093' order by send_date desc ;
update URMTSMLG set send_num='0' where mbl_no='18684506150';
select * from URMTSMLG where mbl_no='15652940345' order by send_date desc ;

--富国产品信息表
select * from FUNTPUBINF;
--定期理财
select * from cmmtpro where prd_id='JXBANK201609071';
--招商基金,天天基金
select * from fundtzlinf;

--基金理财
select * from fundtzlinf where (tx_dt,fund_code) in (select max(tx_dt),fund_code from fundtzlinf group by fund_code);
update fundtzlinf set tx_dt='20160412' where tx_dt='20160405';
update fundtzlinf set to_char(sysdate,'yyyymmdd')  where (tx_dt,fund_code) in (select max(tx_dt),fund_code from fundtzlinf group by fund_code);

--富国基金,基金报错跟新
select * from FUNTPUBINF  where (tx_dt,fund_cd) in (select max(upd_dt),fund_cd from FUNTPUBINF group by fund_cd);
select max(upd_dt) from funtpubinf where fund_cd = '000638';
update funtpubinf set upd_dt=to_char(sysdate,'yyyymmdd') where fund_cd = '000638' and upd_dt='20160622';
update funtpubinf set TX_DT=to_char(sysdate,'yyyymmdd') where (tx_dt,fund_cd) in (select max(TX_DT),fund_cd from FUNTPUBINF group by fund_cd);
update FUNDTZLINF set TX_DT=to_char(sysdate,'yyyymmdd') where (tx_dt,FUND_CODE) in (select max(TX_DT),FUND_CODE from FUNDTZLINF group by FUND_CODE);

--查下是否有绑卡
SELECT COUNT(1) BIND_COUNT FROM (SELECT CAP_CRD_NO FROM
				URMTFUNDINF WHERE
				USR_NO = (select usr_no from Urmtfinf where mbl_no ='13574856074')
				AND
				CAP_CRD_NO IS NOT NULL UNION
				SELECT
				CAP_CRD_NO FROM URMTFINF WHERE
				USR_NO =(select usr_no from Urmtfinf where mbl_no ='13574856074')
				AND CAP_CRD_NO IS NOT NULL);
--查下活动
SELECT * FROM MKMTJOINACT WHERE USR_NO ='36da4594adf215416e8b1a15' AND
                                MKT_ID='17';
select * from urmtpinf where mbl_no='17758164527';
select * from urmtpinf where usr_no ='bab34a81baae357d7e37d583';
select * from urmtfinf where id_no='150207197410268043';
select * from Urmtfinf where mbl_no ='13621607648';

--定期理财产品信息表
select * from cmmtpro where prd_id='JF201506020000000408';

select * from MCAADM.PTSTWEBCONFIG where url like '%MCA2027078%';

select * from ptstwebconfig where out_url like '%p';
select * from ptstwebconfig where out_url like '%detailfundinf';
select * from ptstwebconfig where out_url like '%getCmsTyp';


select * from ptstwebconfig where out_url like '/m';
select * from ptstwebconfig where out_url like '%login';
select * from ptstwebconfig where out_url like '%qryajxwallet';
select * from ptstwebconfig where out_url like '%walletLicai';
select * from ptstwebconfig where out_url like '%fqbdata';
--pc基金交易记录
select * from ptstwebconfig where out_url like '%getfundinf';
--APP基金交易记录
select * from ptstwebconfig where out_url like '%qrydqincfund';
Set define on;
select menuid&1 from ptstwebconfig;
--
select * from ptstwebconfig where out_url like '%qryhqfund';
--天天基金交易记录查询
select * from ptstwebconfig where out_url like '%userSmsRegister';
select * from ptstwebconfig where out_url like '%agreement';
select * from ptstwebconfig where out_url like '%bindcard';
select * from ptstwebconfig where out_url like '%sendmsg';
--图形验证码
select * from ptstwebconfig where out_url like '/';
select * from ptstwebconfig where out_url like '%forgetPayPass';

select * from PWMTBANK where CAP_CORG='CCB';

select * from ptstwebconfig where out_url like '%huoInvestAgreement';
select * from ptstwebconfig where out_url like '%qryredhsdetail';
select * from ptstwebconfig where out_url like '%jpushMsgDetail';
select * from ptstwebconfig where out_url like '%getfundetail';


select * from ptstwebconfig where out_url like '%qryfqb_tradedetail';
select * from ptstwebconfig where out_url like '%qryFhbAmt';
select * from ptstwebconfig where out_url like '%detailfundinf';
--基金买入交易信息
select * from ptstwebconfig where out_url like '%sellOutConfirm';
--基金赎回交易信息
select * from ptstwebconfig where out_url like '%/m/forgetPayPass';

--注册逻辑
select * from ptstwebconfig where out_url like '%userSmsRegister';
select * from ptstwebconfig where out_url like '%userRegisterSendSms';
select * from ptstwebconfig where out_url like '%userSmsRegisterResult';
select * from ptstwebconfig where out_url like '%sellOutConfirm';
select * from ptstwebconfig where url like '%MCA202708%';
select * from ptstwebconfig where url like '%MCA5010771%';--fundProInfo.jspfundProInfo.jsp

--分组推送
select * from CMMJPUSHGROUP;

select * from FUNDPRODUCTINF;
--用户信息表
select count(1) from urmtpinf where cre_dt = TO_CHAR(SYSDATE, 'YYYYMMDD');

select * from URMTFINF;
--新绑卡信息表
select * from URMTFUNDINF;
select * from URMTFUNDINF where mbl_no='13076847530';
update URMTFUNDINF where fund_corg='YK' and mbl_no='13076847530';
--
select * from Urmtpinf;

--对卡信息
select * from PWMTBANK;

--对外url映射
select t.*,t.rowid from mcaadm.PTSTWEBCONFIG t where url like '/OMCAMKM1/MCA13010%';


select * from CMMTSMSCONFIG;

INSERT INTO PUBTTXIF (AP_MMO, MOD_NAM, SVR_NM, TX_CD, TX_TYP, BUS_TYP, VLD_FLG, LOG_FLG, TX_NM, COR_ALW_FLG, CHKC_FLG, TX_LVL, ATH_LVL1, ATH_LVL2, TX_CLS, FIN_FLG, HOL_RUN_FLG, SELF_ATH_FLG, BAT_ATH_FLG, BAT_ATH_NUM, STSW, RUN_MODE, UPD_DT, UPD_TLR, TM_SMP)
VALUES ('PAY', 'PWM', 'OPWMPUB7', '2027014', ' ', ' ', '1', '1', '补发新手红包', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', TO_CHAR(SYSDATE, 'YYYYMMDD'),' ',TO_CHAR(SYSDATE,'YYYYMMDDHHMISS'));

--清除账号信息
--查下短信验证码码
select * from URMTSMLG where mbl_no='18810563006' order by send_date desc ;
delete URMTCERTI where log_id='15608404490' ;
delete urmtfundinf where usr_no in (select usr_No from urmtpinf where mbl_no='15608404490');
delete urmtfinf where usr_no in (select usr_No from urmtpinf where mbl_no='15608404490');
delete urmtpinf where usr_no in (select usr_No from urmtpinf where mbl_no='15608404490');
delete from mkmtbon where usr_mbl_no='15608404490';

commit;
select * from urmtfinf where mbl_no='18810563006';
select * from URMTFUNDINF where mbl_no='18810563006';
select * from urmtpinf where mbl_no='18810563006';
update urmtpinf set Real_Flag=0,id_no=null,real_name=null where usr_no='b2884c0782c0580ee240dbea';
 update URMTFUNDINF set bind_flag=1,state=1 where mbl_no='18826210743';

--购买地址
select * from ptstwebconfig where out_url like '%ZLdetailfundinf';--招联


--交易记录

select * from ptstwebconfig where out_url like '%exchangeOutCard';


select * from ptstwebconfig where out_url like '%userRegisterSendSms';

select * from ptstwebconfig where out_url like '%qryjjfund%';
select * from ptstwebconfig where out_url like '%fixedRecommend%';
select * from PRDTPURCORD where ppd_pay_amt!=0;

select * from PRDTUSRORD where cm_cd='';
--tab配置表
select * from CMMTTABDATA order by mod_id,id;
select * from CMMTNAVBUTDATA order by rel_id,id;

select * from PRDTPSFORD where bus_typ='0505';

--活期交易记录
select * from PRDTUSRORD where mbl_no='13076847530';
select * from PRDTPURCORD where ord_sts='M';
select * from prdtpurcord where mbl_no='18602031889' and ord_sts in ('S','R','M'); ppd_out_ord='WBF20160214114858276443';

--定期交易
select * from payadm.PRDTJFREDEJRL where mbl_no='18605451272';
select * from payadm.PRDTREDORD where usr_no = (select usr_no from urmtpinf where mbl_no='18605451272');
--首页推荐配置
select * from PRDTRECOMMEND;
select * from PRDTRECOMMENDMOD;
select * from URMTFUNDINF;
select  bnk_id 银行id,B.BANK_NAME 银行名称,count(1) 购买次数,sum(ppd_ord_amt) 购买总金额  from prdtpurcord A,FUNMAXPRICE B where a.bnk_id=B.BANK_ID group by bnk_id,B.BANK_NAME order by sum(ppd_ord_amt) desc,count(1) desc;
select cap_corg 银行id,B.BANK_NAME 银行名称,count(1) 绑卡数量 from URMTFUNDINF A,FUNMAXPRICE B where A.cap_corg = B.BANK_ID AND fund_corg in('REDH','JF') group by cap_corg,B.BANK_NAME order by count(1) desc;

--版本跟新表
select * from CMMTCASVER;

--精选理财
SELECT * FROM CMMTJXPRO WHERE FUND_CD='JF201506020000000408' AND FUND_CORG='JF';
select * from CMMTJXPRO where show_flg='1' and '201609012' BETWEEN SHOW_BEG_DT AND SHOW_END_DT order by prd_lvl desc ;

--首页banner
select * from CMSTCNT where Cnt_Typ_Id in(select typ_id from CMSTCTP where TYP_POS = 'BAN');
select * from CMSTCTP where TYP_POS = 'BAN';

INSERT INTO PUBTTXIF (AP_MMO, MOD_NAM, SVR_NM, TX_CD, TX_TYP, BUS_TYP, VLD_FLG, LOG_FLG, TX_NM, COR_ALW_FLG, CHKC_FLG, TX_LVL, ATH_LVL1, ATH_LVL2, TX_CLS, FIN_FLG, HOL_RUN_FLG, SELF_ATH_FLG, BAT_ATH_FLG, BAT_ATH_NUM, STSW, RUN_MODE, UPD_DT, UPD_TLR, TM_SMP)
VALUES ('PAY', 'PRD', 'OPRDPUB1', '5010111', ' ', ' ', '1', '1', '查询首页轮询信息', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', TO_CHAR(SYSDATE, 'YYYYMMDD'),' ',TO_CHAR(SYSDATE,'YYYYMMDDHHMISS'));

--推送流水表
select * from CMMJPUSHMSGJRL;
select * from CMMURMDEVICE where usr_no =(select usr_no from urmtpinf where mbl_no='15905201572');
select * from CMMJPUSHGROUP;

--错误码查询
select * from PUBTTMS where msg_cd like 'URM%';
select * from PUBTTMS where msg_inf like '%{1}%';
--短信发送量统计
select substr(send_date,0,6) from URMTSMLG where send_date like '201602%';
select sum(send_num) 短信发送量,substr(send_date,0,6) 月份 from URMTSMLG group by substr(send_date,0,6) order by substr(send_date,0,6);

--易兴通短信下发统计
select count(1) 短信发送量,substr(tx_dt,0,6) 月份 from SMSTSMMO where  send_sts='S' group by substr(tx_dt,0,6);
select count(1) from SMSTSMMO where tx_dt like '201602%' and send_sts='S';
select * from SMSTSMMO;
select count(1),tx_dt from SMSTSMMO where sms_cd!='URM002'  group by tx_dt order by tx_dt desc;

---短信模板
SELECT * FROM SMSTTMPS where sms_cd = 'URM002';
UPDATE "SMSTTMPS" SET SMS_TMP = '本次操作验证码：{$}，工作人员不会向您索要此验证码，任何人索要此验证码均为诈骗' WHERE sms_cd = 'URM002';

select count(1) from SMSTSMMO where sms_cd = 'URM002' and RET_MSG  like '%0000%' order by sm_jrn_no desc ;
select count(1) from SMSTSMMO where sms_cd = 'URM002' order by sm_jrn_no desc ;
select count(1),tx_dt from SMSTSMMO where sms_cd = 'URM002' group by tx_dt;


--会员积分
--积分产品配置表
select a.USR_NO,a.SCORES,a.TM_SMP from MKMSCORESTAS a where a.USR_NO =(select usr_no from urmtpinf where mbl_no='15874864095');
update MKMSCORESTAS set scores='400000' where usr_no =(select usr_no from urmtpinf where mbl_no='13574856074');
--会员中心
select * from Mkmscorerule;
select * from Mkmscorerecord;
select * from Mkmscorerecord2;

select * from mkmscorestas;
select * from mkmscorerecord;
select * from mkmscorerule;

--外部券
select * from MKMOUTSCOREPRD; 
SELECT * FROM MKMSCOREUSEORD WHERE JRL_NO='201506120005067326';
select * from MKMOUTSCOREPRDDETAIL where Prd_Id in('006','007','008');

--内部券
select * from MKMSCOREPRD;
--抽奖
select * from Gifttconfig;
select * from Giftlotteryjrl where mkt_id = 31 and substr(create_time,0,8) < '20160601' and usr_time is not null and gift_id ='P7';
--查下奖品
select * from Giftlotteryjrl where mbl_no='18163758123';
select * from Giftlotteryjrl where usr_no='36da4594adf215416e8b1a15';
--查询奖品配置
select * from Gifttconfig where gift_start_dt>='20160328';

--后台ui数据字典配置表
select * from pubtbuihlp where fld_nm in ('DQ_CORG','FUND_CORG','TRANS_TYPE','') order by fld_nm;

--H5首页,理财列表配置信息
select * from cmmtmainconfig order by id;
select * from Cmmtmainconfigdata order by mod_id,mod_sort;
--drop table Cmmtmainconfigdata;
select * from Cmmtmainconfigprd order by config_prd_id,id;
select * from Cmmtmainconfigtag A order by config_prd_id,tag_mod;
select * from Cmmtmainconfigprdmod;
--alter table Cmmtmainconfigdata add COLOR varchar2(20);

--老tag配置表
select * from CMMTPROTAG;

--限额配置
select * from PRDTLIMIT;--MEMBER201606131173733
select * from PRDTLIMITDAY;
select * from PRDTGROUPRELATION;
select  * from PRDTCURPARA;

--帮助中心
select * from CMSTCTP where typ_pos = 'HELP';


select * from PWMTJNQPAG where mbl_no='15278179747';


select * from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%'   order by mod_id;
select * from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%') order by mod_id,mod_sort;
select * from Cmmtmainconfigprd where Config_Prd_Id in (select config_id from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%')) order by config_prd_id,id;
select * from Cmmtmainconfigtag where Config_Prd_Id in (select config_id from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%')) order by config_prd_id,tag_mod;

delete from Cmmtmainconfigprd where Config_Prd_Id in (select config_id from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%')) ;
delete from Cmmtmainconfigtag where Config_Prd_Id in (select config_id from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%')) ;
delete from Cmmtmainconfigdata where mod_id in(select mod_id from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%') ;
delete from cmmtmainconfig where (mod_id like 'h5_%' or mod_id like 'wc_%') and mod_id not like '%_hotsale_%'   ;


UPDATE "PAYADM"."CMMTMAINCONFIGPRD" SET FIELD_NM = 'FUND_NO,FUND_CD,FUND_NM,TO_CHAR(WK_RT,''fm999990.0000'') PRD_RAT,TO_CHAR(TEN_IN_CM,''fm999990.000'') PRD_IN_CM,DECODE(RISKLEVEL,0,''低'',1,''中低'',2,''中'',3,''中高'',''4'',''高'',''高'') RISKLEVEL,DECODE(RISKLEVEL,0,''#97D940'',1,''#97D940'',2,''#FFB838'',3,''#FFB838'',''4'',''#F62F2F'',''#F62F2F'') FUND_COLOR' WHERE ROWID = 'AAAWoYAAFAAAhdcAAT';

select * from Cmmtmainconfigtag where tag_MOd=13;



