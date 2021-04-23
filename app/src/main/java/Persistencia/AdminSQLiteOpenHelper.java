package Persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        try{
        BD.execSQL("create table Usuarios (idUser INTEGER PRIMARY KEY AUTOINCREMENT, email text unique, contrasena text, nombre text, apellido text, fecha_nacimiento text, ultimoAcceso text, foto text)");
        BD.execSQL("create table Cultivos (idCultivo INTEGER PRIMARY KEY AUTOINCREMENT, nombre text unique, descripcion text, foto text)");
        BD.execSQL("create table Pasos (idPaso INTEGER PRIMARY KEY AUTOINCREMENT, idUser integer, pasos integer, fecha text)");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {
        try {
            /*Se elimina la versión anterior de la table*/
            BD.execSQL("DROP TABLE IF EXISTS Usuarios");
            BD.execSQL("DROP TABLE IF EXISTS Cultivos");
            BD.execSQL("DROP TABLE IF EXISTS Pasos");

            /*Se crea la nueva versión de la table*/
            BD.execSQL("create table Usuarios (idUser INTEGER PRIMARY KEY AUTOINCREMENT, email text unique, contrasena text, nombre text, apellido text, fecha_nacimiento text, ultimoAcceso text, foto text)");
            BD.execSQL("create table Cultivos (idCultivo INTEGER PRIMARY KEY AUTOINCREMENT, nombre text unique, descripcion text, foto text)");
            BD.execSQL("create table Pasos (idPaso INTEGER PRIMARY KEY AUTOINCREMENT, idUser integer, pasos integer, fecha text)");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
