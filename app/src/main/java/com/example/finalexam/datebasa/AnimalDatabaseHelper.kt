import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.finalexam.datebasa.Animal

class AnimalDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "animals.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "animal"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_WEIGHT = "average_weight"
        private const val COLUMN_PHOTO = "photo_uri"
        private const val COLUMN_LIFESPAN = "lifespan"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_HABITAT = "habitat"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_WEIGHT REAL,
                $COLUMN_PHOTO TEXT,
                $COLUMN_LIFESPAN INTEGER,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_HABITAT TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertAnimal(animal: Animal): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_WEIGHT, animal.averageWeight)
            put(COLUMN_PHOTO, animal.photoUri)
            put(COLUMN_LIFESPAN, animal.lifespan)
            put(COLUMN_DESCRIPTION, animal.description)
            put(COLUMN_HABITAT, animal.habitat)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllAnimals(): List<Animal> {
        val animals = mutableListOf<Animal>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val animal = Animal(
                    id = getInt(getColumnIndexOrThrow(COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(COLUMN_NAME)),
                    averageWeight = getDouble(getColumnIndexOrThrow(COLUMN_WEIGHT)),
                    photoUri = getString(getColumnIndexOrThrow(COLUMN_PHOTO)),
                    lifespan = getInt(getColumnIndexOrThrow(COLUMN_LIFESPAN)),
                    description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    habitat = getString(getColumnIndexOrThrow(COLUMN_HABITAT))
                )
                animals.add(animal)
            }
            close()
        }
        return animals
    }


    fun deleteAnimal(id: Int): Boolean {
        val db = writableDatabase
        println("Deleting animal with id: $id")
        return try {
            val rowsDeleted = db.delete("animals", "id = ?", arrayOf(id.toString()))
            println("Rows deleted: $rowsDeleted")
            rowsDeleted > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}


