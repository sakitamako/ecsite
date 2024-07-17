package com.diworksdev.ecsite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//ユーザー登録機能

//全てのクラス 変数
public class DateUtil {

	//全てのクラス 変数 変数名
	public String getDate() {

		//インスタンス化（コピーして代入）初期値
		//年、月、日、時間、分、秒の値を取得
		Date date = new Date();

		//SimpleDateFormatは、日付のフォーマットと解析を、ロケールを考慮して行うための具象クラスです。
		//フォーマット(日付→テキスト)、解析(テキスト→日付)および正規化を行うことができます。
		//現在日時を指定フォーマットで取得する
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//処理結果を渡す
		return simpleDateFormat .format(date);

	}

}
