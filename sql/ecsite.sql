/*クライアントがSQLステートメントをサーバーに送信するために使用する文字セット*/
set names utf8;
/*MySQLで外部キー制約を適用しているテーブルにはDROP TABLEができない
 * 一時的に外部キー制約を無効にすることができる*/
set foreign_key_checks = 0;
/*DROP TABLE IF EXISTS でテーブルが存在すれば削除する*/
drop database if exists ecsite;
/*もし存在しなければデータベースecsiteを作成*/
create database if not exists ecsite;
use ecsite;

/*DROP TABLE IF EXISTS でテーブルが存在すれば削除する*/
drop table if exists login_user_transaction;

/*テーブルを作成する
 *テーブルの中でただ一つのデータを特定する＝primary key
 *データベーステーブルのカラムに適用される制約の一つで、そのカラムに重複した値が入らないようにする制約＝unique
 *文字列格納＝varchar*/
create table login_user_transaction(
id int not null primary key auto_increment,
login_id varchar(16) unique,
login_pass varchar(16),
user_name varchar(50),
insert_date datetime,
updated_date datetime);

/*DROP TABLE IF EXISTS でテーブルが存在すれば削除する*/
drop table if exists item_info_transaction;

/*テーブルを作成する
 *テーブルの中でただ一つのデータを特定する＝primary key
 *文字列格納＝varchar
 *小数を含まない値を扱うためのデータ型＝int*/
create table item_info_transaction(
id int not null primary key auto_increment,
item_name varchar(30),
item_price int,
item_stock int,
insert_date datetime,
update_date datetime);

/*DROP TABLE IF EXISTS でテーブルが存在すれば削除する*/
drop table if exists user_buy_item_transaction;

/*テーブルを作成する
 *テーブルの中でただ一つのデータを特定する＝primary key
 *文字列格納＝varchar
 *小数を含まない値を扱うためのデータ型＝int*/
create table user_buy_item_transaction(
id int not null primary key auto_increment,
item_transaction_id int,
total_price int,
total_count int,
user_master_id varchar(16),
pay varchar(30),
insert_date datetime,
delete_date datetime);

/*データベースのテーブル上にデータを登録する際に使用されるステートメントの構文=INSERT INTO
 *1 つ以上の行のセットをテーブルとして返す＝VALUES
 *作成したテーブルに情報を格納する*/
INSERT INTO item_info_transaction(item_name, item_price, item_stock) VALUES("ノ ー ト Book", 100, 50);
INSERT INTO login_user_transaction(login_id, login_pass, user_name) VALUES("diworks", "diworks01", "test");
