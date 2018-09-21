package com.example.wahyupermadi.kotlinsubmission2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.wahyupermadi.kotlinsubmission2.model.Team
import org.jetbrains.anko.db.*
import java.nio.file.attribute.FileAttributeView

class DBHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "favoriteTeams.db", null, 1) {
    companion object {
        private var instance : DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if(instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_MATCH to TEXT + UNIQUE,
                Favorite.AWAY_DEFF to TEXT,
                Favorite.HOME_DEFF to TEXT,
                Favorite.AWAY_FORMATION to TEXT,
                Favorite.HOME_FORMATION to TEXT,
                Favorite.AWAY_FORWARD to TEXT,
                Favorite.HOME_FORWARD to TEXT,
                Favorite.AWAY_GOALDET to TEXT,
                Favorite.HOME_GOALDET to TEXT,
                Favorite.AWAY_GOALKEEPER to TEXT,
                Favorite.HOME_GOALKEEPER to TEXT,
                Favorite.AWAY_MIDFIELD to TEXT,
                Favorite.HOME_MIDFIELD to TEXT,
                Favorite.AWAY_ID to TEXT,
                Favorite.HOME_ID to TEXT,
                Favorite.AWAY_SCORE to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.HOME_TEAM to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.AWAY_SHOT to TEXT,
                Favorite.HOME_SHOT to TEXT,
                Favorite.DATE_EVENT to TEXT,
                Favorite.HOME_SUBS to TEXT,
                Favorite.AWAY_SUBS to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)