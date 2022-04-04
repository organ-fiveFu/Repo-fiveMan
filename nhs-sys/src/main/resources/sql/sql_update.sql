
-- 2022/04/04 三测单添加护士签名
ALTER TABLE `nursing_home`.`bus_vital_sign_record`
ADD COLUMN `nurse_sign` varchar(20) NULL COMMENT '护士签名' AFTER `blood_oxygen`;