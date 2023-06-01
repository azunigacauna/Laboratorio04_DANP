package com.gaur.laboratorio04

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import com.gaur.laboratorio04.entities.BookEntity
import com.gaur.laboratorio04.entities.StudentEntity
import com.gaur.laboratorio04.model.AppDatabase
import com.gaur.laboratorio04.model.Repository
import com.gaur.laboratorio04.ui.theme.Laboratorio04Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio04Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val repository = Repository(AppDatabase.getInstance(context.applicationContext))

                    RoomSample(repository)

                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RoomSample(repository: Repository) {
    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillTables(repository, scope)
        }

        val studentsOnClick: () -> Unit = {
            scope.launch {
                repository.getAllStudents().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        val booksOnClick: () -> Unit = {
            scope.launch {
                repository.getAllBooks().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        val studentWithBooksOnClick: () -> Unit = {
            scope.launch {
                repository.getStudentWithBooks() .forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = fillDataOnClick) {
            Text(text = "Llenar las mesas de estudiantes y libros")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentsOnClick) {
            Text(text = "Mostrar Estudiantes")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = booksOnClick) {
            Text(text = "Mostrar libros")

        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentWithBooksOnClick) {
            Text(text = "Estudiantes con libros")
        }

    }
}

fun fillTables(rep: Repository, scope: CoroutineScope) {

    val students = ArrayList<StudentEntity>()

    for (i in 100..120) {
        val studentEntity = StudentEntity(i, fullname = "Estudiante " + i.toString())
        students.add(studentEntity)
    }

    scope.launch {
        rep.insertStudents(students)
    }

    for (i in 0..20) {
        val studentId = Random.nextInt(100, 120)
        val bookEntity = BookEntity(name = "Libro " + i.toString(), studentId)
        scope.launch {
            rep.insertBook(bookEntity)
        }

    }


}

private fun setupTable(){
    val tableRow0 = TableRow(requireContext())
    tableRow0.setBackgroundResource(R.color.black)
    //Crear Headers para la tabla
    val textView0 = TextView(requireContext())
    textView0.text=" Nombre del estudiante "
    //agregar textView a la fila de la tabla:
    tableRow0.addView(textView0)
    val textView1 = TextView(requireContext())
    textView1.text=" Nombre del libro "
    //agregar textView a la fila de la tabla:
    tableRow0.addView(textView1)
    for (i in 1..5){
        val tblRow = TableRow(requireContext())
        val tv0 = TextView(requireContext())
        tv0.text=" Estudiante $i "
        tv0.gravity= Gravity.CENTER
        tblRow.addView(tv0)

        val tv1 = TextView(requireContext())
        tv1.text=" Libro ${i*10} "
        tv1.gravity=Gravity.CENTER

    }
}

fun requireContext(): Context {

}


