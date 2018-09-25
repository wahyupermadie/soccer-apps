package com.example.wahyupermadi.kotlinsubmission2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.wahyupermadi.kotlinsubmission2.model.Team
import org.jetbrains.anko.db.*
import java.nio.file.attribute.FileAttributeView

class DBHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "db_favorite.db", null, 1) {
    companion object {
        private var instance : DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if(instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_MATCH to TEXT + UNIQUE,
                Favorite.HOME_TEAM to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.EVENT_DATE to TEXT,
                Favorite.AWAY_SCORE to TEXT,
                Favorite.HOME_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)