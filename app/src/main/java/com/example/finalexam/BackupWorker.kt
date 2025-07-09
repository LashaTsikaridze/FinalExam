package com.example.finalexam

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.finalexam.datebasa.Animal
import AnimalDatabaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class BackupWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val dbHelper = AnimalDatabaseHelper(applicationContext)
            val animalList = dbHelper.getAllAnimals()

            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return Result.failure()

            val backupRef = FirebaseDatabase.getInstance().reference
                .child("backups")
                .child(userId)
                .child("animals")

            // წინა მონაცემების წაშლა, რომ თავიდან ჩაიწეროს
            backupRef.removeValue().await()

            // ცხოველების გადატანა Firebase-ში უნიკალური push key-ებით
            for (animal in animalList) {
                val newRef = backupRef.push()
                val data = mapOf(
                    "id" to animal.id,
                    "name" to animal.name,
                    "weight" to animal.averageWeight,
                    "photoUri" to animal.photoUri,
                    "lifespan" to animal.lifespan,
                    "description" to animal.description,
                    "habitat" to animal.habitat
                )
                newRef.setValue(data).await()
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
