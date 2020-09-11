DROP DATABASE IF EXISTS `logging_sample`;
CREATE DATABASE `logging_sample` DEFAULT CHARSET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON `logging_sample`.* TO logging_sample@localhost IDENTIFIED BY 'パスワード';