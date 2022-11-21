package com.abanoub.cipher.ui.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abanoub.cipher.ui.theme.EncryptionWithCipherTheme
import com.abanoub.cipher.utils.CryptoManager
import com.abanoub.cipher.utils.CryptoManager.Companion.ALIAS
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cryptoManager = CryptoManager()

        setContent {
            EncryptionWithCipherTheme {

                var textToEncrypt by remember { mutableStateOf("") }
                var textToDecrypt by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {

                    TextField(
                        value = textToEncrypt,
                        onValueChange = { textToEncrypt = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Encrypt String") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Button(onClick = {
                            val bytes = textToEncrypt.encodeToByteArray()
                            val file = File(filesDir, "$ALIAS.txt")
                            if (!file.exists()) {
                                file.createNewFile()
                            }
                            val fos = FileOutputStream(file)

                            textToDecrypt = cryptoManager.encrypt(
                                byteArray = bytes,
                                outputStream = fos
                            ).decodeToString()

                        }) {
                            Text(text = "Encrypt")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(onClick = {
                            val file = File(filesDir, "$ALIAS.txt")
                            textToDecrypt = cryptoManager.decrypt(
                                inputStream = FileInputStream(file)
                            ).decodeToString()
                        }) {
                            Text(text = "Decrypt")
                        }

                    }

                    Text(text = textToDecrypt)
                }
            }
        }

    }
}
