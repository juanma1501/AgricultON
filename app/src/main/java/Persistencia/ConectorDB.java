package Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import Dominio.Cultivo;
import Dominio.Paso;
import Dominio.Usuario;

public class ConectorDB {
    static final String NOMBRE_BBDD = "BD";
    private AdminSQLiteOpenHelper dbHelper;
    private SQLiteDatabase BD;

    /*Constructor*/
    public ConectorDB (Context ctx)
    {
        dbHelper = new AdminSQLiteOpenHelper(ctx, NOMBRE_BBDD, null, 1);
    }


    /*Abre la conexión con la base de datos*/
    public ConectorDB abrir() throws SQLException
    {
        BD = dbHelper.getWritableDatabase();
        return this;
    }


    /*Cierra la conexión con la base de datos*/
    public void cerrar()
    {
        if (BD != null) BD.close();
    }

    /*inserta un usuario en la BD*/
    public void insertarUsuario(Usuario usuario)
    {
        BD = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("contrasena", usuario.getContrasena());
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellidos());
        values.put("fecha_nacimiento", usuario.getFechaNacimiento());
        values.put("ultimoAcceso", usuario.getUltimoAcceso());
        values.put("foto",usuario.getFoto());

        // Inserting Row
        BD.insert("Usuarios", null, values);
        BD.close();
    }

    public void insertarCultivo(Cultivo cultivo){
        BD = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descripcion", cultivo.getDescripcion());
        values.put("foto", cultivo.getFoto());
        values.put("nombre", cultivo.getNombre());

        // Inserting Row
        BD.insert("Cultivos", null, values);
        BD.close();
    }

    public void insertarPaso(Paso paso)
    {
        BD = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idUser", paso.getIdUser());
        values.put("pasos", paso.getPasos());
        values.put("fecha", paso.getFecha());

        // Inserting Row
        BD.insert("Pasos", null, values);
        BD.close();
    }

    /*devuelve todos los Usuarios*/
    public Cursor listarUsuario()
    {
        return BD.rawQuery("SELECT * FROM Usuario", null);
    }

    public int checkUser(String email, String contrasena)
    {
        BD = dbHelper.getReadableDatabase();
        int id=-1;
        Cursor cursor= BD.rawQuery("SELECT idUser FROM Usuarios WHERE email=? AND contrasena=?",
                new String[]{email, contrasena});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        BD.close();
        return id;
    }

    public int checkUser(String email)
    {
        Log.d("consulta leída", "consulta leída");
        BD = dbHelper.getReadableDatabase();
        int id=-1;
        Cursor cursor= BD.rawQuery("SELECT idUser FROM Usuarios WHERE email=?",
                new String[]{email});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        BD.close();
        return id;
    }

    public Usuario leerUsuario(int id){
        BD = dbHelper.getReadableDatabase();
        Usuario usuario = null;
        String[] campos = new String[] {"email","contrasena","nombre","apellido","fecha_nacimiento","ultimoAcceso","foto"};

        Cursor c = BD.query("Usuarios", campos, "idUser="+ id, null,
                null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String email = c.getString(0);
                String pass = c.getString(1);
                String nombre = c.getString(2);
                String apellidos = c.getString(3);
                String fechaNacimiento = c.getString(4);
                String ultimoAcceso = c.getString(5);
                String foto = c.getString(6);
                usuario = new Usuario(id, pass, nombre, apellidos, fechaNacimiento, email, ultimoAcceso, foto);
            } while(c.moveToNext());
        }

        usuario.setListaPasos(leerPasos(usuario));
        BD.close();

        return usuario;
    }

    private ArrayList<Paso> leerPasos(Usuario usuario) {
        BD = dbHelper.getReadableDatabase();
        ArrayList<Paso> pasos = new ArrayList<Paso>();
        String[] campos = new String[] {"pasos", "fecha"};
        int idUser = usuario.getIdUser();

        Cursor c = BD.query("Pasos", campos, "idUser="+ idUser, null,
                null, null, "fecha");

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int pasos_ = c.getInt(0);
                String fecha = c.getString(1);
                Paso paso = new Paso(idUser, pasos_, fecha);
                pasos.add(paso);
                Log.d("Debug_BBDD","Paso leído:"+pasos.toString());
            } while(c.moveToNext());
        }
        BD.close();
        return pasos;
    }

}
